
package com.helper.file;

import java.util.HashMap;
import java.util.Map;

public class FileObjDic {
    private Map<String,FileObject> fileobjects=null;
    public void setFileObjects(String filename,FileObject fo){
        if(fileobjects==null){
            fileobjects=new HashMap();
        }
        fileobjects.put(filename, fo);
    }
    
    public Map<String,FileObject> getFileObjects(){
        return this.fileobjects;
    }
    
    public boolean getExist(String filename){
        if(fileobjects==null){
            return false;
        }else{
            return fileobjects.containsKey(filename);
        }
    }
    
    public FileObject getFileObject(String filename){
        if(fileobjects==null){
            return null;
        }else{
            return this.fileobjects.get(filename);
        }
    }
    
    public void removeFileObject(String filename){
        if(fileobjects!=null){
            fileobjects.remove(filename);
        }
    }
    
    public void printFileObjects(){
        for(String key:fileobjects.keySet()){
            FileObject fo=fileobjects.get(key);
            System.out.println(key+"*"+fo.getFilename()+"*"+fo.getFilesize()+"*"+fo.getFiledate()+"*"+fo.getFilepath()+"*"+fo.getBackupstate());
        }
    }
    
    public void clear(){
        this.fileobjects.clear();
    }
}
