/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.thread;

import com.cfg.PublicProperty;
import com.controller.MySQLBackupController;
import com.helper.NowString;
import com.helper.file.FileHelper;

/**
 *
 * @author xudong.weng
 */
public class BackupThread extends Thread{
    private String esql="";
    public BackupThread(String esql){
        this.esql=esql;
    }
    
    @Override
     public void run() {
        StringBuilder sbLog=new StringBuilder();
        sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Start backup ").append(esql).append("\n");
        System.out.print(sbLog);
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
        if(MySQLBackupController.exportDatabaseTool(esql)){
            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Finished backup ").append(esql).append("\n");
        }else{
            sbLog.append(NowString.getTime("yyyy-MM-dd HH:mm:ss")).append(" Backup failed ").append(esql).append("\n");
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "BackupThread.run()"," Backup failed "+esql);
        }
        System.out.print(sbLog);
        FileHelper.outputFile(PublicProperty.getLaunch()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(), sbLog.toString());
        sbLog.delete(0,sbLog.length());
     }
}
