/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.controller.xmlrpc;

import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import com.xml.helper.file.FileObjDic;
import com.xml.helper.file.object.FileObject;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;
import com.xml.model.url.link.Database;
import com.xml.model.url.link.DatabaseDic;
import com.xml.model.url.link.LinkDic;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author xudong.weng
 */
public class XmlRpcHandler {
    
    // <editor-fold defaultstate="collapsed" desc="获取linkname">
    public String getLinkXmlInterface(String node){//该参数无效
        Element root=new Element("rpclinkinfo");
        Document doc=new Document(root);
        URLDic.getURLs().keySet().stream().map((key) -> {
            Element elurl=new Element("url");
            elurl.setAttribute("linkname", key);
            return elurl;
        }).forEach((elurl) -> {
            root.addContent(elurl);
        });
        ByteArrayOutputStream byteRsp=new ByteArrayOutputStream();
        XMLOutputter xmlOut=new XMLOutputter();
        Format fm = Format.getPrettyFormat();
        fm.setEncoding(PublicProperty.getEncoding());
        xmlOut.setFormat(fm);
        try {
            xmlOut.output(doc, byteRsp);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XmlRpcHandler.getLinkXml("+node+")",e.getMessage());
            return null;
        }

        return byteRsp.toString();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="获取linkname对应所有存储路径">
    public String getLinkNameXmlInterface(String linkname){
        DatabaseDic dbDic=LinkDic.getLink(linkname);
        Element root=new Element("rpclinknameinfo");
        Document doc=new Document(root);
        Map<String,Database> databases=dbDic.getDatabases();
        databases.keySet().stream().map((key) -> {
            //Database db=databases.get(key);
            return key;
        }).map((key) -> {
            Element eldb=new Element("database");
            eldb.setAttribute("db", key);
            eldb.setAttribute("path",URLDic.getURL(linkname).getSavepath()+PublicProperty.getPathmark()+linkname+PublicProperty.getPathmark()+key);
            return eldb;
        }).forEach((eldb) -> {
            root.addContent(eldb);
            //System.out.println(key+"*"+db.getGUID()+"*"+db.getDay());
        });
        ByteArrayOutputStream byteRsp=new ByteArrayOutputStream();
        XMLOutputter xmlOut=new XMLOutputter();
        Format fm = Format.getPrettyFormat();
        fm.setEncoding(PublicProperty.getEncoding());
        xmlOut.setFormat(fm);
        try {
            xmlOut.output(doc, byteRsp);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XmlRpcHandler.getLinkXml("+linkname+")",e.getMessage());
            return null;
        }

        return byteRsp.toString();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="获取所有存储路径">
    public String getLinkPathXmlInterface(String node){//该参数无效
        Element root=new Element("rpclinkpathinfo");
        Document doc=new Document(root);
        List<Map.Entry<String, URL>> listURLs=URLDic.getSortURLs();
        for(int i=0;i<listURLs.size();i++){
            String key=listURLs.get(i).getKey();
            if(FileHelper.getFileExist(key+".xml")){
                URL url =listURLs.get(i).getValue();
                Element elrpclinkname=new Element("rpclink");
                elrpclinkname.setAttribute("linkname",key);
                
                List<Map.Entry<String,Database>> listDatabases=LinkDic.getLink(key).getSortDatabases();
                for(int j=0;j<listDatabases.size();j++){
                    Element eldbpath=new Element("dbpath");
                    //System.out.println(listDatabases.get(j).getKey());
                    eldbpath.setAttribute("path",url.getSavepath()+PublicProperty.getPathmark()+key+
                            PublicProperty.getPathmark()+listDatabases.get(j).getKey());
                    elrpclinkname.addContent(eldbpath);
                }
                
                root.addContent(elrpclinkname);
            }
        }
        /*
        for (String key :URLDic.getURLs().keySet()) {
            if(FileHelper.getFileExist(key+".xml")){
                URL url = URLDic.getURLs().get(key);
                //System.out.println(key+"*"+url.getUser()+"*"+url.getPwd()+"*"+url.getServer()+"*"+url.getPort()+"*"+url.getSavepath());      
                //XMLController.readDatabase(key);
                Element elrpclinkname=new Element("rpclink");
                elrpclinkname.setAttribute("linkname",key);
                
                List<Map.Entry<String,Database>> listDatabases=LinkDic.getLink(key).getSortDatabases();
                for(int j=0;j<listDatabases.size();j++){
                    Element eldbpath=new Element("dbpath");
                    //System.out.println(listDatabases.get(j).getKey());
                    eldbpath.setAttribute("path",url.getSavepath()+PublicProperty.getPathmark()+key+
                            PublicProperty.getPathmark()+listDatabases.get(j).getKey());
                    elrpclinkname.addContent(eldbpath);
                }
                
                root.addContent(elrpclinkname);
            }
        }*/
        ByteArrayOutputStream byteRsp=new ByteArrayOutputStream();
        XMLOutputter xmlOut=new XMLOutputter();
        Format fm = Format.getPrettyFormat();
        fm.setEncoding(PublicProperty.getEncoding());
        xmlOut.setFormat(fm);
        try {
            xmlOut.output(doc, byteRsp);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XmlRpcHandler.getLinkXml("+node+")",e.getMessage());
            return null;
        }

        return byteRsp.toString();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="获取路径下对应文件属性">
    public String getFilePropertyXmlInterface(String path){
        Element root=new Element("rpcfileinfo");
        Document doc=new Document(root);
        FileObjDic fod=FileHelper.getFilesPro(path);
        
        if(fod!=null){
            List<Map.Entry<String, FileObject>> filelist=fod.getSortFileObjects();
            for(int i=0;i<filelist.size();i++){
                FileObject fo= filelist.get(i).getValue();
                Element elf=new Element("file");
                elf.setAttribute("filename", fo.getFilename());
                elf.setAttribute("filesize", fo.getFilesize());
                elf.setAttribute("filedate", fo.getFiledate());
                elf.setAttribute("filedate", fo.getFiledate());
                elf.setAttribute("backupstate", String.valueOf(fo.getBackupstate()));
                root.addContent(elf);
            }
            /*
            for(String key:fod.getFileObjects().keySet()){
                FileObject fo=fod.getFileObject(key);
                //System.out.println(key+"*"+fo.getFilename()+"*"+fo.getFilesize()+"*"+fo.getFiledate()+"*"+fo.getFilepath()+"*"+fo.getBackupstate());
                //加入行数据
                Element elf=new Element("file");
                elf.setAttribute("filename", fo.getFilename());
                elf.setAttribute("filesize", fo.getFilesize());
                elf.setAttribute("filedate", fo.getFiledate());
                elf.setAttribute("filedate", fo.getFiledate());
                elf.setAttribute("backupstate", String.valueOf(fo.getBackupstate()));
                root.addContent(elf);
            }*/
        }
        ByteArrayOutputStream byteRsp=new ByteArrayOutputStream();
        
        XMLOutputter xmlOut=new XMLOutputter();
        Format fm = Format.getPrettyFormat();
        fm.setEncoding(PublicProperty.getEncoding());
        xmlOut.setFormat(fm);
        try {
            xmlOut.output(doc, byteRsp);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"XmlRpcHandler.getFilePropertyXml("+path+")",e.getMessage());
            return null;
        }
        return byteRsp.toString();
    }
    // </editor-fold>
    
    public boolean shutdownServerInterface(String novalue){
        RPCInfoInterfaceController.shutdownRemoteServer();
        return true;
    }
    
    public boolean startServerInterface(String novalue){
        RPCInfoInterfaceController.startRemoteServer();
        return true;
    }
}
