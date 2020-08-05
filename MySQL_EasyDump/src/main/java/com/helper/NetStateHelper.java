/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper;

import com.cfg.PublicProperty;
import com.helper.file.FileHelper;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author xudong.weng
 */
public class NetStateHelper {
    public static boolean isLoclePortUsing(int port){
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        }catch (Exception e) {
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"NetStateHelper.isPortUsing("+port+")",e.getMessage());
        }
        return flag;
    }
    
    public static boolean isPortUsing(String host,int port) throws UnknownHostException, IOException{
        //boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress,port);
            return true;
        }catch (IOException e){
            return false;
            //FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"NetStateHelper.isPortUsing("+host+","+port+")",e.getMessage());
        }
        //return flag;
    }
}
