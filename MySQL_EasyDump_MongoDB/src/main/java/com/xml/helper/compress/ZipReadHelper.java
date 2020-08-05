/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.helper.compress;

import com.xml.cfg.PublicProperty;
import com.xml.helper.NowString;
import com.xml.helper.file.FileHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

/**
 *
 * @author xudong.weng
 */
public class ZipReadHelper {
    public static ZipFileDic getZipInfo(String srcfile){
        RandomAccessFile randomAccessFile = null;
        IInArchive inArchive = null;
        ZipFileDic zfd=new ZipFileDic();
        try {
            randomAccessFile = new RandomAccessFile(srcfile, "r");
            inArchive = SevenZip.openInArchive(null, // autodetect archive type
                    new RandomAccessFileInStream(randomAccessFile));

            // Getting simple interface of the archive inArchive
            ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

            //System.out.println("   Size   | Compr.Sz. | Filename");
            //System.out.println("----------+-----------+---------");

            for (ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
                //System.out.println(String.format("%9s | %9s | %s", item.getSize(),item.getPackedSize(),item.getPath()));
                String a[]=item.getPath().split(File.separator+File.separator);
                zfd.setZipFile(a[1], new ZipFile(item.getSize(),item.getPackedSize()));
            }
        } catch (FileNotFoundException | SevenZipException e) {
            System.out.println(e.getClass().getName() + ":"+e.getMessage());
            FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"ZipReadHelper.getZipInfo("+srcfile+")",e.getMessage());
        } finally {
            if (inArchive != null) {
                try {
                    inArchive.close();
                } catch (SevenZipException e) {
                    System.out.println(e.getClass().getName() + ":"+e.getMessage());
                    FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"ZipReadHelper.getZipInfo("+srcfile+")",e.getMessage());
                }
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    System.out.println(e.getClass().getName() + ":"+e.getMessage());
                    FileHelper.outputFileFormat(PublicProperty.getErr()+NowString.getTime("yyyy-MM-dd")+PublicProperty.getExtname(),"ZipReadHelper.getZipInfo("+srcfile+")",e.getMessage());
                }
            }
            return zfd;
        }
    }
}
