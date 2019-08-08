/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.cfg;

/**
 *
 * @author xudong.weng
 */
public class MongoDBProperty {
    private static String server="localhost";
    private static String port="27017";
    private static String db="backuplog";
    private static String user="root";
    private static String pwd="123456";
    private static int mongodb=0;//是否启用mongodb默认为0表示不启用
    private static int importxml=1;//从xml导入mongodb 1为导入，默认在新建mongodb.xml时为1,在运行一次后无论是否导入过都改变为0
    private static int outputxml=0;//从mongodb导出xml
    /**
     * @return the server
     */
    public static String getServer() {
        return server;
    }

    /**
     * @param aServer the server to set
     */
    public static void setServer(String aServer) {
        server = aServer;
    }

    /**
     * @return the port
     */
    public static String getPort() {
        return port;
    }

    /**
     * @param aPort the port to set
     */
    public static void setPort(String aPort) {
        port = aPort;
    }

    /**
     * @return the db
     */
    public static String getDb() {
        return db;
    }

    /**
     * @param aDb the db to set
     */
    public static void setDb(String aDb) {
        db = aDb;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the pwd
     */
    public static String getPwd() {
        return pwd;
    }

    /**
     * @param aPwd the pwd to set
     */
    public static void setPwd(String aPwd) {
        pwd = aPwd;
    }

    /**
     * @return the importxml
     */
    public static int getImportxml() {
        return importxml;
    }

    /**
     * @param aImportxml the importxml to set
     */
    public static void setImportxml(int aImportxml) {
        importxml = aImportxml;
    }

    /**
     * @return the outputxml
     */
    public static int getOutputxml() {
        return outputxml;
    }

    /**
     * @param aOutputxml the outputxml to set
     */
    public static void setOutputxml(int aOutputxml) {
        outputxml = aOutputxml;
    }

    /**
     * @return the mongodb
     */
    public static int getMongodb() {
        return mongodb;
    }

    /**
     * @param aMongodb the mongodb to set
     */
    public static void setMongodb(int aMongodb) {
        mongodb = aMongodb;
    }

    
}
