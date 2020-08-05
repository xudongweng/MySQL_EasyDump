/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.cfg.PublicProperty;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.helper.NowString;
import com.helper.file.FileHelper;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author xudong.weng
 */
public class MongoDBHelper {
    
    //<editor-fold defaultstate="collapsed" desc="去重">
    public static List getDistinctCol(String server,String port,String db,String collection,String valCol,String user,String pwd){
        MongoClient mongoClient=null;
        try {  
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
            //ServerAddress()两个参数分别为 服务器地址 和 端口  
            ServerAddress serverAddress = new ServerAddress(server,Integer.valueOf(port));  
            List<ServerAddress> addrs = new ArrayList<>();  
            addrs.add(serverAddress);  
             
            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
            MongoCredential credential = MongoCredential.createScramSha1Credential(user, db, pwd.toCharArray());  
            List<MongoCredential> credentials = new ArrayList<>();  
            credentials.add(credential);  
            
            //通过连接认证获取MongoDB连接  
            mongoClient = new MongoClient(addrs,credentials);  
            DB mongodb=mongoClient.getDB(db);
            List list=mongodb.getCollection(collection).distinct(valCol);
            
            System.out.println(list.toString());
            
            mongoClient.close();
            return list;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.getDistinctCol("+server+","+port+","+db+","+collection+","+valCol+","+user+","+pwd+")",e.getMessage());
            return null;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="按条件读取文档">
    public static String findDocument(String server,String port,String db,String collection,String col,String val,String user,String pwd){
        MongoClient mongoClient=null;
        try {  
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
            //ServerAddress()两个参数分别为 服务器地址 和 端口  
            ServerAddress serverAddress = new ServerAddress(server,Integer.valueOf(port));  
            List<ServerAddress> addrs = new ArrayList<>();  
            addrs.add(serverAddress);  
             
            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
            MongoCredential credential = MongoCredential.createScramSha1Credential(user, db, pwd.toCharArray());  
            List<MongoCredential> credentials = new ArrayList<>();  
            credentials.add(credential);  
            
            //通过连接认证获取MongoDB连接  
            mongoClient = new MongoClient(addrs,credentials);  
             
            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase(db);  
            
            //获取集合 参数为 “集合名称”  
            MongoCollection<Document> docs=mongoDatabase.getCollection(collection);
            //检索所有文档  
            /** 
            * 1. 获取迭代器FindIterable<Document> 
            * 2. 获取游标MongoCursor<Document> 
            * 3. 通过游标遍历检索出的文档集合 
            * */ 
            /*
            FindIterable<Document> findIterable = docs.find();  
            MongoCursor<Document> mongoCursor = findIterable.iterator();  
            while(mongoCursor.hasNext()){  
               System.out.println(mongoCursor.next());  
            }*/
            //docs.find(Filters.eq("linkname", "172.16.216.4_3306"));
            
            //String strdoc=docs.find(Filters.eq(col, val)).toString();
            //System.out.println(docs.find(Filters.eq(col, val)));
            StringBuilder sb=new StringBuilder();
            for(Document doc:docs.find(Filters.eq(col, val))){
                //System.out.println(doc.toString());
                sb.append(doc.toJson()).append("\n");
            }
            mongoClient.close();
            return sb.toString();
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.findDocument("+server+","+port+","+db+","+collection+","+user+","+pwd+")",e.getMessage());
            return "";
        }
    }

    public static String findDocument(String server,String port,String db,String collection,Document doc,String user,String pwd){
        MongoClient mongoClient=null;
        try {  
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
            //ServerAddress()两个参数分别为 服务器地址 和 端口  
            ServerAddress serverAddress = new ServerAddress(server,Integer.valueOf(port));  
            List<ServerAddress> addrs = new ArrayList<>();  
            addrs.add(serverAddress);  
             
            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
            MongoCredential credential = MongoCredential.createScramSha1Credential(user, db, pwd.toCharArray());  
            List<MongoCredential> credentials = new ArrayList<>();  
            credentials.add(credential);  
            
            //通过连接认证获取MongoDB连接  
            mongoClient = new MongoClient(addrs,credentials);  
             
            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase(db);  
            
            //获取集合 参数为 “集合名称”  
            MongoCollection<Document> docs=mongoDatabase.getCollection(collection);
            //检索所有文档  
            /** 
            * 1. 获取迭代器FindIterable<Document> 
            * 2. 获取游标MongoCursor<Document> 
            * 3. 通过游标遍历检索出的文档集合 
            * */ 
            /*
            FindIterable<Document> findIterable = docs.find();  
            MongoCursor<Document> mongoCursor = findIterable.iterator();  
            while(mongoCursor.hasNext()){  
               System.out.println(mongoCursor.next());  
            }*/
            //docs.find(Filters.eq("linkname", "172.16.216.4_3306"));
            
            //docs.find(Filters.eq("linkname","172.16.216.4_3306")).toString();
            FindIterable<Document> findIterable=docs.find(doc);
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            StringBuilder sb=new StringBuilder();
            while(mongoCursor.hasNext()){  
               //System.out.println(mongoCursor.next());
               sb.append(mongoCursor.next().toJson()).append("\n");
            }
            //System.out.println(strdoc);
            //for(Document doc:docs.find(Filters.eq("linkname","172.16.216.4_3306"))){
            //    System.out.println(doc.toString());
            //}
         
            mongoClient.close();
            return sb.toString();
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.readDocument("+server+","+port+","+db+","+collection+","+user+","+pwd+")",e.getMessage());
            return "";
        }
    }
    //</editor-fold>
}
