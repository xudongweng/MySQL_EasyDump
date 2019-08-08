package com.xml.model.dump;

import java.util.ArrayList;
import java.util.List;


public class DumpArrObject {
    private final List<String> arrDump=new ArrayList<>();//mysqldump字符串集合
    private final List<String> arrPath=new ArrayList<>();//mysqldump路径字符串集合
    private final List<String> arrDay=new ArrayList<>();//mysqldump备份天数字符串集合
    public void addDump(String dump){
        this.arrDump.add(dump);
    }
    
    public void addPath(String path){
        this.arrPath.add(path);
    }
    
    public void addDay(String day){
        this.arrDay.add(day);
    }
    
    public List<String> getArrDump(){
        return this.arrDump;
    }
    
    public List<String> getArrPath(){
        return this.arrPath;
    }
    
    public List<String> getArrDay() {
        return arrDay;
    }
    
    public void clear(){
        this.arrDump.clear();
        this.arrPath.clear();
        this.arrDay.clear();
    }

    


}
