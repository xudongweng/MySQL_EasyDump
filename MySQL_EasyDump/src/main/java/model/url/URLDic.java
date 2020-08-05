/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.url;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class URLDic {
    private static Map<String,URL> urls=null;
    
    public static void setURL(String linkname,URL url){
        if(urls==null){
            urls=new HashMap();
        }
        urls.put(linkname, url);
    }
    
    public static void removeURL(String linkname){
        if(urls!=null){
            urls.remove(linkname);
        }
    }
    
    public static Map<String,URL> getURLs(){
        return URLDic.urls;
    }
    
    public static List<Map.Entry<String, URL>> getSortURLs(){
        List<Map.Entry<String, URL>> listsort=new ArrayList<>(URLDic.urls.entrySet());
        //排序
        Collections.sort(listsort, new Comparator<Map.Entry<String, URL>>() {   
            @Override
            public int compare(Map.Entry<String, URL> o1, Map.Entry<String, URL> o2) {      
                //return (o2.getValue() - o1.getValue()); 
                return (o1.getKey()).compareTo(o2.getKey());
            }
        }); 
        return listsort;
    }
    
    public static URL getURL(String linkname){
        if(urls==null){
            return null;
        }else{
            return urls.get(linkname);
        }
    }

    //打印所有值
    public static void printURLs(){
        for(String key : urls.keySet()){
            URL url = urls.get(key);
            System.out.println(key+"*"+url.getUser()+"*"+url.getPwd()+"*"+url.getServer()+"*"+url.getPort());
        }
        /*
        urls.keySet().stream().forEach((key) -> {
            URL url = urls.get(key);
            System.out.println(key+"*"+url.getUser()+"*"+url.getPwd()+"*"+url.getServer()+"*"+url.getPort());
        });*/
    }

}
