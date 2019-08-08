/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.cfg.PublicProperty;
import com.helper.NowString;
import com.helper.file.FileHelper;
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author xudong.weng
 */
public class RPCInfoController {
    public static String getLinkXml(String ip,String port){
        // XmlRpcClient
	XmlRpcClient client = new XmlRpcClient();
        // 客户端配置
	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            // 访问服务器路径、端口
            config.setServerURL(new URL("http://"+ip+":"+port));	//此13306端口正好被内嵌webServer监听；
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getLinkXml("+ip+","+port+")",e.getMessage());
                return null;
        }
        // 客户端设置
	client.setConfig(config);
        // 远程调用
        try {
                return (String) client.execute("RpcListener.getLinkXmlInterface", new Object[] { "" });
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getLinkXml("+ip+","+port+")",e.getMessage());
                return null;
        }
    }
    
    public static String getLinkNameXml(String ip,String port,String linkname){
        // XmlRpcClient
	XmlRpcClient client = new XmlRpcClient();
        // 客户端配置
	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            // 访问服务器路径、端口
            config.setServerURL(new URL("http://"+ip+":"+port));	//此13306端口正好被内嵌webServer监听；
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getLinkNameXml("+ip+","+port+","+linkname+")",e.getMessage());
                return null;
        }
        // 客户端设置
	client.setConfig(config);
        // 远程调用
        try {
                return (String) client.execute("RpcListener.getLinkNameXmlInterface", new Object[] { linkname });
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getLinkNameXml("+ip+","+port+","+linkname+")",e.getMessage());
                return null;
        }
    }
    
    public static String getFilePropertyXml(String ip,String port,String path){
        // XmlRpcClient
	XmlRpcClient client = new XmlRpcClient();
        // 客户端配置
	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            // 访问服务器路径、端口
            config.setServerURL(new URL("http://"+ip+":"+port));	//此13306端口正好被内嵌webServer监听；
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getFilePropertyXml("+ip+","+port+","+path+")",e.getMessage());
                return null;
        }
        // 客户端设置
	client.setConfig(config);
        // 远程调用
        try {
                return (String) client.execute("RpcListener.getFilePropertyXmlInterface", new Object[] { path });
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getFilePropertyXml("+ip+","+port+","+path+")",e.getMessage());
                return null;
        }
    }
    
    public static String getLinkPathXml(String ip,String port){
        // XmlRpcClient
	XmlRpcClient client = new XmlRpcClient();
        // 客户端配置
	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            // 访问服务器路径、端口
            config.setServerURL(new URL("http://"+ip+":"+port));	//此13306端口正好被内嵌webServer监听；
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getLinkPathXml("+ip+","+port+")",e.getMessage());
                return null;
        }
        // 客户端设置
	client.setConfig(config);
        // 远程调用
        try {
                return (String) client.execute("RpcListener.getLinkPathXmlInterface", new Object[] { "" });
        } catch (Exception e) {
                FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "RPCInfoController.getLinkPathXml("+ip+","+port+")",e.getMessage());
                return null;
        }
    }
}
