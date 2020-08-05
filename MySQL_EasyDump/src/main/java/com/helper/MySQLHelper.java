/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper;


import com.cfg.PublicProperty;
import com.sun.rowset.CachedRowSetImpl;
import com.helper.file.FileHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.RowSet;


public class MySQLHelper {
    private final static String drive="com.mysql.jdbc.Driver";
    private final static String urlpart1="jdbc:mysql://";
    private final static String urlpart2=":";
    
    public static String getCon(String user,String pwd,String server,String port){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException cnfEx)
        {
            return cnfEx.toString();
        }
        try{

            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                conn.close();
                return "连接成功";
            }else
                return "连接失败";
        }catch(SQLException sqlEx){
            return sqlEx.toString();
        }
    }
    
    public static RowSet getAllDB(String user,String pwd,String server,String port){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getAllDB("+user+","+pwd+","+server+","+port+")",e.getMessage());
            return null;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement(); //创建语句对象，用以执行sql语言
                ResultSet rs = stmt.executeQuery("show databases"); 
                CachedRowSetImpl rowset = new CachedRowSetImpl();
                rowset.populate(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowset;
            }else
                return null;
            
        }catch(SQLException sqlEx){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getAllDB("+user+","+pwd+","+server+","+port+")",sqlEx.getErrorCode()+sqlEx.getMessage());
            return null;}
    }
   
    
    public static RowSet getDBTables(String user,String pwd,String server,String port,String db){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getDBTables("+user+","+pwd+","+server+","+port+","+db+")",e.getMessage());
            return null;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
                ResultSet rs = stmt.executeQuery("select table_name from information_schema.tables where TABLE_SCHEMA='"+db+"'");
                CachedRowSetImpl rowset = new CachedRowSetImpl();
                rowset.populate(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowset;
            }else
                return null;
            
        }catch(SQLException sqlEx){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getDBTables("+user+","+pwd+","+server+","+port+","+db+")",sqlEx.getErrorCode()+sqlEx.getMessage());
            return null;
        }
    }
    
    public static RowSet getTable(String user,String pwd,String server,String port,String table){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getTable("+user+","+pwd+","+server+","+port+")",e.getMessage());
            return null;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
                ResultSet rs = stmt.executeQuery("select * from "+table);
                CachedRowSetImpl rowset = new CachedRowSetImpl();
                rowset.populate(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowset;
            }else
                return null;
            
        }catch(SQLException sqlEx){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLHelper.getTable("+user+","+pwd+","+server+","+port+")",sqlEx.getMessage());
            return null;
        }
    }
    
    public static RowSet getRowCount(String user,String pwd,String server,String port,String db,String table){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getRowCount("+user+","+pwd+","+server+","+port+","+db+","+table+")",e.getMessage());
            return null;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
             
                ResultSet rs = stmt.executeQuery("select count(*) from "+db+"."+table);
                CachedRowSetImpl rowset = new CachedRowSetImpl();
                rowset.populate(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowset;
            }else
                return null;
            
        }catch(SQLException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getRowCount("+user+","+pwd+","+server+","+port+","+db+","+table+")",e.getMessage());
            return null;}
    }
    
    public static RowSet getSqlData(String user,String pwd,String server,String port,String sql){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.getSqlData("+user+","+pwd+","+server+","+port+","+sql+")",e.getMessage());
            return null;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言

                ResultSet rs = stmt.executeQuery(sql);
                CachedRowSetImpl rowset = new CachedRowSetImpl();
                rowset.populate(rs);
                rs.close();
                stmt.close();
                conn.close();
                return rowset;
            }else
                return null;
            
        }catch(SQLException sqlEx){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLHelper.getSqlData("+user+","+pwd+","+server+","+port+","+sql+")",sqlEx.getErrorCode()+sqlEx.getMessage());
            return null;
        }
    }
    
    public static boolean execSql(String user,String pwd,String server,String port,String sql){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.execSql("+user+","+pwd+","+server+","+port+","+sql+")",e.getMessage());
            return false;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
                stmt.executeQuery(sql);
                stmt.close();
                conn.close();
                return true;
            }else
                return false;
            
        }catch(SQLException sqlEx){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLHelper.execSql("+user+","+pwd+","+server+","+port+","+sql+")",sqlEx.getErrorCode()+sqlEx.getMessage());
            return false;
        }
    }
    
    public static boolean execSql(String user,String pwd,String server,String port,String[] sqlarr){
        try{
            Class.forName(drive).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),
                    "MySQLHelper.execSql("+user+","+pwd+","+server+","+port+","+sqlarr.toString()+")",e.getMessage());
            return false;
        }
        try{
            Connection conn=DriverManager.getConnection(urlpart1+server+urlpart2+port, user, pwd);
            if(!conn.isClosed()){
                Statement stmt = conn.createStatement();//创建语句对象，用以执行sql语言
                for(String sql:sqlarr){
                    stmt.executeQuery(sql);
                }
                stmt.close();
                conn.close();
                return true;
            }else
                return false;
            
        }catch(SQLException sqlEx){
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"MySQLHelper.execSql("+user+","+pwd+","+server+","+port+","+sqlarr.toString()+")",sqlEx.getErrorCode()+sqlEx.getMessage());
            return false;
        }
    }
    
}
