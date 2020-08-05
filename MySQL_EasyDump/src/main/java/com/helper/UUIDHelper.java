/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper;

import java.util.UUID;


public class UUIDHelper {
    private static String guid="";
    public final static String createUUID(){
        UUID uuid = UUID.randomUUID();
        UUIDHelper.guid=uuid.toString();
        return UUIDHelper.guid;
    }
    
    public static String getUUID(){
        return UUIDHelper.guid;
    }
}
