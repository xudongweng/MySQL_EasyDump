/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dump;

import java.io.File;


public class DumpOption {

    /**
     * @return the add_locks
     */
    public String getAdd_locks() {
        return add_locks;
    }

    /**
     * @return the skip_add_locks
     */
    public String getSkip_add_locks() {
        return skip_add_locks;
    }
    private final String add_drop_database="--add-drop-database";
    private final String n="-n";//只导出数据，而不添加CREATE DATABASE 语句。
    
    private final String single_transaction="--single-transaction";
    private final String comments="--comments";//附加注释信息。默认为打开，可以用--skip-comments取消
    private final String default_character_utf8="--default-character-set=utf8";//设置默认字符集，默认值为utf8
    private final String force="--force";//在导出过程中忽略出现的SQL错误。
    private final String opt="--opt";
    private final String skip_opt="--skip-opt";
    private final String xml="--xml";
    private final String quick="--quick";
    private final String create_options="--create-options";
    
    private final String add_drop_table="--add-drop-table";
    private final String skip_extended_insert="--skip-extended-insert";
    private final String c="-c";//使用完整的insert语句(包含列名称)。这么做能提高插入效率，但是可能会受到max_allowed_packet参数的影响而导致插入失败。
    private final String add_locks="--add-locks";//在每个表导出之前增加LOCK TABLES并且之后UNLOCK  TABLE。(默认为打开状态，使用--skip-add-locks取消选项)
    private final String skip_add_locks="--skip-add-locks";
    private final String a="-a";//在CREATE TABLE语句中包括所有MySQL特性选项。(默认为打开状态)
    private final String e="-e";//使用具有多个VALUES列的INSERT语法。这样使导出文件更小，并加速导入时的速度。默认为打开状态，使用--skip-extended-insert取消选项。
    private final String t="-t";//只导出数据，而不添加CREATE TABLE 语句。
    private final String d="-d";//不导出任何数据，只导出数据库表结构。
    
    private final StringBuilder sbDumpModel = new StringBuilder();
    private final StringBuilder sbTemp = new StringBuilder();
    private final StringBuilder sbDumpTemp=new StringBuilder();
    
    public void setDumpModel(String bin,String server,String user,String pwd,String port){
        sbDumpModel.delete(0, sbDumpModel.length());
        sbDumpModel.append(bin).append(File.separator).append("mysqldump").append(" -h").append(server);
        sbDumpModel.append(" --user=").append(user).append(" --password=").append(pwd).append(" --port=").append(port);
        //sbDumpModel.append(" ").append(this.quick).append(" ").append(this.single_transaction).append(" ").append(this.skip_extended_insert).append(" ").append(this.default_character_utf8).append(" ");
        sbDumpModel.append(" ").append(this.skip_add_locks).append(" ").append(this.quick).append(" ").append(this.single_transaction).append(" ").append(this.default_character_utf8).append(" ");
    } 
    
    public void setDumpModel(String server,String user,String pwd,String port){
        sbDumpModel.delete(0, sbDumpModel.length());
        sbDumpModel.append("mysqldump").append(" -h").append(server);
        sbDumpModel.append(" --user=").append(user).append(" --password=").append(pwd).append(" --port=").append(port);
        //sbDumpModel.append(" ").append(this.quick).append(" ").append(this.single_transaction).append(" ").append(this.skip_extended_insert).append(" ").append(this.default_character_utf8).append(" ");
        sbDumpModel.append(" ").append(this.skip_add_locks).append(" ").append(this.quick).append(" ").append(this.single_transaction).append(" ").append(this.default_character_utf8).append(" ");
    }
    
    public String getDumpModel(){
        return this.sbDumpModel.toString();
    }
    
    public String getDumpTemp(){
        return this.sbTemp.toString();
    }
    
    public String getMergeDumpTemp(){
        this.sbDumpTemp.delete(0, sbDumpTemp.length());
        return this.sbDumpTemp.append(this.sbDumpModel).append(this.sbTemp).toString();
    }
    
    public void setDumpDB(String db,String path){
        sbTemp.delete(0, sbTemp.length());
        sbTemp.append("-R ").append(db).append(" --result-file=").append(path).append(db).append(".sql");
    }
    
    public void setDumpTable(String db,String table,String path){
        sbTemp.delete(0, sbTemp.length());
        sbTemp.append(db).append(" ").append(table).append(" --result-file=").append(path).append(table).append(".sql");
    }
    
    public void setDumpProcedure(String db,String path){
        sbTemp.delete(0, sbTemp.length());
        sbTemp.append(db).append(" ").append("--force -n -t -d -R").append(" --result-file=").append(path).append("procedures").append(".sql");
    }

    /**
     * @return the skipextendedinsert
     */
    public String getSkip_extended_insert() {
        return skip_extended_insert;
    }
    
}
