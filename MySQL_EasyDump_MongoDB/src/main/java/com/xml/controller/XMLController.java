/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.controller;

import com.xml.cfg.PublicProperty;
import com.xml.cfg.PublicPropertyTmp;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.xml.model.url.link.uuid.UUIDDic;
import com.xml.model.url.link.uuid.Table;
import com.xml.model.url.link.uuid.TableDic;
import com.xml.model.url.link.Database;
import com.xml.model.url.link.DatabaseDic;
import com.xml.model.url.link.LinkDic;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class XMLController {

    @SuppressWarnings("null")
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
            elInit.setAttribute("mysqlindex",PublicProperty.getMysqlIndex());
            elInit.setAttribute("launch",PublicProperty.getLaunch());
            elInit.setAttribute("err",PublicProperty.getErr());
            elInit.setAttribute("encoding",PublicProperty.getEncoding());
            elInit.setAttribute("backuptype",String.valueOf(PublicProperty.getBackuptype()));
            elInit.setAttribute("bin",PublicProperty.getBin());
            elInit.setAttribute("backupthread",String.valueOf(PublicProperty.getBackupthread()));
            elInit.setAttribute("zipthread",String.valueOf(PublicProperty.getZipthread()));
            elInit.setAttribute("remoteport",String.valueOf(PublicProperty.getRemoteport()));
            root.addContent(elInit);

            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
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
                if(elInit.getAttributeValue("mysqlindex")!=null&&!elInit.getAttributeValue("mysqlindex").equals("")){
                    PublicProperty.setMysqlIndex(elInit.getAttributeValue("mysqlindex"));
                }
                if(elInit.getAttributeValue("launch")!=null&&!elInit.getAttributeValue("launch").equals("")){
                    PublicProperty.setLaunch(elInit.getAttributeValue("launch"));
                }
                if(elInit.getAttributeValue("err")!=null&&!elInit.getAttributeValue("err").equals("")){
                    PublicProperty.setErr(elInit.getAttributeValue("err"));
                }
                if(elInit.getAttributeValue("encoding")!=null&&!elInit.getAttributeValue("encoding").equals("")){
                    PublicProperty.setEncoding(elInit.getAttributeValue("encoding"));
                }
                if(elInit.getAttributeValue("backuptype")!=null&&!elInit.getAttributeValue("backuptype").equals("")){
                    PublicProperty.setBackuptype(Integer.valueOf(elInit.getAttributeValue("backuptype")));
                }
                
                if(elInit.getAttributeValue("bin")!=null&&!elInit.getAttributeValue("bin").equals("")){
                    PublicProperty.setBin(String.valueOf(elInit.getAttributeValue("bin")));
                }
                
                if(elInit.getAttributeValue("backupthread")!=null&&!elInit.getAttributeValue("backupthread").equals("")){
                    PublicProperty.setBackupthread(Integer.valueOf(elInit.getAttributeValue("backupthread")));
                }
                
                if(elInit.getAttributeValue("zipthread")!=null&&!elInit.getAttributeValue("zipthread").equals("")){
                    PublicProperty.setZipthread(Integer.valueOf(elInit.getAttributeValue("zipthread")));
                }
                
                if(elInit.getAttributeValue("remoteport")!=null&&!elInit.getAttributeValue("remoteport").equals("")){
                    PublicProperty.setRemoteport(Integer.valueOf(elInit.getAttributeValue("remoteport")));
                }
            }
            return true;
        } catch (JDOMException | IOException  e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
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
                    ppt.setMysqlIndex(elInit.getAttributeValue("mysqlindex"));
                }
                if(elInit.getAttributeValue("launch")!=null&&!elInit.getAttributeValue("launch").equals("")){
                    ppt.setLaunch(elInit.getAttributeValue("launch"));
                }
                if(elInit.getAttributeValue("err")!=null&&!elInit.getAttributeValue("err").equals("")){
                    ppt.setErr(elInit.getAttributeValue("err"));
                }
                if(elInit.getAttributeValue("encoding")!=null&&!elInit.getAttributeValue("encoding").equals("")){
                    ppt.setEncoding(elInit.getAttributeValue("encoding"));
                }
                if(elInit.getAttributeValue("backuptype")!=null&&!elInit.getAttributeValue("backuptype").equals("")){
                    ppt.setBackuptype(Integer.valueOf(elInit.getAttributeValue("backuptype")));
                }
                
                if(elInit.getAttributeValue("bin")!=null&&!elInit.getAttributeValue("bin").equals("")){
                    ppt.setBin(String.valueOf(elInit.getAttributeValue("bin")));
                }
                
                if(elInit.getAttributeValue("backupthread")!=null&&!elInit.getAttributeValue("backupthread").equals("")){
                    ppt.setBackuphread(Integer.valueOf(elInit.getAttributeValue("backupthread")));
                }
                
                if(elInit.getAttributeValue("zipthread")!=null&&!elInit.getAttributeValue("zipthread").equals("")){
                    ppt.setZipthread(Integer.valueOf(elInit.getAttributeValue("zipthread")));
                }
            }
            return true;
        } catch (JDOMException | IOException  e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
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
                    elInit.setAttribute("launch",ppt.getLaunch());
                    elInit.setAttribute("err",ppt.getErr());
                    elInit.setAttribute("encoding",ppt.getEncoding());
                    elInit.setAttribute("backuptype",String.valueOf(ppt.getBackuptype()));
                    elInit.setAttribute("bin",ppt.getBin());
                    elInit.setAttribute("backupthread",String.valueOf(ppt.getBackuphread()));
                    elInit.setAttribute("zipthread",String.valueOf(ppt.getZipthread()));
                    elInit.setAttribute("remoteport",String.valueOf(ppt.getRemoteport()));
            }
            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.updateConfig("+
                    filename+","+ppt.toString()+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="URL">
    //添加mysqlindex.xml的url节点
    public static String addURL(String linkname,URL url,String filename){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();

            // 创建元素url
            Element elurl=new Element("url");
            elurl.setAttribute("bakid",url.getBakid());
            elurl.setAttribute("linkname",linkname);
            elurl.setAttribute("user",url.getUser());
            elurl.setAttribute("pwd",url.getPwd());
            elurl.setAttribute("server",url.getServer());
            elurl.setAttribute("port",url.getPort());
            elurl.setAttribute("savepath",url.getSavepath());
            root.addContent(elurl);

            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.updateConfig("+
                    linkname+","+url.toString()+","+filename+")",e.getMessage());
            return e.toString();
        }
    }
    
    //读取mysqlindex.xml
    public static int readURLs(String filename) {
        // 使用SAXBuilder解析器解析xml文件
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            //获取mysql
            Element root = doc.getRootElement();
            //获取URL子节点
            List<Element> listUrl = root.getChildren("url");
            
            listUrl.stream().forEach((elUrl) -> {
                URLDic.setURL(elUrl.getAttributeValue("linkname"), 
                        new URL(elUrl.getAttributeValue("user"), elUrl.getAttributeValue("pwd"), elUrl.getAttributeValue("server"), 
                                elUrl.getAttributeValue("port"), elUrl.getAttributeValue("savepath"),elUrl.getAttributeValue("bakid")));
            });
            return listUrl.size();
        } catch (JDOMException | IOException  e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XMLController.readURLs("+filename+")",e.getMessage());
            return -1;
        }
    }
    
    //修改mysqlindex.xml的url节点
    public static String updateURL(String filename,String oldlinkname,String newlinkname,URL url){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();
            
            List<Element> listUrl = root.getChildren("url");
            for (Element elUrl : listUrl) {
                if (elUrl.getAttributeValue("linkname").equals(oldlinkname)) {
                    //info.removeContent(elUrl);
                    elUrl.setAttribute("bakid",url.getBakid());
                    elUrl.setAttribute("linkname",newlinkname);
                    elUrl.setAttribute("user",url.getUser());
                    elUrl.setAttribute("pwd",url.getPwd());
                    elUrl.setAttribute("server",url.getServer());
                    elUrl.setAttribute("port",url.getPort());
                    elUrl.setAttribute("savepath",url.getSavepath());
                    break;
                }
            }
            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.updateURL("+filename+","+oldlinkname+","+newlinkname+","+url+")",e.getMessage());
            return e.toString();
        }
    }
    
    //删除mysqlindex.xml的url节点
    public static String delURL(String linkname,String filename){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(filename+".xml");
            Element root = doc.getRootElement();
            
            List<Element> listUrl = root.getChildren("url");
            for (Element elUrl : listUrl) {
                if (elUrl.getAttributeValue("linkname").equals(linkname)) {
                    root.removeContent(elUrl);
                    break;
                }
            }
            return save(doc,filename+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.delURL("+linkname+","+filename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Link">
    //添加linkname的Link节点
    public static String addLink(String linkname){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(linkname+".xml");
            Element root = doc.getRootElement();
            //info.addContent(new Comment("备份单库，不加tables和procedures，加入后则变为分表备份。day为备份天数，splite数值1为分表。"));
            // 创建元素link
            Element ellink=new Element("link");
            ellink.setAttribute("linkname",linkname);
            root.addContent(ellink);

            return save(doc,linkname+".xml");
            
  
        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.addLink("+linkname+")",e.getMessage());
            return e.toString();
        }
    }
    //添加linkname的linkname节点下的database节点
    public static String addDatabase(String linkname,String dbname,Database db){
        
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(linkname+".xml");
            Element root = doc.getRootElement();
            Element elLink=root.getChild("link");
            // 创建元素database
            Element elDatabase=new Element("database");
            elDatabase.setAttribute("db",dbname);
            elDatabase.setAttribute("guid",db.getGUID());
            elDatabase.setAttribute("day",String.valueOf(db.getDay()));
            elDatabase.setAttribute("splite",String.valueOf(db.getSplite()));//splite为1做分表
            elLink.addContent(elDatabase);

            return save(doc,linkname+".xml");
        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.addDatabase("+linkname+","+dbname+","+db.toString()+")",e.getMessage());
            return e.toString();
        }
    }
    
    //读取link的数据库相关的xml
    public static String readDatabase(String linkname){
        // 使用SAXBuilder解析器解析xml文件
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(linkname+".xml");
            Element root = doc.getRootElement();
            //获取link子节点
            List<Element> listLink = root.getChildren("link");
            for (Element elLink : listLink) {
                //获取database子节点
                List<Element> listdatabase=elLink.getChildren("database");
                DatabaseDic databasedic=new DatabaseDic();
                for(Element eldatabase : listdatabase){
                    //添加databasedic内容
                    //System.out.print(Integer.valueOf(eldatabase.getAttributeValue("splite")));
                    databasedic.setDatabase(eldatabase.getAttributeValue("db"), 
                            new Database(eldatabase.getAttributeValue("guid"),
                                    Integer.valueOf(eldatabase.getAttributeValue("day")),
                                    Integer.valueOf(eldatabase.getAttributeValue("splite"))));
                    /*
                    //添加guiddic内容
                    XMLController.readUUIDDatabase(eldatabase.getAttributeValue("guid"));*/
                }
                LinkDic.setLink(linkname, databasedic);
            }
            return "";
        } catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.readDatabase("+linkname+")",e.getMessage());
            return e.toString();
        }
    }
    
    //删除link的数据库相关xml中的database项
    public static String delDatabase(String linkname,String db){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(linkname+".xml");
            Element root = doc.getRootElement();
            //获取link子节点
            List<Element> listLink = root.getChildren("link");
            for (Element elLink : listLink) {
                if(elLink.getAttributeValue("linkname").equals(linkname)){
                    //获取database子节点
                    List<Element> listdatabase=elLink.getChildren("database");
                    for(Element eldatabase : listdatabase){
                        if(eldatabase.getAttributeValue("db").equals(db)){
                            elLink.removeContent(eldatabase);
                            break;
                        }
                    }
                }
            }
            return save(doc,linkname+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.delDatabase("+linkname+","+db+")",e.getMessage());
            return e.toString();
        }
    }
    
    //修改linkname
    public static String updateLinkname(String linkname,String newlinkname){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc=sb.build(linkname+".xml");
            Element root = doc.getRootElement();
            //获取link子节点
            List<Element> listLink = root.getChildren("link");
            for (Element elLink : listLink) {
                if(elLink.getAttributeValue("linkname").equals(linkname)){
                    //更新linkname
                    elLink.setAttribute("linkname", newlinkname);
                    break;
                }
            }
            return save(doc,linkname+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.updateLinkname("+linkname+","+newlinkname+")",e.getMessage());
            return e.toString();
        }
    }
    
    //批量修改linkname的db
    public static String updateDBs(String linkname,DatabaseDic dctemp){
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc=sb.build(linkname+".xml");
            Element root = doc.getRootElement();
            //获取link子节点
            List<Element> listLink = root.getChildren("link");
            for (Element elLink : listLink) {
                //获取database子节点
                if(elLink.getAttributeValue("linkname").equals(linkname)){
                    //获取database子节点
                    
                    Iterator<Element> listdatabase=elLink.getChildren("database").iterator();
                    //listdatabase.iterator()
                    while(listdatabase.hasNext()){
                    //for(Element eldatabase : listdatabase){
                        Element eldatabase=listdatabase.next();
                        String dbname=eldatabase.getAttributeValue("db");
                        if(dctemp.getExist(dbname)){//检查xml中的备份计划是否存在于现在的备份计划
                            Database db=dctemp.getDatabase(dbname);//获取需要修正备份计划的db
                            if(db.getMark()==1){//mark为1表示备份计划做过修改
                                //修改xml
                                eldatabase.getAttribute("guid").setValue(db.getGUID());
                                eldatabase.getAttribute("day").setValue(String.valueOf(db.getDay()));
                                eldatabase.getAttribute("splite").setValue(String.valueOf(db.getSplite()));
                                //将新的db的备份计划内容加入字典
                                LinkDic.getLink(linkname).setDatabase(dbname, new Database(db.getGUID(),db.getDay(),db.getSplite()));
                                //删除临时db的备份计划
                                dctemp.removeDatabase(dbname);
                            }else{
                                //将新的db的备份计划内容加入字典
                                LinkDic.getLink(linkname).setDatabase(dbname, new Database(db.getGUID(),db.getDay(),db.getSplite()));
                                //删除临时db的备份计划
                                dctemp.removeDatabase(dbname);
                            }
                        }else{
                            listdatabase.remove();//解决java.util.ConcurrentModificationException问题
                            elLink.removeContent(eldatabase);
                        }
                    }
                    
                    
                   if(!LinkDic.getExist(linkname)){//确认是否存在DatabaseDic对象，在第一次建立时，必须。
                       LinkDic.setLink(linkname, new DatabaseDic());
                   }
                    //添加未在link.xml中找到的新的db备份计划
                    for (String dbname:dctemp.getDatabases().keySet()){
                        Element elAddLink=root.getChild("link");
                        Database db=dctemp.getDatabase(dbname);
                        Element elDatabase=new Element("database");
                        elDatabase.setAttribute("db",dbname);
                        elDatabase.setAttribute("guid",db.getGUID());
                        elDatabase.setAttribute("day",String.valueOf(db.getDay()));
                        elDatabase.setAttribute("splite",String.valueOf(db.getSplite()));
                        elAddLink.addContent(elDatabase);
                        LinkDic.getLink(linkname).setDatabase(dbname, new Database(db.getGUID(),db.getDay(),db.getSplite()));
                    }
                    dctemp.clearAll();
                }
            }
            return save(doc,linkname+".xml");

        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.updateDBs("+linkname+","+dctemp.toString()+")",e.getMessage());
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
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.save("+doc.toString()+","+xmlfilename+")",e.getMessage());
            return e.toString();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="以下暂不使用">
    //创建UUID下的database节点
    public static void addUUIDDatabase(String guidname){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(guidname+".xml");
            Element info = doc.getRootElement();

            // 创建元素database
            Element eldatabase=new Element("database");
            eldatabase.setAttribute("guid",guidname);
            info.addContent(eldatabase);

            save(doc,guidname+".xml");
            
        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.addUUIDDatabase("+guidname+")",e.getMessage());
        }
    }
    //创建UUID下的database节点下table节点和datetime属性
    public static void addUUIDTable(String guidname,String table,String datetime){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(guidname+".xml");
            Element info = doc.getRootElement();
            
            Element elDatabase=info.getChild("database");
            
            // 创建元素database
            Element elTable=new Element("table");
            elTable.setText(table);
            elTable.setAttribute("datetime", datetime);
            elDatabase.addContent(elTable);

            save(doc,guidname+".xml");
            
        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.addUUIDTable("+guidname+","+table+","+datetime+")",e.getMessage());
        }
    }
    //删除UUID下的database节点下table节点和datetime属性
    public static void delUUIDTable(String guidname,String table){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(guidname+".xml");
            Element root = doc.getRootElement();
            //获取database子节点
            List<Element> listDatabase=root.getChildren("database");
            for (Element elDatabase : listDatabase) {
                //获取table属性
                List<Element> listTable=elDatabase.getChildren("table");
                
                for(Element elTable : listTable){
                    if(elTable.getText().equals(table)){
                        elDatabase.removeContent(elTable);
                        break;
                    }
                }
            }
            save(doc,guidname+".xml");
            
        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.delUUIDTable("+guidname+","+table+")",e.getMessage());
        }
    }
    //更新UUID下的database节点下table节点的datetime属性
    public static void updateUUIDTableDatetime(String guidname,String table,String datetime){
        SAXBuilder sb = new SAXBuilder();
        
        try {
            Document doc = sb.build(guidname+".xml");
            Element root = doc.getRootElement();
            //获取database子节点
            List<Element> listDatabase=root.getChildren("database");
            for (Element elDatabase : listDatabase) {
                //获取table属性
                List<Element> listTable=elDatabase.getChildren("table");
                
                for(Element elTable : listTable){
                    if(elTable.getText().equals(table)){
                        elTable.setAttribute("datetime", datetime);
                        break;
                    }
                }
            }
            save(doc,guidname+".xml");
            
        }catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.updateUUIDTableDatetime("+guidname+","+table+","+datetime+")",e.getMessage());
        }
    }
    //读取UUID下的database节点
    public static void readUUIDDatabase(String guidname){
        // 使用SAXBuilder解析器解析xml文件
        SAXBuilder sb = new SAXBuilder();
         
        try {
            Document doc = sb.build(guidname+".xml");
            Element root = doc.getRootElement();
            //获取database子节点
            List<Element> listDatabase = root.getChildren("database");
            for (Element elDatabase : listDatabase) {
                //获取table子节点
                List<Element> listtable=elDatabase.getChildren("table");
                TableDic tabledic=new TableDic();
                for(Element eltable : listtable){
                    //添加guiddic内容
                    Table table=new Table();
                    tabledic.setTable(eltable.getText(), table);
                }
                UUIDDic.setUUID(guidname, tabledic);
            }
        } catch (JDOMException | IOException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "XMLController.readUUIDDatabase("+guidname+")",e.getMessage());
        }
    }
    //</editor-fold>

}
