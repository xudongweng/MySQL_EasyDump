package com;


import com.mongodb.cfg.MongoDBProperty;
import com.xml.controller.InitController;
import com.mongodb.controller.MongoDBController;
import com.mongodb.controller.XMLController2;
import com.mongodb.view.JFManagements;
import com.xml.controller.XMLController;
import com.xml.helper.file.FileHelper;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import com.xml.view.JFManagement;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xudong.weng
 */
public class MySQL_EasyDump {

    
    public static void main(String[] args) {
        
        if(!FileHelper.getFileExist("config.xml")){
            XMLController.initXML("config");
            XMLController.addConfig("config");
        }else if(!XMLController.readConfig("config")){
            return;
        }
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
        /* Create and display the form */
     
        java.awt.EventQueue.invokeLater(() -> {
            //创建mongodb.xml
            if(!FileHelper.getFileExist("mongodb.xml")){
                XMLController2.createXML("mongodb");
                XMLController2.addConfig("mongodb");
            }
            XMLController2.readConfig("mongodb");//读取mongodb.xml配置文件
            if(MongoDBProperty.getMongodb()==1){//确认是否启用mongodb
                if(MongoDBProperty.getImportxml()==1){ //确定从xml中导入所有配置到mongodb
                    if(InitController.indexInit()>=0){ //读取Linkname关联xml
                        if(MongoDBController.initImportMongoDB()<0){//导入mongodb是否成功
                            MongoDBProperty.setMongodb(0);//确定无法连接mongodb，关闭启用mongodb
                        }
                    }
                    MongoDBProperty.setImportxml(0);//关闭导入mongodb功能
                    //更新mongodb.xml配置文件状态
                    XMLController2.updateConfig("mongodb");
                    
                }else if(MongoDBController.showDocuments()>=0){//显示所有配置文档集，用以确认是否连接mongodb是否成功
                    MongoDBController.initOutportMongoDB();
                    MongoDBController.setMaxBakid();
                }else{
                    MongoDBProperty.setMongodb(0);//确定无法连接mongodb，关闭启用mongodb
                    XMLController2.updateConfig("mongodb");
                    JOptionPane.showMessageDialog(null, "MongoDB connection is error.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                JFManagements jfms=new JFManagements();
                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension screenSize = kit.getScreenSize();
                int width = (int) screenSize.getWidth();
                int height = (int) screenSize.getHeight();
                int w=jfms.getWidth();
                int h=jfms.getHeight();
                jfms.setLocation( (width - w) / 2, (height - h) / 2);
                jfms.setVisible(true);
            }else if(InitController.indexInit()>=0){
                JFManagement jfm=new JFManagement();
                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension screenSize = kit.getScreenSize();
                int width = (int) screenSize.getWidth();
                int height = (int) screenSize.getHeight();
                int w=jfm.getWidth();
                int h=jfm.getHeight();
                jfm.setLocation( (width - w) / 2, (height - h) / 2);
                jfm.setVisible(true);
            }
        });
    }
    
}
