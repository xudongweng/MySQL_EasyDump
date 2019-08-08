package com;


import com.controller.XMLController;
import com.helper.file.FileHelper;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.cfg.PublicProperty;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import model.url.URL;
import model.url.URLDic;
import view.JFManagement;



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

        //初始化读取mysqlindex.xml，如果不存在则创建
        if(!FileHelper.getFileExist(PublicProperty.getMysqlIndex()+".xml")){
            XMLController.createXML(PublicProperty.getMysqlIndex());
        }
        int errid=XMLController.readURLs(PublicProperty.getMysqlIndex());
        if(errid>0)
        {
            ArrayList list = new ArrayList();
            for (String key :URLDic.getURLs().keySet()) {
                if(FileHelper.getFileExist(key+".xml")){
                    URL url = URLDic.getURLs().get(key);
                    list.add(Integer.valueOf(url.getBakid()));//添加备份序列号
                    System.out.println(key+"*"+url.getUser()+"*"+url.getPwd()+"*"+url.getServer()+"*"+url.getPort());
                    XMLController.readDatabase(key);
                }
            }
            Collections.sort(list);
            System.out.println("Sorting id is "+list.toString());
            PublicProperty.setBakid(Integer.valueOf(list.get(list.size()-1).toString()));
            System.out.println("Backup max id is "+PublicProperty.getBakid());
        }else if(errid<0){
            JOptionPane.showMessageDialog(null, "Read "+PublicProperty.getMysqlIndex()+".xml is error.", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //XmlRpcHandler xrh=new XmlRpcHandler();
        //System.out.println(xrh.getFilePropertyXmlInterface("D:\\MYSQL_BACKUP\\172.16.216.21_3306\\bandai_2_2"));
        
        //LinkDic.printLinks();
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
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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
