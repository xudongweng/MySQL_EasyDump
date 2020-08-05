/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper.file;

import com.helper.file.object.DiskObject;
import com.helper.file.object.FilePathObject;
import com.helper.file.object.FileObject;
import com.cfg.PublicProperty;
import com.helper.NowString;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;




public class FileHelper {
    private final static StringBuilder sb=new StringBuilder();
    
    public static boolean getFileExist(String filename){
        File file=new File(filename);
        return file.exists();
    }
    
    public static boolean deleteFile(String filename){
        File file=new File(filename);
        if (file.isFile()&&file.exists()){
            file.delete();
            return true;
        }
        return false;
    }
    
    public static boolean deleteFolder(String directory){
        File folder=new File(directory);
        
        if(folder.isDirectory()){
            if(folder.listFiles().length>0){
                File[] files=folder.listFiles();
                for(File file:files){
                    file.delete();
                }
            }
            folder.delete();
            return true;
        }
        return false;
    }
    
    public static boolean renameFile(String oldname,String newname){
        if(!oldname.equals(newname)){
            File of=new File(oldname);
            File nf=new File(newname);
            if(!of.exists()){
                System.out.println(oldname+"不存在");
                return false;
            }
            if(nf.exists()){
                System.out.println(newname+"已存在");
                return false;
            }else{
                of.renameTo(nf);
                return true;
            }
        }
        System.out.println("新文件名和旧文件名相同");
        return false;
    }
    //创建路径
    public static void createPath(String path){
        File file =new File(path);
        if  (!file.exists()  && !file.isDirectory()) {        
            file.mkdirs();
        }
    }
    //输出显示内容到文件
    public static void outputFile(String filename,String content){
        File file = new File(filename);
        try{
            @SuppressWarnings("UnusedAssignment")
            Writer writer=null;
            if(file.exists()){
                writer = new OutputStreamWriter(new FileOutputStream(file, true), "GBK");
            }
            else{
                writer = new OutputStreamWriter(new FileOutputStream(file, false), "GBK");
            }
            writer.write(content);
            writer.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"FileHelper.outputFile("+filename+","+content+")",e.getMessage());
        } catch(IOException ioe){
            outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"FileHelper.outputFile("+filename+","+content+")",ioe.getMessage());
        }
    }
    
    public static void outputFileFormat(String filename,String classname,String info){
        File file = new File(filename);
        try{
            @SuppressWarnings("UnusedAssignment")
            Writer writer=null;
            if(file.exists()){
                writer = new OutputStreamWriter(new FileOutputStream(file, true), "GBK");
            }
            else{
                writer = new OutputStreamWriter(new FileOutputStream(file, false), "GBK");
            }
            
            sb.append(NowString.getTime("yyyyMMddHHmmss")).append("\n").append("class:").append(classname).append("\n").append("error:").append(info).append("\n\n");
            
            writer.write(sb.toString());
            writer.close();
            sb.delete(0, sb.length());

        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    //获取文件属性
    public static FileObjDic getFilesPro(String path){

        File file=new File(path);
        File[] tempList=file.listFiles();
        //System.out.println(tempList.length);
        FileObjDic fod=null;//已完成备份完成对象
        FileObject fotmp=null;//未备份完成对象
        if(tempList!=null && tempList.length>0){
            
            fod=new FileObjDic();//已完成备份完成对象
            for (File tempfile : tempList) {
                if (tempfile.isFile()) {
                    FileObject fo=new FileObject();
                    //System.out.println("1"+tempList[i]);
                    fo.setFilepath(tempfile.toString());
                    //System.out.println(tempList[i].getName());
                    fo.setFilename(tempfile.getName());
                    //System.out.println(tempList[i].length()/1024/1024);
                    fo.setFilesize(FormatSize.getSize(tempfile.length()));
                    
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //System.out.println(df.format(tempList[i].lastModified()));
                    fo.setFiledate(df.format(tempfile.lastModified()));
                    fo.setBackupstate(0);
                    fod.setFileObjects(tempfile.getName(), fo);
                } else if (tempfile.isDirectory()) {
                    //确认是否有文件夹并创建对象保存
                    //System.out.println("2"+" "+i+" "+tempList[i].length());
                    fotmp=new FileObject();
                    fotmp.setFilepath(tempfile.toString());
                    fotmp.setFilename(tempfile.getName());
                    fotmp.setFilesize(FormatSize.getSize(tempfile.length()));
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    fotmp.setFiledate(df.format(tempfile.lastModified()));
                    fotmp.setBackupstate(2);
                }
            }
            if(fotmp==null){//备份完成
                return fod;
            }else{ 
                if(fod.getExist(fotmp.getFilename()+".zip")){//压缩中
                    FileObject fo=fod.getFileObject(fotmp.getFilename()+".zip");
                    fo.setBackupstate(1);
                }else{//备份中
                    fod.setFileObjects(fotmp.getFilename(), fotmp);
                }
                //fod.printFileObjects();
                return fod;
            }
        }
        return fod;
    }
    
    
    
    //获取最近文件路径
    public static String getLatestFile(String path){
        File file=new File(path);
        File[] tempList=file.listFiles();
        if(tempList==null)
            return "";
        long filedate=0;
        String filepath="";
        if(tempList.length>0){
            for (File tempfile : tempList) {
                if(filedate==0){
                    filepath=tempfile.toString();
                    filedate=tempfile.lastModified();
                }else if(filedate<tempfile.lastModified()){
                    filepath=tempfile.toString();
                    filedate=tempfile.lastModified();
                }
            }
            return filepath;
        }else{
            return filepath;
        }
    }
    
    //获取接近最近文件路径
    public static String getHistoryFile(String path,int day){
        File file=new File(path);
        File[] tempList=file.listFiles();
        if(tempList==null)
            return "";
        if(tempList.length>0){
            List<FilePathObject> listFile=new ArrayList<>();
            for (File tempfile : tempList) {
                listFile.add(new FilePathObject(tempfile.lastModified(),tempfile.getName(),tempfile.toString()));
            }
            Collections.sort(listFile);
            FilePathObject fpo=null;
            if(day<=0)
                fpo=listFile.get(0);
            else if(day>=listFile.size())
                fpo=listFile.get(listFile.size()-1);
            else
                fpo=listFile.get(day);
            listFile.clear();
            return fpo.getFilepath();
        }else{
            return "";
        }
    }
    
    //获取最早文件路径
    public static String getEarliestFile(String path){
        File file=new File(path);
        File[] tempList=file.listFiles();
        if(tempList==null)
            return "";
        long filedate=0;
        String filepath="";
        if(tempList.length>0){
            for (File tempfile : tempList) {
                if(filedate==0){
                    filepath=tempfile.toString();
                    filedate=tempfile.lastModified();
                }else if(filedate>tempfile.lastModified()){
                    filepath=tempfile.toString();
                    filedate=tempfile.lastModified();
                }
            }
            return filepath;
        }else{
            return filepath;
        }
    }
    //获取路径下文件数量
    public static int getFileNumber(String path){
        File file=new File(path);
        File[] tempList=file.listFiles();
        return tempList.length;
    }
    
    //选择文件路径文本框
    public static String getFilePath(Object obj){
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("路径");
        fileChooser.showOpenDialog((Component) obj);
        fileChooser.setVisible(true);
        File file=fileChooser.getSelectedFile();
        if(file==null)
            return "";
        else
            return file.getAbsolutePath();
    }
    
    public static List getDiskSpace(){
        File[] roots= File.listRoots();
        List diskList=new ArrayList();
        for (File size : roots) {
            DiskObject diskobj=new DiskObject();
            diskobj.setSpacepath(size.getPath());
            diskobj.setFreespace(FormatSize.getSize(size.getFreeSpace()));
            diskobj.setUsablespace(FormatSize.getSize(size.getUsableSpace()));
            diskobj.setTotalspace(FormatSize.getSize(size.getTotalSpace()));
            diskobj.setUsedspace(FormatSize.getSize(size.getTotalSpace()-size.getFreeSpace()));
            diskList.add(diskobj);
        }
        return diskList;
    }
}
