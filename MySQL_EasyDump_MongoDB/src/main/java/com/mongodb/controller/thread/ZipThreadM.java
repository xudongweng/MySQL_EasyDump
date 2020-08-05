/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.controller.thread;


import com.mongodb.controller.MongoDBController;
import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.compress.ZipUtilsHelper;
import com.xml.helper.file.FileHelper;

/**
 *
 * @author xudong.weng
 */
public class ZipThreadM extends Thread{
    private String zippath="";
    private int day=0;
    public ZipThreadM(String zippath,int day){
        this.zippath=zippath;
        this.day=day;
    }
    
    @Override
     public void run() {
        //StringBuilder sbLog=new StringBuilder();
        //sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" 开始压缩：").append(this.zippath).append("\n");
        MongoDBController.insertDumpLog(NowString.getTime("yyyy-MM-dd"),NowString.getTime("HH:mm:ss"), "Start compress","", this.zippath);
        try{
            ZipUtilsHelper.compress(this.zippath);
        }catch(Exception e){
            FileHelper.outputFileFormat(PublicProperty.getErr(),"ZipThread.run",e.getMessage());
        }
        //sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" 压缩完成：").append(this.zippath).append("\n");
        MongoDBController.insertDumpLog(NowString.getTime("yyyy-MM-dd"),NowString.getTime("HH:mm:ss"), "Finished compress","", this.zippath);
        //System.out.println(sbLog);
        //FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        //sbLog.delete(0,sbLog.length());
        
        //删除未压缩备份文件
        FileHelper.deleteFolder(this.zippath);
        
        String pathtemp=this.zippath.substring(0, this.zippath.lastIndexOf(PublicProperty.getPathmark()));
        pathtemp=pathtemp.substring(0, pathtemp.lastIndexOf(PublicProperty.getPathmark()));
        //删除过期备份文件，根据结束符@_@
        //int day=this.zipday;
        if(FileHelper.getFileNumber(pathtemp)>this.day){
            FileHelper.deleteFile(FileHelper.getEarliestFile(pathtemp));
        }
     }
}
