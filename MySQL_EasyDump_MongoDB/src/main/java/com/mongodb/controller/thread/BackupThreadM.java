/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.controller.thread;



import com.mongodb.controller.MongoDBController;
import com.xml.cfg.PublicProperty;
import com.xml.controller.MySQLBackupController;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;

/**
 *
 * @author xudong.weng
 */
public class BackupThreadM extends Thread{
    private String esql="";
    private String linkname="";
    public BackupThreadM(String linkname,String esql){
        this.esql=esql;
        this.linkname=linkname;
    }
    
    @Override
     public void run() {
        StringBuilder sbLog=new StringBuilder();
        //sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start backup ").append(esql).append("\n");
        MongoDBController.insertDumpLog(NowString.getTime("yyyy-MM-dd"),NowString.getTime("HH:mm:ss"), "Start backup",linkname, esql);
        System.out.print(sbLog);
        //FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        if(MySQLBackupController.exportDatabaseTool(esql)){
            //sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finished backup ").append(esql).append("\n");
            MongoDBController.insertDumpLog(NowString.getTime("yyyy-MM-dd"),NowString.getTime("HH:mm:ss"), "Finished backup",linkname, esql);
        }else{
            MongoDBController.insertDumpLog(NowString.getTime("yyyy-MM-dd"),NowString.getTime("HH:mm:ss"), "Backup failed",linkname, esql);
            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Backup failed ").append(esql).append("\n");
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "BackupThread.run()"," Backup failed "+esql);
        }
        System.out.print(sbLog);
        //FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
     }


}
