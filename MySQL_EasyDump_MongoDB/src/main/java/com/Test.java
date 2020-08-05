/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.mongodb.client.MongoCollection;
import com.mongodb.controller.MongoDBController;
import com.mongodb.helper.MongoDBHelper;
import com.xml.helper.NowString;
import org.bson.Document;
import static org.jdom2.filter.Filters.document;

/**
 *
 * @author xudong.weng
 */
public class Test {
    public static void main(String[] args){
        //MongoDBHelper.insertCollection("10.2.0.235","27017" , "backuplog", "trace", "root", "123456");
        /*
        Document document = new Document("datetime", NowString.getTime("yyyy-MM-dd HH:mm:ss")).  
        append("status", "start backup ").  
        append("database", "ttt").  
        append("source", "mysqldump -h172.16.170.200 --user=sa --password=sa --port=3306 --quick --skip-opt --single-transaction --create-options --default-character-set=utf8 daikin_ob tcicrm_call_log");
        MongoHelper.insertDocument("10.2.0.235","27017" , "backuplog", "trace",document, "root", "123456");
        */
        //MongoDBController.initOutportMongoDB();
        //System.out.println(MongoDBHelper.readDocumentSortOne("10.2.0.235","27017", "backuplog", "mysqlindex","bakid","bakid",-1, "root", "123456"));
        //MongoDBHelper.getDistinctCol("10.2.0.235","27017", "backuplog", "launghlog","linkname","root", "123456");
        Document document = new Document("linkname","172.16.216.4_3306").append("status", "Start backup");
        String s=MongoDBHelper.findDocument("10.2.0.235","27017", "backuplog", "launghlog", document,"root", "123456");
        System.out.println(s);
    }
}
