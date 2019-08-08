/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.model.mysql;

/**
 *
 * @author xudong.weng
 */
public class MySQLObj extends User{
    
    
   // private String user="";
  //  private String password="";
    private String server="";
    private String port="";

    private String db="";
    
    
    public MySQLObj(){}
    public MySQLObj(String user,String password,String server,String port){
        //this.user=user;
        //this.password=password;
        //super.setUser(user);
        //super.setPassword(password);
        super(user,password);
        this.server=server;
        this.port=port;
    }
    
    public MySQLObj(String user,String password,String server,String port,String db){
        //this.user=user;
        //this.password=password;
        //super.setUser(user);
        //super.setPassword(password);
        super(user,password);
        this.server=server;
        this.port=port;
        this.db=db;
    }
    /*

    public String getUser() {
        //return this.user;
        return super.getUser();
    }


    public void setUser(String user) {
        super.setUser(user);
        //this.user = user;
    }


    public String getPassword() {
        //return password;
        return super.getPassword();
    }


    public void setPassword(String password) {
        //this.password = password;
        super.setPassword(password);
    }
    */
    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the db
     */
    public String getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(String db) {
        this.db = db;
    }
    
}
