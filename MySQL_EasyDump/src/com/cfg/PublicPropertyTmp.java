/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfg;


/**
 *
 * @author xudong.weng
 */
public class PublicPropertyTmp {
    private String mysqlIndex=PublicProperty.getMysqlIndex();
    private String launch=PublicProperty.getLaunch();
    private String encoding=PublicProperty.getEncoding();
    private String err=PublicProperty.getErr();
    private int backuptype=PublicProperty.getBackuptype();//0:数据库备份后直接压缩，1:数据库全部备份后压缩,2:线程备份
    private String bin=PublicProperty.getBin();
    private int backuphread=PublicProperty.getBackupthread();
    private int zipthread=PublicProperty.getZipthread();
    private int remoteport=PublicProperty.getRemoteport();
    /**
     * @return the mysqlIndex
     */
    public String getMysqlIndex() {
        return mysqlIndex;
    }

    /**
     * @param mysqlIndex the mysqlIndex to set
     */
    public void setMysqlIndex(String mysqlIndex) {
        this.mysqlIndex = mysqlIndex;
    }

    /**
     * @return the launch
     */
    public String getLaunch() {
        return launch;
    }

    /**
     * @param launch the launch to set
     */
    public void setLaunch(String launch) {
        this.launch = launch;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * @return the err
     */
    public String getErr() {
        return err;
    }

    /**
     * @param err the err to set
     */
    public void setErr(String err) {
        this.err = err;
    }

    /**
     * @return the backuptype
     */
    public int getBackuptype() {
        return backuptype;
    }

    /**
     * @param backuptype the backuptype to set
     */
    public void setBackuptype(int backuptype) {
        this.backuptype = backuptype;
    }

    /**
     * @return the bin
     */
    public String getBin() {
        return bin;
    }

    /**
     * @param bin the bin to set
     */
    public void setBin(String bin) {
        this.bin = bin;
    }

    /**
     * @return the backuphread
     */
    public int getBackuphread() {
        return backuphread;
    }

    /**
     * @param backuphread the backuphread to set
     */
    public void setBackuphread(int backuphread) {
        this.backuphread = backuphread;
    }

    /**
     * @return the zipthread
     */
    public int getZipthread() {
        return zipthread;
    }

    /**
     * @param zipthread the zipthread to set
     */
    public void setZipthread(int zipthread) {
        this.zipthread = zipthread;
    }

    /**
     * @return the remoteport
     */
    public int getRemoteport() {
        return remoteport;
    }

    /**
     * @param remoteport the remoteport to set
     */
    public void setRemoteport(int remoteport) {
        this.remoteport = remoteport;
    }
}
