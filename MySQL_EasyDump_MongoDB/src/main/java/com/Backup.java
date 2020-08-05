/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.mongodb.cfg.MongoDBProperty;
import com.mongodb.controller.BackupController;
import com.mongodb.controller.MongoDBController;
import com.mongodb.controller.XMLController2;
import com.xml.cfg.PublicProperty;
import com.xml.controller.MySQLBackupController;
import com.xml.controller.XMLController;
import com.xml.controller.ZipFileCheckController;
import com.xml.helper.file.FileHelper;

/**
 *
 * @author xudong.weng
 */
public class Backup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(!XMLController.readConfig("config")){
            return;
        }

        if(FileHelper.getFileExist("mongodb.xml")&&XMLController2.readConfig("mongodb")){
            if(MongoDBProperty.getMongodb()==1){
                if(MongoDBController.showDocuments()>=0){
                    //mongodb读取备份
                    mongoDBBackup();
                }else{
                    MongoDBProperty.setMongodb(0);//确定无法连接mongodb，关闭启用mongodb
                    XMLController2.updateConfig("mongodb");
                    return;
                }
            }
        }
        if(MongoDBProperty.getMongodb()==0){
            xmlBackup();
        }
    }
    
    private static void mongoDBBackup(){
        MongoDBController.initOutportMongoDB();
        //config.xml的backuptype值决定备份类型
        switch (PublicProperty.getBackuptype()) {
            case 0:
                BackupController.backupDTD();
                break;
            case 1:
                BackupController.backupATA();
                break;
            default:
                BackupController.backupThreadAllToAll();
                break;
        }
        //检查备份差异
        ZipFileCheckController.readBackupCheck();
    }
    
    //xml读取备份
    private static void xmlBackup(){
        
        if(!FileHelper.getFileExist(PublicProperty.getMysqlIndex()+".xml")){
            return;
        }
        //config.xml的backuptype值决定备份类型
        switch (PublicProperty.getBackuptype()) {
            case 0:
                MySQLBackupController.backupDTD();
                break;
            case 1:
                MySQLBackupController.backupATA();
                break;
            default:
                MySQLBackupController.backupThreadAllToAll();
                break;
        }
        //检查备份差异
        ZipFileCheckController.readBackupCheck();
    }
}
