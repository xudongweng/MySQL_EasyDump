/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.xml.cfg.PublicProperty;
import com.xml.controller.XMLController;
import com.xml.controller.xmlrpc.RPCInfoInterfaceController;
import com.xml.helper.file.FileHelper;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;

/**
 *
 * @author xudong.weng
 */
public class StartServer {
    public static void main(String[] args) {
        if(!FileHelper.getFileExist("config.xml")){
            System.out.println("config.xml is not exist.");
            return;
        }else if(!XMLController.readConfig("config")){
            return;
        }

        //初始化读取mysqlindex.xml，如果不存在则创建
        if(!FileHelper.getFileExist(PublicProperty.getMysqlIndex()+".xml")){
            XMLController.createXML(PublicProperty.getMysqlIndex());
        }
        if(XMLController.readURLs(PublicProperty.getMysqlIndex())>0)
        {
            URLDic.getURLs().keySet().stream().filter((key) -> (FileHelper.getFileExist(key+".xml"))).map((key) -> {
                URL url = URLDic.getURLs().get(key);
                System.out.println(key+"*"+url.getUser()+"*"+url.getPwd()+"*"+url.getServer()+"*"+url.getPort());
                return key;
            }).forEach((key) -> {
                XMLController.readDatabase(key);
            });
            /*
            for (String key :URLDic.getURLs().keySet()) {
                if(FileHelper.getFileExist(key+".xml")){
                    URL url = URLDic.getURLs().get(key);
                    System.out.println(key+"*"+url.getUser()+"*"+url.getPwd()+"*"+url.getServer()+"*"+url.getPort());
                    XMLController.readDatabase(key);
                }
            }
            */
        }
        RPCInfoInterfaceController.startRemoteServer();
    }
}
