
package com.xml.helper.file.object;


public class FileObject {
    private String filename;
    private String filesize;
    private String filepath;
    private String filedate;//文件创建时间
    private int backupstate;//文件状态，0没有相名的文件和文件夹，1为存在相名的文件和文件夹，2为有文件夹，没有文件
    public FileObject(){}
    public FileObject(String filename,String filesize,String filepath,String filedate,int backupstate){
        this.filename=filename;
        this.filesize=filesize;
        this.filepath=filepath;
        this.filedate=filedate;
        this.backupstate=backupstate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFiledate() {
        return filedate;
    }

    public void setFiledate(String filedate) {
        this.filedate = filedate;
    }

    public int getBackupstate() {
        return backupstate;
    }

    public void setBackupstate(int backupstate) {
        this.backupstate = backupstate;
    }

}
