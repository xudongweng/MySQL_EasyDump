/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper.compress;

/**
 *
 * @author xudong.weng
 */
public class ZipFile {
    
    public ZipFile(){}
    public ZipFile(long size,long compssize){
        this.size=size;
        this.compssize=compssize;
    }
    private long size=0;
    private long compssize=0;
    
    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * @return the compssize
     */
    public long getCompssize() {
        return compssize;
    }

    /**
     * @param compssize the compssize to set
     */
    public void setCompssize(long compssize) {
        this.compssize = compssize;
    }
}
