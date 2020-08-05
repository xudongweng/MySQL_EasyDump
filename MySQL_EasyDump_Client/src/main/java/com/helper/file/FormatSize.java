/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper.file;

/**
 *
 * @author xudong.weng
 */
public class FormatSize {
    public static String getSize(long size){
        if(size<1024){
            return String.valueOf(size) + "B";
        }else if(size<1024*1024){
            return String.format("%.2f",  (double)size/(double)1024) + "KB";
        }else if(size<1024*1024*1024){
            return String.format("%.2f",(double)size/(double)1024/(double)1024) + "MB";
        }else{
            return String.format("%.2f",(double)size/(double)1024/(double)1024/(double)1024) + "GB";
        }
    }
}
