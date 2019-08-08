/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.xml.cfg.PublicProperty;
import com.xml.controller.XMLController;
import com.xml.helper.file.FileHelper;
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author xudong.weng
 */
public class ShutdownServer {
    public static void main(String[] args) {
        
        if(!FileHelper.getFileExist("config.xml")){
            System.out.println("config.xml is not exist.");
            return;
        }else if(!XMLController.readConfig("config")){
            System.out.println("config.xml read error.");
            return;
        }
        
        // XmlRpcClient
        XmlRpcClient client = new XmlRpcClient();

        // 客户端配置
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
                // 访问服务器路径、端口
            config.setServerURL(new URL("http://127.0.0.1:"+PublicProperty.getRemoteport()));	//此10080端口正好被内嵌webServer监听；
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // 客户端设置
        client.setConfig(config);
        // 远程调用
        try {
            client.execute("RpcListener.shutdownServerInterface", new Object[] { "" });
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
        }
    }
}
