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
    private String mysqlClientIndex=PublicProperty.getMysqlClientIndex();
    private String encoding=PublicProperty.getEncoding();
    private String err=PublicProperty.getErr();

    /**
     * @return the mysqlIndex
     */
    public String getMysqlIndex() {
        return mysqlClientIndex;
    }

    /**
     * @param mysqlClientIndex
     */
    public void setMysqlClientIndex(String mysqlClientIndex) {
        this.mysqlClientIndex = mysqlClientIndex;
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

}
