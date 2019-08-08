
package com.helper.file;

import com.helper.file.object.FileObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
    
    public List<Map.Entry<String, FileObject>> getSortFileObjects(){
        List<Map.Entry<String, FileObject>> infoIds=new ArrayList<Map.Entry<String,FileObject>>(fileobjects.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, FileObject>>() {   
            @Override
            public int compare(Map.Entry<String, FileObject> o1, Map.Entry<String, FileObject> o2) {      
                return (o1.getKey()).compareTo(o2.getKey());
            }
        }); 
        return infoIds;
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
