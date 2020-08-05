/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper.file.object;

/**
 *
 * @author xudong.weng
 */
public class DiskObject {
    private String spacepath="";
    private String freespace="";
    private String usablespace="";
    private String totalspace="";
    private String usedspace="";

    /**
     * @return the freespace
     */
    public String getFreespace() {
        return freespace;
    }

    /**
     * @param freespace the freespace to set
     */
    public void setFreespace(String freespace) {
        this.freespace = freespace;
    }

    /**
     * @return the usablespace
     */
    public String getUsablespace() {
        return usablespace;
    }

    /**
     * @param usablespace the usablespace to set
     */
    public void setUsablespace(String usablespace) {
        this.usablespace = usablespace;
    }

    /**
     * @return the totalspace
     */
    public String getTotalspace() {
        return totalspace;
    }

    /**
     * @param totalspace the totalspace to set
     */
    public void setTotalspace(String totalspace) {
        this.totalspace = totalspace;
    }

    /**
     * @return the usedspace
     */
    public String getUsedspace() {
        return usedspace;
    }

    /**
     * @param usedspace the usedspace to set
     */
    public void setUsedspace(String usedspace) {
        this.usedspace = usedspace;
    }

    /**
     * @return the spacepath
     */
    public String getSpacepath() {
        return spacepath;
    }

    /**
     * @param spacepath the spacepath to set
     */
    public void setSpacepath(String spacepath) {
        this.spacepath = spacepath;
    }
}
