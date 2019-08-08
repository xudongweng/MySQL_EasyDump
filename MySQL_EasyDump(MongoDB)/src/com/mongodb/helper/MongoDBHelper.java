/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.helper;

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
import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author xudong.weng
 */
public class MongoDBHelper {
    
    //<editor-fold defaultstate="collapsed" desc="插入集合">
    public static int insertCollection(String server,String port,String db,String collection,String user,String pwd){
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
            
            //创建集合 参数为 “集合名称”  
            mongoDatabase.createCollection(collection);
            mongoClient.close();
            System.out.println("Collection created successfully");
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.insertCollection("+server+","+port+","+db+","+collection+","+user+","+pwd+")",e.getMessage());
            return -1;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="插入文档">
    public static int insertDocument(String server,String port,String db,String collection,Document doc,String user,String pwd){
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
            
            //插入文档  
            /** 
            * 1. 创建文档 org.bson.Document 参数为key-value的格式 
            * 2. 创建文档集合List<Document> 
            * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
            * */
            List<Document> documents = new ArrayList<>();  
            documents.add(doc);
            docs.insertMany(documents);
            mongoClient.close();
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.insertDocument("+server+","+port+","+db+","+collection+",doc,"+user+","+pwd+")",e.getMessage());
            return -1;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="删除指定文档">
    public static int delDocument(String server,String port,String db,String collection,String col,String val,String user,String pwd){
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
            
            docs.deleteMany(Filters.eq(col, val));//删除所有符合条件的文档
            mongoClient.close();
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.delDocument("+server+","+port+","+db+","+collection+","+col+","+val+","+user+","+pwd+")",e.getMessage());
            return -1;
        }
        
    }
    //</editor-fold>
     
    //<editor-fold defaultstate="collapsed" desc="插入内嵌文档">
    public static int insertSubDocument(String server,String port,String db,String collection,String col,String val,String doccol,Document doc,String user,String pwd){
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
            //对符合条件的数据进行，内嵌文档的添加
            docs.updateOne(Filters.eq(col, val), new Document("$push", new Document(doccol,doc)));

            mongoClient.close();
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.insertSubDocument("+server+","+port+","+db+","+collection+","+col+","+val+","+doccol+",doc,"+user+","+pwd+")",e.getMessage());
            return -1;
        }
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="批量插入内嵌文档">
    public static int insertSubDocument(String server,String port,String db,String collection,String col,String val,String doccol,List<Document> subdocs,String user,String pwd){
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
            //对符合条件的数据进行，内嵌文档批量添加
            for(int i=0;i<subdocs.size();i++)
                docs.updateOne(Filters.eq(col, val), new Document("$push", new Document(doccol,subdocs.get(i))));

            mongoClient.close();
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.insertSubDocument("+server+","+port+","+db+","+collection+","+col+","+val+","+doccol+",List,"+user+","+pwd+")",e.getMessage());
            return -1;
        }
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="删除指定所有子文档">
    public static int delSubDocumentAll(String server,String port,String db,String collection,String col,String val,String doccol,String user,String pwd){
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
            //对符合条件的数据进行，内嵌文档批量添加
            
            docs.updateOne(Filters.eq(col, val), new Document("$unset", new Document(doccol,0)));

            mongoClient.close();
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.DelSubDocumentAll("+server+","+port+","+db+","+collection+","+col+","+val+","+doccol+","+user+","+pwd+")",e.getMessage());
            return -1;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="读取全部文档">
    public static int readDocuments(String server,String port,String db,String collection,String user,String pwd){
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
            
            for(Document doc:docs.find()){
                System.out.println(doc.toString());
            }
            mongoClient.close();
            return 1;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.readDocument("+server+","+port+","+db+","+collection+","+user+","+pwd+")",e.getMessage());
            return -1;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="获取MongoClient连接对象">
    public static MongoClient getMongoClient(String server,String port,String db,String user,String pwd){
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
            
            return mongoClient;
            /*
            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase(db);  
            
            //获取集合 参数为 “集合名称”  
            MongoCollection<Document> docs=mongoDatabase.getCollection(collection);
            
            for(Document doc:docs.find()){
                System.out.println(doc.toString());
            }
            mongoClient.close();
            */
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.readDocument("+server+","+port+","+db+","+user+","+pwd+")",e.getMessage());
            return null;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="读取最大或最小值">
    //按sortCol列排序，按valColl列取值
    public static Object readDocumentSortOne(String server,String port,String db,String collection,String sortCol,String valCol,int sort,String user,String pwd){
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
            FindIterable<Document> findIterable;
            //按sortCol列排序
            if(sort>=1)
                findIterable= docs.find().sort(new BasicDBObject(sortCol,1)).limit(1);//升序
            else
                findIterable= docs.find().sort(new BasicDBObject(sortCol,-1)).limit(1);//降序
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            Object objmax=null;
            while(mongoCursor.hasNext()){  
               objmax=mongoCursor.next().get(valCol);//按valCol列给数据
            }
            mongoClient.close();
            return objmax;
        } catch (Exception e) { 
            if(mongoClient!=null)
                mongoClient.close();
            System.out.println(e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MongoDBHelper.readDocumentSortOne("+server+","+port+","+db+","+collection+","+sortCol+","+valCol+","+sort+","+user+","+pwd+")",e.getMessage());
            return null;
        }
    }
    //</editor-fold>
    
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
