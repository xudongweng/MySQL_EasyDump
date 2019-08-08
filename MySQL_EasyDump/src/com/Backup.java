/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.cfg.PublicProperty;
import com.controller.MySQLBackupController;
import com.controller.XMLController;
import com.controller.ZipFileCheckController;
import com.helper.file.FileHelper;

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
