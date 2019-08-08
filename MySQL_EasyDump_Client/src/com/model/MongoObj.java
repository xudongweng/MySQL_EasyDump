/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author xudong.weng
 */
public class MongoObj {
    private String mserver="localhost";
    private String mport="27017";
    private String mdb="backuplog";
    private String muser="root";
    private String mpwd="123456";

    public MongoObj(String mserver,String mport,String mdb,String muser,String mpwd){
        this.mserver=mserver;
        this.mport=mport;
        this.mdb=mdb;
        this.muser=muser;
        this.mpwd=mpwd;
    }
    /**
     * @return the mserver
     */
    public String getMserver() {
        return mserver;
    }

    /**
     * @param mserver the mserver to set
     */
    public void setMserver(String mserver) {
        this.mserver = mserver;
    }

    /**
     * @return the mport
     */
    public String getMport() {
        return mport;
    }

    /**
     * @param mport the mport to set
     */
    public void setMport(String mport) {
        this.mport = mport;
    }

    /**
     * @return the mdb
     */
    public String getMdb() {
        return mdb;
    }

    /**
     * @param mdb the mdb to set
     */
    public void setMdb(String mdb) {
        this.mdb = mdb;
    }

    /**
     * @return the muser
     */
    public String getMuser() {
        return muser;
    }

    /**
     * @param muser the muser to set
     */
    public void setMuser(String muser) {
        this.muser = muser;
    }

    /**
     * @return the mpwd
     */
    public String getMpwd() {
        return mpwd;
    }

    /**
     * @param mpwd the mpwd to set
     */
    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }
}
