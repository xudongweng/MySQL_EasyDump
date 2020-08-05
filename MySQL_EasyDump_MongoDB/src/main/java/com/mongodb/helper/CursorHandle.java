/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.helper;

import com.mongodb.Cursor;

/**
 *
 * @author xudong.weng
 */
public interface CursorHandle {
    public void handle(Cursor cursor);
}
