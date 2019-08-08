/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.helper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NowString {
    public static String getTime(String format){
        //yyyy-MM-dd HH:mm:ss
        SimpleDateFormat df=new SimpleDateFormat(format);
        return df.format(new Date());
    }
}
