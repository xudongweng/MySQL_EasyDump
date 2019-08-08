/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.controller;

import com.xml.cfg.PublicProperty;
import com.xml.helper.file.FileHelper;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author xudong.weng
 */
public class InitController {
    
    public static int indexInit(){
        //初始化读取mysqlindex.xml，如果不存在则创建
        if(!FileHelper.getFileExist(PublicProperty.getMysqlIndex()+".xml")){
            XMLController.createXML(PublicProperty.getMysqlIndex());
        }
        int errid=XMLController.readURLs(PublicProperty.getMysqlIndex());
        if(errid>0)
        {
            ArrayList list = new ArrayList();
            //读取Linkname关联xml
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
            if(list.size()>0)
                PublicProperty.setBakid(Integer.valueOf(list.get(list.size()-1).toString()));
           
            System.out.println("Backup max id is "+PublicProperty.getBakid());
            
        }else if(errid<0){
            JOptionPane.showMessageDialog(null, "Read "+PublicProperty.getMysqlIndex()+".xml is error.", "error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return 1;
    }
}
