/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xudong.weng
 */
public class IPPortDic {
    private static Map<String,IPPort> ipports=null;
    
    public static void setIPPort(IPPort ipport){
        if(ipports==null){
            ipports=new HashMap();
        }
        ipports.put(ipport.getIp()+"_"+ipport.getPort(), ipport);
    }
    
    
    public static void removeIPPort(String ipportkey){
        if(ipports!=null){
            ipports.remove(ipportkey);
        }
    }
    
    public static Map<String,IPPort> getIPPorts(){
        return IPPortDic.ipports;
    }
    
    public static List<Map.Entry<String, IPPort>> getSortIPPorts(){
        List<Map.Entry<String, IPPort>> infoIds=new ArrayList<>(IPPortDic.ipports.entrySet());
        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, IPPort>>() {   
            @Override
            public int compare(Map.Entry<String, IPPort> o1, Map.Entry<String, IPPort> o2) {      
                //return (o2.getValue() - o1.getValue()); 
                return (o1.getKey()).compareTo(o2.getKey());
            }
        }); 
        return infoIds;
    }
    
    public static IPPort getIPPort(String ipport){
        if(ipports==null){
            return null;
        }else{
            return ipports.get(ipport);
        }
    }

    //打印所有值
    public static void printIPPorts(){
        ipports.keySet().stream().forEach((key) -> {
            IPPort ipport = ipports.get(key);
            System.out.println(key+"*"+ipport.getIp()+"*"+ipport.getPort());
        });
    }
}
