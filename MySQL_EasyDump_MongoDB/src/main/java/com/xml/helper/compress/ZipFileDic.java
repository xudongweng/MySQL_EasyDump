/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.helper.compress;

import java.util.HashMap;

/**
 *
 * @author xudong.weng
 */
public class ZipFileDic {
    private HashMap<String,ZipFile> zipfiles=null;
    
    public void setZipFile(String file,ZipFile zipfile){
        if(this.zipfiles==null){
            this.zipfiles=new HashMap();
        }
        this.zipfiles.put(file, zipfile);
    }
    
    public ZipFile getZipFile(String file){
        if(this.zipfiles==null)
            return null;
        else{
            return zipfiles.get(file);
        }
    }
    
    public boolean getExist(String file){
        if(this.zipfiles==null)
            return false;
        else{
            return this.zipfiles.containsKey(file);
        }
    }
    
    public void removeZipFile(String file){
        if(this.zipfiles!=null){
            this.zipfiles.remove(file);
        }
    }
    
    public HashMap<String,ZipFile> getZipFiles(){
        return this.zipfiles;
    }
}
