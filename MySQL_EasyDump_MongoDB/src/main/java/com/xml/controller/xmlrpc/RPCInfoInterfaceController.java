/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.controller.xmlrpc;

import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import java.io.IOException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author xudong.weng
 */
public class RPCInfoInterfaceController {
    // 嵌入式web服务
    private static WebServer web_server;
    
    public static void startRemoteServer() {
        
        web_server = new WebServer(PublicProperty.getRemoteport());
        XmlRpcServer xmlRpcServer = web_server.getXmlRpcServer();

        // 处理程序映射
        PropertyHandlerMapping phm = new PropertyHandlerMapping();

        try {
            /// 为服务添加方法
            // RpcListener服务->XmlRpcHandler.class类
            phm.addHandler("RpcListener", XmlRpcHandler.class);
            web_server.start();
        } catch (XmlRpcException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"RPCInfoInterfaceController.startRemoteServer",e.getMessage());
        }
        xmlRpcServer.setHandlerMapping(phm);
        xmlRpcServer.setHandlerMapping(phm);
        XmlRpcServerConfigImpl config=(XmlRpcServerConfigImpl)xmlRpcServer.getConfig();
        config.setEnabledForExtensions(true);
    }
    
    public static void shutdownRemoteServer() {
        if(web_server!=null)
            web_server.shutdown();
    }
    
    
}
