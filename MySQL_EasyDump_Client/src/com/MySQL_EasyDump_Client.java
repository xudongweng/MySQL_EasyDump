/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.cfg.PublicProperty;
import com.controller.XMLClientController;
import com.helper.file.FileHelper;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.model.IPPort;
import com.model.IPPortDic;
import com.view.JFManagementClient;

/**
 *
 * @author xudong.weng
 */
public class MySQL_EasyDump_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(!FileHelper.getFileExist("clientconfig.xml")){
            XMLClientController.initXML("clientconfig");
            XMLClientController.addConfig("clientconfig");
        }else if(!XMLClientController.readConfig("clientconfig")){
            return;
        }
        
        //初始化读取mysqlclientindex.xml，如果不存在则创建
        if(!FileHelper.getFileExist(PublicProperty.getMysqlClientIndex()+".xml")){
            XMLClientController.createXML(PublicProperty.getMysqlClientIndex());
        }
        if(XMLClientController.readIPPorts(PublicProperty.getMysqlClientIndex())>0)
        {
            IPPortDic.getIPPorts().keySet().stream().forEach((key) -> {
                IPPort ipport = IPPortDic.getIPPorts().get(key);
                System.out.println(key+"*"+ipport.getIp()+"*"+ipport.getPort());
            });
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
            java.util.logging.Logger.getLogger(JFManagementClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
   
        
 
        
        /* Create and display the form */
     
        java.awt.EventQueue.invokeLater(() -> {
            JFManagementClient jfm=new JFManagementClient();
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            int w=jfm.getWidth();
            int h=jfm.getHeight();
            jfm.setLocation( (width - w) / 2, (height - h) / 2);
            jfm.setVisible(true);
        });
        
    }
    
}
