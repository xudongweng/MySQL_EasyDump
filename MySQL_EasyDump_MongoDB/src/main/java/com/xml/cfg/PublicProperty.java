
package com.xml.cfg;

import java.io.File;

public class PublicProperty {
    private static String mysqlIndex="mysqlindex";
    private static String launch="launch";
    private static String pathmark=File.separator;
    private static String encoding="gb2312";
    private static String err="myerr";
    private static String extname=".log";//日志扩展名
    private static int backuptype=0;//0:数据库备份后直接压缩，1:数据库全部备份后压缩
    private static String bin="";
    private static int bakid=1;//控制linkname备份顺序
    
    
    
    private static int backupthread=3;//备份线程数
    private static int zipthread=3;//压缩线程数
    private static int remoteport=13306;//远程端口
    /**
     * @return the mysqlindex
     */
    public static String getMysqlIndex() {
        return mysqlIndex;
    }

    /**
     * @param aMysqlIndex
     */
    public static void setMysqlIndex(String aMysqlIndex) {
        mysqlIndex = aMysqlIndex;
    }

    /**
     * @return the launch
     */
    public static String getLaunch() {
        return launch;
    }

    /**
     * @param aLaunch the launch to set
     */
    public static void setLaunch(String aLaunch) {
        launch = aLaunch;
    }

    /**
     * @return the pathmark
     */
    public static String getPathmark() {
        return pathmark;
    }

    /**
     * @param aPathmark the pathmark to set
     */
    public static void setPathmark(String aPathmark) {
        pathmark = aPathmark;
    }

    /**
     * @return the err
     */
    public static String getErr() {
        return err;
    }

    /**
     * @param aErr the err to set
     */
    public static void setErr(String aErr) {
        err = aErr;
    }

    /**
     * @return the encoding
     */
    public static String getEncoding() {
        return encoding;
    }

    /**
     * @param aEncoding the encoding to set
     */
    public static void setEncoding(String aEncoding) {
        encoding = aEncoding;
    }

    /**
     * @return the backuptype
     */
    public static int getBackuptype() {
        return backuptype;
    }

    /**
     * @param aBackuptype the backuptype to set
     */
    public static void setBackuptype(int aBackuptype) {
        backuptype = aBackuptype;
    }

    public static String getExtname(){
        return extname;
    }
    
    public static void setExtname(String aExtname){
        extname=aExtname;
    }

    /**
     * @return the bin
     */
    public static String getBin() {
        return bin;
    }

    /**
     * @param aBin the bin to set
     */
    public static void setBin(String aBin) {
        bin = aBin;
    }

    /**
     * @return the backupthread
     */
    public static int getBackupthread() {
        return backupthread;
    }

    /**
     * @param aBackupthread the backupthread to set
     */
    public static void setBackupthread(int aBackupthread) {
        backupthread = aBackupthread;
    }

    /**
     * @return the zipthread
     */
    public static int getZipthread() {
        return zipthread;
    }

    /**
     * @param aZipthread the zipthread to set
     */
    public static void setZipthread(int aZipthread) {
        zipthread = aZipthread;
    }

    /**
     * @return the remoteport
     */
    public static int getRemoteport() {
        return remoteport;
    }

    /**
     * @param aRemoteport the remoteport to set
     */
    public static void setRemoteport(int aRemoteport) {
        remoteport = aRemoteport;
    }

    /**
     * @return the bakid
     */
    public static int getBakid() {
        return bakid;
    }

    /**
     * @param aBakid the bakid to set
     */
    public static void setBakid(int aBakid) {
        bakid = aBakid;
    }

   
}
