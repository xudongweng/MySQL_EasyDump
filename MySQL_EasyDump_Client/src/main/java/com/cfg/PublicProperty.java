
package com.cfg;

import java.io.File;

public class PublicProperty {
    private static String mysqlClientIndex="mysqlclientindex";
    private static String pathmark=File.separator;
    private static String encoding="gb2312";
    private static String err="myerr";
    private static String extname=".log";//日志扩展名
    private static int remoteport=13306;//远程端口
    /**
     * @return the mysqlindex
     */
    public static String getMysqlClientIndex() {
        return mysqlClientIndex;
    }

    /**
     * @param aMysqlClientIndex
     */
    public static void setMysqlClientIndex(String aMysqlClientIndex) {
        mysqlClientIndex = aMysqlClientIndex;
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

    public static String getExtname(){
        return extname;
    }
    
    public static void setExtname(String aExtname){
        extname=aExtname;
    }
    

}
