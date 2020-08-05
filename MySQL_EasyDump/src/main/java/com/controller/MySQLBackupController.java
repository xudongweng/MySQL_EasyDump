/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.helper.file.FileHelper;
import com.helper.MySQLHelper;
import com.helper.NowString;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.RowSet;
import model.dump.DumpArrObject;
import model.dump.DumpOption;
import com.cfg.PublicProperty;
import com.controller.thread.BackupThread;
import com.controller.thread.ZipThread;
import com.helper.compress.ZipUtilsHelper;
import com.helper.file.object.DiskObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import model.url.BackupURLDic;
import model.url.link.Database;
import model.url.link.DatabaseDic;
import model.url.link.LinkDic;
import model.url.URL;
import model.url.URLDic;


public class MySQLBackupController {
    
    // <editor-fold defaultstate="collapsed" desc="执行MYSQLDUMP">
    //执行MYSQLDUMP
    public static boolean exportDatabaseTool(String dump){
        try {
            Process process = Runtime.getRuntime().exec(dump);
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                return true;
            }
        } catch (IOException | InterruptedException ioe) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname()," MySQLBackupController ",ioe.getMessage());
        }
        return false;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="合成备份字符串集合并返回">
    //合成备份字符串集合并返回
    public static DumpArrObject getDumpString(String keylinkname) {

        DumpArrObject dumpArr=new DumpArrObject();//mysqldump和mysqldump路径字符串集合
        DumpOption dump=new DumpOption();//mysqldump选项组合对象

        StringBuilder sbPathMerge=new StringBuilder();//路径组合字符串

        if(FileHelper.getFileExist(keylinkname+".xml")){
            //获取URL
            URL url = URLDic.getURLs().get(keylinkname);
            //组合mysqldump前半段字符串
            if(PublicProperty.getBin().equals(""))//如果有bin路径则加上
            {
                dump.setDumpModel(url.getServer(), url.getUser(), url.getPwd(), url.getPort());
            }
            else{
                dump.setDumpModel(PublicProperty.getBin(),url.getServer(), url.getUser(), url.getPwd(), url.getPort());
            }

            DatabaseDic databasedic=LinkDic.getLink(keylinkname);
            Map<String,Database> databases=databasedic.getDatabases();
            for(String keydatabase : databases.keySet())
            {
                //获取database
                Database db=databases.get(keydatabase);
                //Splite=0,做单个数据库备份
                if(db.getSplite()==0){
                    //合成保存路径
                    sbPathMerge.append(url.getSavepath()).append(File.separator).append(keylinkname).append(File.separator).append(keydatabase).append(File.separator).append(NowString.getTime("yyyyMMddHHmmss")).append(File.separator);
                    //创建保存路径
                    FileHelper.createPath(sbPathMerge.toString());
                    //组合mysqldump数据库后半段路径字符串
                    dump.setDumpDB(keydatabase, sbPathMerge.toString());
                    //存储压缩路径的组合字符串
                    dumpArr.addPath(sbPathMerge.toString());
                    //存储备份的组合字符串
                    dumpArr.addDump(dump.getMergeDumpTemp());
                    //清除临时路径字符串
                    sbPathMerge.delete(0, sbPathMerge.length());
                    //增加每个数据库备份完成的结束符
                    dumpArr.addDump("@_@");
                    //添加备份天数
                    dumpArr.addDay(String.valueOf(db.getDay()));
                }else{
                    //分表备份
                    RowSet rs=MySQLHelper.getDBTables(url.getUser(), url.getPwd(), url.getServer(),url.getPort(),keydatabase);
                    if(rs==null){
                        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),url.getUser()+"*"+url.getPwd()+"*jdbc:mysql://"+url.getServer()+":"+url.getPort()+"*"+keydatabase+":connecting fail\n");
                        break;
                    }
                    try{
                        //合成保存路径
                        sbPathMerge.append(url.getSavepath()).append(File.separator).append(keylinkname).append(File.separator).append(keydatabase).append(File.separator).append(NowString.getTime("yyyyMMddHHmmss")).append(File.separator);
                        //创建保存路径
                        FileHelper.createPath(sbPathMerge.toString());
                        //读取备份表名
                        while (rs.next()) {
                            //组合mysqldump数据库表后半段路径字符串
                            dump.setDumpTable(keydatabase, rs.getString(1), sbPathMerge.toString());
                            //存储备份的组合字符串
                            dumpArr.addDump(dump.getMergeDumpTemp());
                        }

                        //创建备份存储过程字符串
                        dump.setDumpProcedure(keydatabase, sbPathMerge.toString());
                        //存储备份的组合字符串
                        dumpArr.addDump(dump.getMergeDumpTemp());

                        //存储压缩路径的组合字符串
                        dumpArr.addPath(sbPathMerge.toString());
                        //清除临时路径字符串
                        sbPathMerge.delete(0, sbPathMerge.length());
                        //增加每个数据库备份完成的结束符
                        dumpArr.addDump("@_@");
                        //添加备份天数
                        dumpArr.addDay(String.valueOf(db.getDay()));
                    }catch( SQLException sqle){
                        sbPathMerge.delete(0, sbPathMerge.length());
                        FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLBackupController.getDumpString("+keylinkname+")",sqle.getMessage());
                    }catch(Exception e){
                        sbPathMerge.delete(0, sbPathMerge.length());
                        FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLBackupController.getDumpString("+keylinkname+")",e.getMessage());
                    }
                }
            }
        }
        return dumpArr;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="数据库对数据库备份压缩"> 
    public static void backupDTD(){
        XMLController.readURLs(PublicProperty.getMysqlIndex());
        StringBuilder sbLog=new StringBuilder();
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Initialization......\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        //获取备份序列
        BackupURLDic bud=new BackupURLDic();
        for (String linkname : URLDic.getURLs().keySet()) {
            if(FileHelper.getFileExist(linkname+".xml")){
                bud.setBackupURL(Integer.valueOf(URLDic.getURLs().get(linkname).getBakid()), linkname);
            }
        }
        //排列备份序列
        if(bud.getBackupURLs()==null){
            System.out.println("Is nothing");
            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Is nothing\n");
            FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"");
            sbLog.delete(0,sbLog.length());
            return;
        }
        List<Map.Entry<Integer,String>> backuplist=bud.getSortBackupURLs();
        
        //System.out.println(list.toString());
        for(int j=0;j<backuplist.size();j++){
            String linkname=backuplist.get(j).getValue();
            URL url=URLDic.getURL(linkname);
            MySQLHelper.execSql(url.getUser(), url.getPwd(), url.getServer(), url.getPort(), "set global net_write_timeout=1800");

            //获取URL所在数据库内容
            XMLController.readDatabase(linkname);

            DumpArrObject dumpArr=MySQLBackupController.getDumpString(linkname);

            int pathindex=0;//路径索引，在每次备份完之后，指定压缩路径。
            for(String esql:dumpArr.getArrDump())
            {
                //判断是否是结束符，不是则备份，是则压缩
                if(!esql.equals("@_@")){
                    sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start backup ").append(esql).append("\n");
                    FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                    sbLog.delete(0,sbLog.length());
                    if(MySQLBackupController.exportDatabaseTool(esql)){
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finished backup ").append(esql).append("\n");
                    }else{
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Backup failed ").append(esql).append("\n");
                        FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLBackupController.backupDTD()"," Backup failed "+esql);
                    }
                    FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                    sbLog.delete(0,sbLog.length());
                }
                else{
                    
                    //磁盘信息
                    List disklist=FileHelper.getDiskSpace();
                    for(int i=0;i<disklist.size();i++){
                        DiskObject diskobj=(DiskObject) disklist.get(i);
                        sbLog.append("Disk:").append(diskobj.getSpacepath()).append("\n");
                        sbLog.append("Freespace:").append(diskobj.getFreespace()).append("\n");
                        sbLog.append("Totalspace:").append(diskobj.getTotalspace()).append("\n");
                        sbLog.append("Usablespace:").append(diskobj.getUsablespace()).append("\n");
                        sbLog.append("Usedspace:").append(diskobj.getUsedspace()).append("\n");
                    }
                    FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                    sbLog.delete(0,sbLog.length());
                    
                    
                    //压缩文件
                    sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start compress\n");
                    try{
                        ZipUtilsHelper.compress(dumpArr.getArrPath().get(pathindex));
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        FileHelper.outputFileFormat(PublicProperty.getErr(),"Console.backupDTD",e.getMessage());
                    }
                    sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finish compressed\n");

                    FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                    sbLog.delete(0,sbLog.length());

                    //删除未压缩备份文件
                    FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                    //获取备份路径
                    System.out.println(dumpArr.getArrPath().get(pathindex));
                    String pathtemp=dumpArr.getArrPath().get(pathindex).substring(0, dumpArr.getArrPath().get(pathindex).lastIndexOf(PublicProperty.getPathmark()));
                    pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
                    //删除过期备份文件，根据结束符@_@
                    int day=Integer.valueOf(dumpArr.getArrDay().get(pathindex));
                    if(FileHelper.getFileNumber(pathtemp)>day){
                        FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
                    }
                    pathindex++;
                }
            }
            MySQLHelper.execSql(url.getUser(), url.getPwd(), url.getServer(), url.getPort(), "set global net_write_timeout=60");
        }
        bud.getBackupURLs().clear();//清除所有临时备份序列
        /*
        for (String linkname : URLDic.getURLs().keySet()) {
            //检测是否存在该链接的文件
            if(FileHelper.getFileExist(linkname+".xml")){
                //获取URL所在数据库内容
                XMLController.readDatabase(linkname);

                DumpArrObject dumpArr=MySQLBackupController.getDumpString(linkname);
                
                int pathindex=0;//路径索引，在每次备份完之后，指定压缩路径。
                for(String esql:dumpArr.getArrDump())
                {
                    //判断是否是结束符，不是则备份，是则压缩
                    if(!esql.equals("@_@")){
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start backup ").append(esql).append("\n");
                        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                        sbLog.delete(0,sbLog.length());
                        if(MySQLBackupController.exportDatabaseTool(esql)){
                            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finish backuped ").append(esql).append("\n");
                        }else{
                            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Backup failed ").append(esql).append("\n");
                        }
                        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                        sbLog.delete(0,sbLog.length());
                    }
                    else{
                        //压缩文件
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start compress\n");
                        try{
                            ZipUtilsHelper.compress(dumpArr.getArrPath().get(pathindex));
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                            FileHelper.outputFileFormat(PublicProperty.getErr(),"Console.backupDTD",e.getMessage());
                        }
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finish compressed\n");
                        
                        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                        sbLog.delete(0,sbLog.length());
                        
                        //删除未压缩备份文件
                        FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                        //获取备份路径
                        System.out.println(dumpArr.getArrPath().get(pathindex));
                        String pathtemp=dumpArr.getArrPath().get(pathindex).substring(0, dumpArr.getArrPath().get(pathindex).lastIndexOf(PublicProperty.getPathmark()));
                        pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
                        //删除过期备份文件，根据结束符@_@
                        int day=Integer.valueOf(dumpArr.getArrDay().get(pathindex));
                        if(FileHelper.getFileNumber(pathtemp)>day){
                            FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
                        }
                        pathindex++;
                    }
                }
            }
        }*/
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" All finished\n\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="数据库全备份后压缩"> 
    public static void backupATA(){
        XMLController.readURLs(PublicProperty.getMysqlIndex());
        StringBuilder sbLog=new StringBuilder();
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Initialization......\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        
        //获取备份序列
        BackupURLDic bud=new BackupURLDic();
        for (String linkname : URLDic.getURLs().keySet()) {
            if(FileHelper.getFileExist(linkname+".xml")){
                bud.setBackupURL(Integer.valueOf(URLDic.getURLs().get(linkname).getBakid()), linkname);
            }
        }
        //排列备份序列
        if(bud.getBackupURLs()==null){
            System.out.println("Is nothing");
            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Is nothing\n");
            FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"");
            sbLog.delete(0,sbLog.length());
            return;
        }
        List<Map.Entry<Integer,String>> backuplist=bud.getSortBackupURLs();
        
        List<DumpArrObject> daoArr=new ArrayList<>();//保存所有备份字符串，用于压缩。
        
        // <editor-fold defaultstate="collapsed" desc="备份"> 
        for(int j=0;j<backuplist.size();j++){
            String linkname=backuplist.get(j).getValue();
            URL url=URLDic.getURL(linkname);
            MySQLHelper.execSql(url.getUser(), url.getPwd(), url.getServer(), url.getPort(), "set global net_write_timeout=1800");
            
            //获取URL所在数据库内容
            XMLController.readDatabase(linkname);

            DumpArrObject dumpArr=MySQLBackupController.getDumpString(linkname);
            daoArr.add(dumpArr);//保存所有备份字符串，用于压缩。
            for(String esql:dumpArr.getArrDump())
            {
                //判断是否是结束符，不是则备份，是则压缩
                if(!esql.equals("@_@")){
                    sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start backup ").append(esql).append("\n");
                    FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                    sbLog.delete(0,sbLog.length());
                    if(MySQLBackupController.exportDatabaseTool(esql)){
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finished backup ").append(esql).append("\n");
                    }else{
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Backup failed ").append(esql).append("\n");
                        FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                "MySQLBackupController.backupATA()"," Backup failed "+esql);
                    }
                    FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                    sbLog.delete(0,sbLog.length());
                }
            }
            MySQLHelper.execSql(url.getUser(), url.getPwd(), url.getServer(), url.getPort(), "set global net_write_timeout=60");
            
        }
        //</editor-fold>
        
        //磁盘信息
        List disklist=FileHelper.getDiskSpace();
        for(int i=0;i<disklist.size();i++){
            DiskObject diskobj=(DiskObject) disklist.get(i);
            sbLog.append("Disk:").append(diskobj.getSpacepath()).append("\n");
            sbLog.append("Freespace:").append(diskobj.getFreespace()).append("\n");
            sbLog.append("Totalspace:").append(diskobj.getTotalspace()).append("\n");
            sbLog.append("Usablespace:").append(diskobj.getUsablespace()).append("\n");
            sbLog.append("Usedspace:").append(diskobj.getUsedspace()).append("\n");
        }
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        
        // <editor-fold defaultstate="collapsed" desc="压缩"> 
        for(DumpArrObject dumpArr:daoArr){
            //int pathindex=0;//路径索引，在每次备份完之后，指定压缩路径。
            for(int pathindex=0;pathindex<dumpArr.getArrPath().size();pathindex++){
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start compress：").append(dumpArr.getArrPath().get(pathindex)).append("\n");
                try{
                    ZipUtilsHelper.compress(dumpArr.getArrPath().get(pathindex));
                    //删除未压缩备份文件
                    FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                }catch(Exception e){
                    FileHelper.outputFileFormat(PublicProperty.getErr(),"Console.backupATA",e.getMessage());
                }
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finish compressed\n");
                
                FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                sbLog.delete(0,sbLog.length());

                //删除未压缩备份文件
                FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                
                
                String pathtemp=dumpArr.getArrPath().get(pathindex).substring(0, dumpArr.getArrPath().get(pathindex).lastIndexOf(PublicProperty.getPathmark()));
                pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
                //删除过期备份文件，根据结束符@_@
                int day=Integer.valueOf(dumpArr.getArrDay().get(pathindex));
                if(FileHelper.getFileNumber(pathtemp)>day){
                    FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
                }
            }
        }
        //</editor-fold>
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" All finished\n\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="数据库全备份后压缩 历史代码"> 
    public static void backupATA2(){
        XMLController.readURLs(PublicProperty.getMysqlIndex());
        StringBuilder sbLog=new StringBuilder();
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Initialization......\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        List<DumpArrObject> daoArr=new ArrayList<>();//保存所有备份字符串，用于压缩。
        
        // <editor-fold defaultstate="collapsed" desc="备份"> 
        for (String linkname : URLDic.getURLs().keySet()) {
            //检测是否存在该链接的文件
            if(FileHelper.getFileExist(linkname+".xml")){
                //获取URL所在数据库内容
                XMLController.readDatabase(linkname);

                DumpArrObject dumpArr=MySQLBackupController.getDumpString(linkname);
                daoArr.add(dumpArr);//保存所有备份字符串，用于压缩。
                for(String esql:dumpArr.getArrDump())
                {
                    //判断是否是结束符，不是则备份，是则压缩
                    if(!esql.equals("@_@")){
                        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start backup ").append(esql).append("\n");
                        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                        sbLog.delete(0,sbLog.length());
                        if(MySQLBackupController.exportDatabaseTool(esql)){
                            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finished backup ").append(esql).append("\n");
                        }else{
                            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Backup failed ").append(esql).append("\n");
                            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLBackupController.backupATA()"," Backup failed "+esql);
                        }
                        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                        sbLog.delete(0,sbLog.length());
                    }
                }
            }
        }
        //</editor-fold>
        
        //磁盘信息
        List disklist=FileHelper.getDiskSpace();
        for(int i=0;i<disklist.size();i++){
            DiskObject diskobj=(DiskObject) disklist.get(i);
            sbLog.append("Disk:").append(diskobj.getSpacepath()).append("\n");
            sbLog.append("Freespace:").append(diskobj.getFreespace()).append("\n");
            sbLog.append("Totalspace:").append(diskobj.getTotalspace()).append("\n");
            sbLog.append("Usablespace:").append(diskobj.getUsablespace()).append("\n");
            sbLog.append("Usedspace:").append(diskobj.getUsedspace()).append("\n");
        }
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        
        // <editor-fold defaultstate="collapsed" desc="压缩"> 
        for(DumpArrObject dumpArr:daoArr){
            //int pathindex=0;//路径索引，在每次备份完之后，指定压缩路径。
            for(int pathindex=0;pathindex<dumpArr.getArrPath().size();pathindex++){
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start compress：").append(dumpArr.getArrPath().get(pathindex)).append("\n");
                try{
                    ZipUtilsHelper.compress(dumpArr.getArrPath().get(pathindex));
                    //删除未压缩备份文件
                    FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                }catch(Exception e){
                    FileHelper.outputFileFormat(PublicProperty.getErr(),"Console.backupATA",e.getMessage());
                }
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finish compressed\n");
                
                FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                sbLog.delete(0,sbLog.length());

                //删除未压缩备份文件
                FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                
                
                String pathtemp=dumpArr.getArrPath().get(pathindex).substring(0, dumpArr.getArrPath().get(pathindex).lastIndexOf(PublicProperty.getPathmark()));
                pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
                //删除过期备份文件，根据结束符@_@
                int day=Integer.valueOf(dumpArr.getArrDay().get(pathindex));
                if(FileHelper.getFileNumber(pathtemp)>day){
                    FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
                }
            }
        }
        //</editor-fold>
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" All finished\n\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
    }
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="多线程数据库全备份后压缩"> 
    public static void backupThreadAllToAll() {
        XMLController.readURLs(PublicProperty.getMysqlIndex());
        StringBuilder sbLog=new StringBuilder();
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Initialization......\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        
        //获取备份序列
        BackupURLDic bud=new BackupURLDic();
        for (String linkname : URLDic.getURLs().keySet()) {
            if(FileHelper.getFileExist(linkname+".xml")){
                bud.setBackupURL(Integer.valueOf(URLDic.getURLs().get(linkname).getBakid()), linkname);
            }
        }
        //排列备份序列
        if(bud.getBackupURLs()==null){
            System.out.println("Is nothing");
            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Is nothing\n");
            FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"");
            sbLog.delete(0,sbLog.length());
            return;
        }
        List<Map.Entry<Integer,String>> backuplist=bud.getSortBackupURLs();
        
        List<DumpArrObject> daoArr=new ArrayList<>();//保存所有备份字符串，用于压缩。
        
        // <editor-fold defaultstate="collapsed" desc="备份"> 
        
        // 创建一个可重用固定线程数的线程池
        ExecutorService poolbk = Executors.newFixedThreadPool(PublicProperty.getBackupthread());
        
        for(int j=0;j<backuplist.size();j++){
            String linkname=backuplist.get(j).getValue();
            URL url=URLDic.getURL(linkname);
            MySQLHelper.execSql(url.getUser(), url.getPwd(), url.getServer(), url.getPort(), "set global net_write_timeout=1800");

            //获取URL所在数据库内容
            XMLController.readDatabase(linkname);

            DumpArrObject dumpArr=MySQLBackupController.getDumpString(linkname);
            daoArr.add(dumpArr);//保存所有备份字符串，用于压缩。
            for(String esql:dumpArr.getArrDump())
            {
                //判断是否是结束符，不是则备份，是则压缩
                if(!esql.equals("@_@")){
                    BackupThread bt=new BackupThread(esql);
                    poolbk.execute(bt);//执行备份
                }
            }
            MySQLHelper.execSql(url.getUser(), url.getPwd(), url.getServer(), url.getPort(), "set global net_write_timeout=60");
        }
        poolbk.shutdown();
        try{
            poolbk.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);//等待线程池结束，注销将不等待
        }catch(InterruptedException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLBackupController.backupThreadAllToAll",e.getMessage());
        }
        //</editor-fold>
        
         //磁盘信息
        List disklist=FileHelper.getDiskSpace();
        for(int i=0;i<disklist.size();i++){
            DiskObject diskobj=(DiskObject) disklist.get(i);
            sbLog.append("Disk:").append(diskobj.getSpacepath()).append("\n");
            sbLog.append("Freespace:").append(diskobj.getFreespace()).append("\n");
            sbLog.append("Totalspace:").append(diskobj.getTotalspace()).append("\n");
            sbLog.append("Usablespace:").append(diskobj.getUsablespace()).append("\n");
            sbLog.append("Usedspace:").append(diskobj.getUsedspace()).append("\n");
        }
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        
        // <editor-fold defaultstate="collapsed" desc="压缩">         
        // 创建一个可重用固定线程数的线程池
        ExecutorService poolzip = Executors.newFixedThreadPool(PublicProperty.getZipthread());
        for(DumpArrObject dumpArr:daoArr){
            //int pathindex=0;//路径索引，在每次备份完之后，指定压缩路径。
            for(int pathindex=0;pathindex<dumpArr.getArrPath().size();pathindex++){
                
                ZipThread zt=new ZipThread(dumpArr.getArrPath().get(pathindex),Integer.valueOf(dumpArr.getArrDay().get(pathindex)));
                poolzip.execute(zt);
                /*
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" 开始压缩：").append(dumpArr.getArrPath().get(pathindex)).append("\n");
                try{
                    ZipUtils.compress(dumpArr.getArrPath().get(pathindex));
                }catch(Exception e){
                    FileHelper.outputFileFormat(PublicProperty.getErr(),"Console.backupThreadAllToAll",e.getMessage());
                }
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" 压缩完成\n");
                
                FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                sbLog.delete(0,sbLog.length());

                //删除未压缩备份文件
                FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                
                String pathtemp=dumpArr.getArrPath().get(pathindex).substring(0, dumpArr.getArrPath().get(pathindex).lastIndexOf(PublicProperty.getPathmark()));
                pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
                //删除过期备份文件，根据结束符@_@
                int day=Integer.valueOf(dumpArr.getArrDay().get(pathindex));
                if(FileHelper.getFileNumber(pathtemp)>day){
                    FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
                }*/
            }
        }
        poolzip.shutdown();
        try{
            poolzip.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);//等待线程池结束，注销将不等待
        }catch(InterruptedException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLBackupController.backupThreadAllToAll",e.getMessage());
        }
        // </editor-fold>
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" All finished\n\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="多线程数据库全备份后压缩 历史代码"> 
    public static void backupThreadAllToAll2() {
        XMLController.readURLs(PublicProperty.getMysqlIndex());
        StringBuilder sbLog=new StringBuilder();
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Initialization......\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        List<DumpArrObject> daoArr=new ArrayList<>();//保存所有备份字符串，用于压缩。
        
        // <editor-fold defaultstate="collapsed" desc="备份"> 
        
        // 创建一个可重用固定线程数的线程池
        ExecutorService poolbk = Executors.newFixedThreadPool(PublicProperty.getBackupthread());
        
        for (String linkname : URLDic.getURLs().keySet()) {
            //检测是否存在该链接的文件
            if(FileHelper.getFileExist(linkname+".xml")){
                //获取URL所在数据库内容
                XMLController.readDatabase(linkname);

                DumpArrObject dumpArr=MySQLBackupController.getDumpString(linkname);
                daoArr.add(dumpArr);//保存所有备份字符串，用于压缩。
                for(String esql:dumpArr.getArrDump())
                {
                    //判断是否是结束符，不是则备份，是则压缩
                    if(!esql.equals("@_@")){
                        BackupThread bt=new BackupThread(esql);
                        poolbk.execute(bt);//执行备份
                    }
                }
            }
        }
        poolbk.shutdown();
        try{
            poolbk.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);//等待线程池结束，注销将不等待
        }catch(InterruptedException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLBackupController.backupThreadAllToAll",e.getMessage());
        }
        //</editor-fold>
        
         //磁盘信息
        List disklist=FileHelper.getDiskSpace();
        for(int i=0;i<disklist.size();i++){
            DiskObject diskobj=(DiskObject) disklist.get(i);
            sbLog.append("Disk:").append(diskobj.getSpacepath()).append("\n");
            sbLog.append("Freespace:").append(diskobj.getFreespace()).append("\n");
            sbLog.append("Totalspace:").append(diskobj.getTotalspace()).append("\n");
            sbLog.append("Usablespace:").append(diskobj.getUsablespace()).append("\n");
            sbLog.append("Usedspace:").append(diskobj.getUsedspace()).append("\n");
        }
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        
        // <editor-fold defaultstate="collapsed" desc="压缩">         
        // 创建一个可重用固定线程数的线程池
        ExecutorService poolzip = Executors.newFixedThreadPool(PublicProperty.getZipthread());
        for(DumpArrObject dumpArr:daoArr){
            //int pathindex=0;//路径索引，在每次备份完之后，指定压缩路径。
            for(int pathindex=0;pathindex<dumpArr.getArrPath().size();pathindex++){
                
                ZipThread zt=new ZipThread(dumpArr.getArrPath().get(pathindex),Integer.valueOf(dumpArr.getArrDay().get(pathindex)));
                poolzip.execute(zt);
                /*
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" 开始压缩：").append(dumpArr.getArrPath().get(pathindex)).append("\n");
                try{
                    ZipUtils.compress(dumpArr.getArrPath().get(pathindex));
                }catch(Exception e){
                    FileHelper.outputFileFormat(PublicProperty.getErr(),"Console.backupThreadAllToAll",e.getMessage());
                }
                sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" 压缩完成\n");
                
                FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
                sbLog.delete(0,sbLog.length());

                //删除未压缩备份文件
                FileHelper.deleteFolder(dumpArr.getArrPath().get(pathindex));
                
                String pathtemp=dumpArr.getArrPath().get(pathindex).substring(0, dumpArr.getArrPath().get(pathindex).lastIndexOf(PublicProperty.getPathmark()));
                pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
                //删除过期备份文件，根据结束符@_@
                int day=Integer.valueOf(dumpArr.getArrDay().get(pathindex));
                if(FileHelper.getFileNumber(pathtemp)>day){
                    FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
                }*/
            }
        }
        poolzip.shutdown();
        try{
            poolzip.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);//等待线程池结束，注销将不等待
        }catch(InterruptedException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLBackupController.backupThreadAllToAll",e.getMessage());
        }
        // </editor-fold>
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" All finished\n\n");
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
    }
    // </editor-fold>
}
