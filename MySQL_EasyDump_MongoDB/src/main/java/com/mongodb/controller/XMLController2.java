/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.controller;


import com.mongodb.cfg.MongoDBProperty;
import com.xml.cfg.PublicProperty;
import com.xml.controller.XMLController;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import com.xml.model.url.link.LinkDic;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author xudong.weng
 */
public class XMLController2 extends XMLController{

    //<editor-fold defaultstate="collapsed" desc="添加mongodb.xml节点">
    public static String addConfig(String filename){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();

            // 创建元素init
            Element elInit=new Element("init");
            elInit.setAttribute("server",MongoDBProperty.getServer());
            elInit.setAttribute("port",MongoDBProperty.getPort());
            elInit.setAttribute("db",MongoDBProperty.getDb());
            elInit.setAttribute("user",MongoDBProperty.getUser());
            elInit.setAttribute("pwd",MongoDBProperty.getPwd());
            elInit.setAttribute("importxml",String.valueOf(MongoDBProperty.getImportxml()));
            elInit.setAttribute("outputxml",String.valueOf(MongoDBProperty.getOutputxml()));
            elInit.setAttribute("mongodb",String.valueOf(MongoDBProperty.getMongodb()));
            root.addContent(elInit);

            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController2.addMongoDBConfig("+filename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="读取mongodb.xml节点">
    public static boolean readConfig(String filename) {
        // 使用SAXBuilder解析器解析xml文件
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            //获取mysql
            Element root = doc.getRootElement();
            //获取init子节点
            List<Element> listInit = root.getChildren("init");
            
            for (Element elInit : listInit) {
                if(elInit.getAttributeValue("server")!=null&&!elInit.getAttributeValue("server").equals("")){
                    MongoDBProperty.setServer(elInit.getAttributeValue("server"));
                }
                if(elInit.getAttributeValue("port")!=null&&!elInit.getAttributeValue("port").equals("")){
                    MongoDBProperty.setPort(elInit.getAttributeValue("port"));
                }
                if(elInit.getAttributeValue("db")!=null&&!elInit.getAttributeValue("db").equals("")){
                    MongoDBProperty.setDb(elInit.getAttributeValue("db"));
                }
                if(elInit.getAttributeValue("user")!=null&&!elInit.getAttributeValue("user").equals("")){
                    MongoDBProperty.setUser(elInit.getAttributeValue("user"));
                }
                if(elInit.getAttributeValue("pwd")!=null&&!elInit.getAttributeValue("pwd").equals("")){
                    MongoDBProperty.setPwd(elInit.getAttributeValue("pwd"));
                }
                if(elInit.getAttributeValue("importxml")!=null&&!elInit.getAttributeValue("importxml").equals("")){
                    MongoDBProperty.setImportxml(Integer.valueOf(elInit.getAttributeValue("importxml")));
                }
                if(elInit.getAttributeValue("outputxml")!=null&&!elInit.getAttributeValue("outputxml").equals("")){
                    MongoDBProperty.setOutputxml(Integer.valueOf(elInit.getAttributeValue("outputxml")));
                }
                if(elInit.getAttributeValue("mongodb")!=null&&!elInit.getAttributeValue("mongodb").equals("")){
                    MongoDBProperty.setMongodb(Integer.valueOf(elInit.getAttributeValue("mongodb")));
                }
            }
            return true;
        } catch (JDOMException | IOException  e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController2.readMongoDBConfig("+filename+")",e.getMessage());
            return false;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="更新mongodb.xml节点">
    public static String updateConfig(String filename){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();
            
            //获取init子节点
            List<Element> listInit = root.getChildren("init");
            
            for (Element elInit : listInit) {
                    elInit.setAttribute("importxml",String.valueOf(MongoDBProperty.getImportxml()));
                    elInit.setAttribute("mongodb",String.valueOf(MongoDBProperty.getMongodb()));
            }
            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController2.updateMongoDBConfig("+
                    filename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="删除所有">
    public static void deleteCfgXMLFile(){
        for (String key:LinkDic.getLinks().keySet()) {
            //DatabaseDic dd=LinkDic.getLink(key);
            //dd.clearAll();
            if(FileHelper.getFileExist(key+".xml")){
                FileHelper.deleteFile(key+".xml");
            }
        }
        //URLDic.getURLs().clear();
        FileHelper.deleteFile(PublicProperty.getMysqlIndex()+".xml");
    }
    //</editor-fold>
    
}
