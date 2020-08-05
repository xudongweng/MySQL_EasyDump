/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.controller;

import com.mongodb.MongoClient;
import com.mongodb.cfg.MongoDBProperty;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.helper.MongoDBHelper;
import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import com.xml.model.url.URL;
import com.xml.model.url.URLDic;
import com.xml.model.url.link.Database;
import com.xml.model.url.link.DatabaseDic;
import com.xml.model.url.link.LinkDic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 *
 * @author xudong.weng
 */
public class MongoDBController {
    
    //<editor-fold defaultstate="collapsed" desc="初始导入MongoDB">
    public static int initImportMongoDB(){
        if(JOptionPane.showConfirmDialog(null, "Do you want to import config file ?", "Hint",JOptionPane.YES_NO_OPTION)==0){
            //导入mysqlindex.xml配置内容
            for (String linkname :URLDic.getURLs().keySet()) {
                if(FileHelper.getFileExist(linkname+".xml")){

                    URL url = URLDic.getURLs().get(linkname);

                    Document document = new Document("bakid",Integer.valueOf(url.getBakid())).append("linkname", linkname).
                            append("user", url.getUser()).append("pwd", url.getPwd()).append("server", url.getServer()).
                            append("port", url.getPort()).append("savepath", url.getSavepath()).append("date",NowString.getTime("yyyy-MM-dd HH:mm:ss"));
                    //删除存在文档
                    if(MongoDBHelper.delDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),
                            "linkname",linkname,MongoDBProperty.getUser(), MongoDBProperty.getPwd())==-1){
                        JOptionPane.showMessageDialog(null, "Connection is error.", "Error", JOptionPane.ERROR_MESSAGE);
                        return -1;
                    }
                    //插入新文档
                    MongoDBHelper.insertDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),
                            document,MongoDBProperty.getUser(), MongoDBProperty.getPwd());
                    DatabaseDic dc=LinkDic.getLink(linkname);
                    List<Document> listsubdocs=new ArrayList();

                    for(String dbname:dc.getDatabases().keySet()){
                        Database db=dc.getDatabase(dbname);
                        Document subdoc = new Document("db",dbname).append("guid", db.getGUID()).append("day", db.getDay()).
                                append("splite", db.getSplite());
                        listsubdocs.add(subdoc);
                    }
                    //批量嵌入子文档
                    MongoDBHelper.insertSubDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),
                            "linkname",linkname,"database",listsubdocs,MongoDBProperty.getUser(), MongoDBProperty.getPwd());
                }
            }
        }else{
            return 0;
        }
        return 1;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="初始导出MongoDB">
    public static int initOutportMongoDB(){
        MongoClient mongoClient=MongoDBHelper.getMongoClient(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(),MongoDBProperty.getUser(), MongoDBProperty.getPwd());
        try {  
            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoDBProperty.getDb());  
            //获取集合 参数为 “集合名称”  
            MongoCollection<Document> docs=mongoDatabase.getCollection(PublicProperty.getMysqlIndex());
            
            for(Document doc:docs.find()){
                //System.out.println(doc.get("bakid"));
                ArrayList list= (ArrayList) doc.get("database");
                URLDic.setURL(doc.get("linkname").toString(), new URL(doc.get("user").toString(),doc.get("pwd").toString(),
                doc.get("server").toString(),doc.get("port").toString(),doc.get("savepath").toString(),doc.get("bakid").toString()));
                DatabaseDic databasedic=new DatabaseDic();
                if(list!=null){
                    for(int i=0;i<list.size();i++){
                        Document dc=(Document) list.get(i);
                        //System.out.println(dc.get("db"));
                        databasedic.setDatabase(dc.get("db").toString(), new Database(dc.get("guid").toString(), 
                        Integer.valueOf(dc.get("day").toString()),Integer.valueOf(dc.get("splite").toString())));
                    }
                }
                LinkDic.setLink(doc.get("linkname").toString(), databasedic);
                if(list!=null)
                    list.clear();
            }
            mongoClient.close();
            return 1;
        }catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBController.initOutportMongoDB()",e.getMessage());
            return -1;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="设置Bakid">
    public static void setMaxBakid(){
        Object objmax=MongoDBHelper.readDocumentSortOne(MongoDBProperty.getServer(), MongoDBProperty.getPort(),MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),"bakid","bakid",-1, MongoDBProperty.getUser(), MongoDBProperty.getPwd());
        if(objmax!=null)
            PublicProperty.setBakid(Integer.valueOf(MongoDBHelper.readDocumentSortOne(MongoDBProperty.getServer(), MongoDBProperty.getPort(),MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),"bakid","bakid",-1, MongoDBProperty.getUser(), MongoDBProperty.getPwd()).toString()));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="显示MongoDB内容">
    public static int showDocuments(){
        return MongoDBHelper.readDocuments(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="添加Linkname到MongoDB">
    public static int addLinkname(String linkname,URL url){
        Document document = new Document("bakid",Integer.valueOf(url.getBakid())).append("linkname", linkname).
                            append("user", url.getUser()).append("pwd", url.getPwd()).append("server", url.getServer()).
                            append("port", url.getPort()).append("savepath", url.getSavepath()).append("date",NowString.getTime("yyyy-MM-dd HH:mm:ss"));
        //插入新文档
        return MongoDBHelper.insertDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),
                document,MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="更新Linkname到MongoDB">
    //linkname1为原linkname,linkname2为新linkname
    public static int updateLinkname(String linkname1,String linkname2,URL url){
        
        Document document = new Document("bakid",Integer.valueOf(url.getBakid())).append("linkname", linkname2).
                            append("user", url.getUser()).append("pwd", url.getPwd()).append("server", url.getServer()).
                            append("port", url.getPort()).append("savepath", url.getSavepath()).append("date",NowString.getTime("yyyy-MM-dd HH:mm:ss"));
        MongoClient mongoClient=MongoDBHelper.getMongoClient(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(),MongoDBProperty.getUser(), MongoDBProperty.getPwd());
        try {  
            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MongoDBProperty.getDb());  
            //获取集合 参数为 “集合名称”  
            MongoCollection<Document> docs=mongoDatabase.getCollection(PublicProperty.getMysqlIndex());
            
            docs.updateMany(Filters.eq("linkname", linkname1), new Document("$set",document));
            
            mongoClient.close();
            return 1;
        }catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBController.initOutportMongoDB()",e.getMessage());
            return -1;
        }
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="删除Linkname到MongoDB">
    public static int dropLinkname(String linkname){
        return MongoDBHelper.delDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(), "linkname", linkname, MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="删除Linkname指定的database子文档到MongoDB">
    public static int delSubDocumentAll(String linkname){
        return MongoDBHelper.delSubDocumentAll(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),
                            "linkname",linkname,"database",MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="将备份列表作为子文档插入到MongoDB">
    public static int insertSubDocumentAll(String linkname,DatabaseDic dcTemp){
        List<Document> listsubdocs=new ArrayList();
        for(String dbname:dcTemp.getDatabases().keySet()){
            Database db=dcTemp.getDatabase(dbname);
            Document subdoc = new Document("db",dbname).append("guid", db.getGUID()).append("day", db.getDay()).
                                append("splite", db.getSplite());
            DatabaseDic dd=LinkDic.getLink(linkname);
            if(dd==null){
                dd=new DatabaseDic();
                LinkDic.setLink(linkname, dd);
            }
            dd.setDatabase(dbname, new Database(db.getGUID(),db.getDay(),db.getSplite()));
            listsubdocs.add(subdoc);
        }
        return MongoDBHelper.insertSubDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), PublicProperty.getMysqlIndex(),
                            "linkname",linkname,"database",listsubdocs,MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="将日志文档插入到MongoDB">
    public static int insertDumpLog(String date,String time,String status,String linkname,String content){
        //插入新文档
        Document document = new Document("date",date).append("time", time).append("status",status).append("linkname", linkname).append("content", content);
        return MongoDBHelper.insertDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), "launghlog",
                document,MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="将日志去重信息从MongoDB读出">
    public static List getLogInfo(String key){
        List list=MongoDBHelper.getDistinctCol(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), "launghlog",key,MongoDBProperty.getUser(), MongoDBProperty.getPwd());
        
        Collections.sort(list,new Comparator<String>(){
            public int compare(String arg0, String arg1) {  
                return arg1.compareTo(arg0);  
            } 
        });
        //Collections.reverse(list);
        return list;
    }
    //</editor-fold>
    
    public static String getLog(Document doc){
        return MongoDBHelper.findDocument(MongoDBProperty.getServer(), MongoDBProperty.getPort(), MongoDBProperty.getDb(), "launghlog", doc, MongoDBProperty.getUser(), MongoDBProperty.getPwd());
    }
}
