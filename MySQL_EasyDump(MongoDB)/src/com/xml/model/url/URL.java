/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.model.url;



public class URL{
    private String user="root";
    private String pwd="";
    private String server="localhost";
    private String port="3306";
    private String savepath="backup";
    private String bakid="1";
    
    public URL(){}
    
    public URL(String user,String pwd,String server,String port,String savepath,String bakid){
        this.user=user;
        this.pwd=pwd;
        if(!server.equals("")){
            this.server=server;
        }
        if(!port.equals("")){
            this.port=port;
        }
        if(!savepath.equals("")){
            this.savepath=savepath;
        }
        if(!bakid.equals("")){
            this.bakid=bakid;
        }
    }
    
    public String getUser(){
        return this.user;
    }
    public void setUser(String user){
        this.user=user;
    }
    
    public String getPwd(){
        return this.pwd;
    }
    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    
    public String getServer(){
        return this.server;
    }
    public void setServer(String server){
        this.server=server;
    }
    public String getPort(){
        return this.port;
    }
    public void setPort(String port){
        this.port=port;
    }
    
    public String getSavepath(){
        return this.savepath;
    }
    public void setSavepath(String savepath){
        this.savepath=savepath;
    }

    /**
     * @return the bakid
     */
    public String getBakid() {
        return bakid;
    }

    /**
     * @param bakid the bakid to set
     */
    public void setBakid(String bakid) {
        this.bakid = bakid;
    }
}
