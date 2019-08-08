/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.controller;

import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.compress.ZipFileDic;
import com.xml.helper.compress.ZipReadHelper;
import com.xml.helper.file.FileHelper;
import java.io.File;
import java.util.Map;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;
import com.xml.model.url.link.Database;
import com.xml.model.url.link.DatabaseDic;
import com.xml.model.url.link.LinkDic;

/**
 *
 * @author xudong.weng
 */
public class ZipFileCheckController {
    public static void readBackupCheck(){
        //确认URLDic是否存在数据
        if(URLDic.getURLs()==null){
            XMLController.readURLs(PublicProperty.getMysqlIndex());
        }
        StringBuilder sbLog=new StringBuilder();
        sbLog.append("\nStart checking...\n");
        for (String keylinkname : URLDic.getURLs().keySet()) {
            if(FileHelper.getFileExist(keylinkname+".xml")){
                
                //获取URL
                URL url = URLDic.getURLs().get(keylinkname);
                if(LinkDic.getLinks()==null){
                    XMLController.readDatabase(keylinkname);
                }
                DatabaseDic databasedic=LinkDic.getLink(keylinkname);
                Map<String,Database> databases=databasedic.getDatabases();
                for(String keydatabase : databases.keySet()){
                    String path1=FileHelper.getHistoryFile(url.getSavepath()+File.separator+keylinkname+File.separator+keydatabase,0);
                    String path2=FileHelper.getHistoryFile(url.getSavepath()+File.separator+keylinkname+File.separator+keydatabase,1);
                    //System.out.println(path1+"\n"+path2);
                    
                    if(!"".equals(path1)&&!"".equals(path2)){
                        
                        File f1= new File(path1);
                        File f2= new File(path2);
                        sbLog.append(path1).append("\n").append(path2).append("\n").append((float)f1.length()/(float)f2.length()).append("\n");
                        //System.out.println(f1.length()+"\n"+f2.length());
                        if((float)f1.length()/(float)f2.length()<1){
                            ZipFileDic zfd1=ZipReadHelper.getZipInfo(path1);
                            ZipFileDic zfd2=ZipReadHelper.getZipInfo(path2);
                            for(String keyzipfile:zfd1.getZipFiles().keySet()){
                                if(zfd2.getExist(keyzipfile)){
                                    System.out.println(keyzipfile+" : "+(float)zfd1.getZipFile(keyzipfile).getSize()/(float)zfd2.getZipFile(keyzipfile).getSize());
                                    sbLog.append(keyzipfile).append(" : ").append((float)zfd1.getZipFile(keyzipfile).getSize()/(float)zfd2.getZipFile(keyzipfile).getSize()).append("\n");
                                    zfd2.removeZipFile(keyzipfile);
                                }else{
                                    System.out.println(keyzipfile+" : exist 1");//显示所有只存在于2的表
                                    sbLog.append(keyzipfile).append(" : exist 1\n");
                                }
                            }
                            for(String keyzipfile:zfd2.getZipFiles().keySet()){
                                System.out.println(keyzipfile+" : exist 2");//显示所有只存在于2的表
                                sbLog.append(keyzipfile).append(" : exist 2\n");
                            }
                            zfd1.getZipFiles().clear();
                            zfd2.getZipFiles().clear();
                            FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname()+"c", sbLog.toString());
                            sbLog.delete(0,sbLog.length());
                        }
                    }
                }
            }
        }
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname()+"c", "Finished\n\n");
    }
}
