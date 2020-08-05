/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.cfg.PublicProperty;
import com.cfg.PublicPropertyTmp;
import com.helper.NowString;
import com.helper.file.FileHelper;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import com.model.IPPort;
import com.model.IPPortDic;
import com.model.MongoObj;
import com.model.MongoDic;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

/**
 *
 * @author xudong.weng
 */
public class XMLClientController {
    
    //<editor-fold defaultstate="collapsed" desc="创建xml">
    //初始配置xml
    public static void initXML(String filename){
        // 创建document
        Document doc = new Document();
        Element root = new Element("config");
        doc.setRootElement(root);
        
        save(doc,filename+".xml");
    }
    
    //索引XML
    public static void createXML(String filename) {
        // 创建document
        Document doc = new Document();
        Element info = new Element("information");
        doc.setRootElement(info);
        
        save(doc,filename+".xml");
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="config">
    //<editor-fold defaultstate="collapsed" desc="添加config.xml节点">
    public static String addConfig(String filename){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();

            // 创建元素init
            Element elInit=new Element("init");
            elInit.setAttribute("mysqlclientindex",PublicProperty.getMysqlClientIndex());
            elInit.setAttribute("err",PublicProperty.getErr());
            elInit.setAttribute("encoding",PublicProperty.getEncoding());

            root.addContent(elInit);

            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.addConfig("+filename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="读取config.xml节点">
    //读取config.xml
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
                if(elInit.getAttributeValue("mysqlclientindex")!=null&&!elInit.getAttributeValue("mysqlclientindex").equals("")){
                    PublicProperty.setMysqlClientIndex(elInit.getAttributeValue("mysqlclientindex"));
                }
                if(elInit.getAttributeValue("err")!=null&&!elInit.getAttributeValue("err").equals("")){
                    PublicProperty.setErr(elInit.getAttributeValue("err"));
                }
                if(elInit.getAttributeValue("encoding")!=null&&!elInit.getAttributeValue("encoding").equals("")){
                    PublicProperty.setEncoding(elInit.getAttributeValue("encoding"));
                }
               
            }
            return true;
        } catch (JDOMException | IOException  e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.readConfig("+filename+")",e.getMessage());
            return false;
        }
    }
    
    public static boolean readConfig(String filename,PublicPropertyTmp ppt) {
        // 使用SAXBuilder解析器解析xml文件
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(filename+".xml");
            //获取mysql
            Element root = doc.getRootElement();
            //获取init子节点
            List<Element> listInit = root.getChildren("init");
            
            for (Element elInit : listInit) {
                if(elInit.getAttributeValue("mysqlindex")!=null&&!elInit.getAttributeValue("mysqlindex").equals("")){
                    ppt.setMysqlClientIndex(elInit.getAttributeValue("mysqlindex"));
                }
                if(elInit.getAttributeValue("err")!=null&&!elInit.getAttributeValue("err").equals("")){
                    ppt.setErr(elInit.getAttributeValue("err"));
                }
                if(elInit.getAttributeValue("encoding")!=null&&!elInit.getAttributeValue("encoding").equals("")){
                    ppt.setEncoding(elInit.getAttributeValue("encoding"));
                }
            }
            return true;
        } catch (JDOMException | IOException  e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.readConfig("+
                    filename+","+ppt.toString()+")",e.getMessage());
            return false;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="更新config.xml节点">
    public static String updateConfig(String filename,PublicPropertyTmp ppt){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();
            
            //获取init子节点
            List<Element> listInit = root.getChildren("init");
            
            for (Element elInit : listInit) {
                    elInit.setAttribute("mysqlindex",ppt.getMysqlIndex());
                    elInit.setAttribute("err",ppt.getErr());
                    elInit.setAttribute("encoding",ppt.getEncoding());
            }
            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.updateConfig("+
                    filename+","+ppt.toString()+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="IPPort">
    //添加mysqclientlindex.xml的ipport节点
    public static String addIPPort(String ip,String port,String filename){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();

            // 创建元素ipport
            Element elipport=new Element("ipport");
            elipport.setAttribute("ip",ip);
            elipport.setAttribute("port",port);
            elipport.setAttribute("mserver","localhost");
            elipport.setAttribute("mport","27017");
            elipport.setAttribute("mdb","backuplog");
            elipport.setAttribute("muser","root");
            elipport.setAttribute("mpwd","123456");
            elipport.setAttribute("mongodb","0");
            root.addContent(elipport);

            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLClientController.addIPPort("+
                    ip+","+port+","+filename+")",e.getMessage());
            return e.toString();
        }
    }
    
    //读取mysqclientlindex.xml
    public static int readIPPorts(String filename) {
        // 使用SAXBuilder解析器解析xml文件
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            //获取mysql
            Element root = doc.getRootElement();
            //获取URL子节点
            List<Element> listIPPort = root.getChildren("ipport");
            for(Element elIPPort:listIPPort)
            {
                IPPortDic.setIPPort(new IPPort(elIPPort.getAttributeValue("ip"),elIPPort.getAttributeValue("port")));
                if(elIPPort.getAttributeValue("mongodb")!=null&&Integer.valueOf(elIPPort.getAttributeValue("mongodb"))==1){
                    MongoObj mongo=new MongoObj(elIPPort.getAttributeValue("mserver"),elIPPort.getAttributeValue("mport"),
                    elIPPort.getAttributeValue("mdb"),elIPPort.getAttributeValue("muser"),elIPPort.getAttributeValue("mpwd"));
                    MongoDic.setMongo(new IPPort(elIPPort.getAttributeValue("ip"),elIPPort.getAttributeValue("port")),
                            mongo);
                }else{
                    MongoDic.setMongo(new IPPort(elIPPort.getAttributeValue("ip"),elIPPort.getAttributeValue("port")),null);
                }
            }
            return listIPPort.size();
        } catch (JDOMException | IOException e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLClientController.readIPPorts("+filename+")",e.getMessage());
            return -1;
        }
    }

    //删除mysqclientlindex.xml的ipport节点
    
    
    public static String delIPPort(String ip,String port,String filename){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();
            
            List<Element> listIPPort = root.getChildren();
            for (Element elIPPort : listIPPort) {
                if (elIPPort.getAttributeValue("ip").equals(ip)&&elIPPort.getAttributeValue("port").equals(port)) {
                    root.removeContent(elIPPort);
                    break;
                }
            }
            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLClientController.delIPPort("+ip+","+port+","+filename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="保存XML">
    //保存XML
    public static String save(Document doc,String xmlfilename) {
        // 将doc对象输出到文件
        try {
            // 创建xml文件输出流
            XMLOutputter xmlopt = new XMLOutputter();
            // 创建文件输出流
            FileWriter writer = new FileWriter(xmlfilename);
            // 指定文档格式
            Format fm = Format.getPrettyFormat();
            //fm.setEncoding("gb2312");
            //fm.setEncoding("utf8");
            fm.setEncoding(PublicProperty.getEncoding());
            xmlopt.setFormat(fm);
            // 将doc写入到指定的文件中
            xmlopt.output(doc, writer);
            writer.close();
            return "";
        } catch (Exception e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.save("+doc.toString()+","+xmlfilename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="解析远程XML字符串">
    /*解析远程XML字符串*/
    public static List<Element> readRPCInfo(String xml){
        //创建一个新的字符串
        StringReader reader=new StringReader(xml);
        InputSource source=new InputSource(reader);
        SAXBuilder sax=new SAXBuilder();
        try {
            Document doc=sax.build(source);
            Element root = doc.getRootElement();       
            return root.getChildren();
        } catch (JDOMException | IOException e) {  
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLClientController.readRPCLink("+xml+")",e.getMessage());
            return null;
        }
    }
    //</editor-fold>
}
