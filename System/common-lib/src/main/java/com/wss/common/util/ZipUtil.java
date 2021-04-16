package com.wss.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void zipFileTest(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


    static List<String> listFiles(File dir) throws IOException {
        List<String> totalfiles = new ArrayList<String>();
        File[] files = dir.listFiles();

        for (File file : files) {
            //check a file is directory or not
            if (file.isDirectory()) {
                //if a directory then make recursive call to list out subfiles
                listFiles(file);
            } else {
                //if it is a file then add it's absolute path to the list.
                totalfiles.add(file.getAbsolutePath());
            }
        }

        return totalfiles;
    }

    public static void test (File dir, ZipOutputStream zos) throws IOException{

            String dirPath= dir.getAbsolutePath();


//            //create output stream for the zipfile.
//            FileOutputStream fos = new FileOutputStream(zipFile);
//
//            //create zipoutputstream for the outputstream.
//            ZipOutputStream zos = new ZipOutputStream(fos);

            byte[] buffer = new byte[1024];
            int len;

            //for each file in list do zipping process
            for (String path : listFiles(dir)) {
                File ipfile = new File(path);

                //for zipping purpose we need only relative path. we shouldn't consider absolute path.
                //this will give relative path of the file which we are zipping now.
                String zippath = path.substring(dirPath.length() + 1, path.length());

                //we should create zipentry for each file.
                ZipEntry zen = new ZipEntry(zippath);

                //adding to zipoutputstream
                zos.putNextEntry(zen);

                FileInputStream fis = new FileInputStream(ipfile);

                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                //close all IO streams. Or else we may get corrupted zip files
                zos.closeEntry();
                fis.close();

                System.out.println(ipfile.getAbsolutePath()+"is zipped");
            }
            if(listFiles(dir).size() == 0){

            }

//            zos.close();
//            fos.close();


    }

    public static void zipTest(String path, String location,ZipOutputStream zipOut) throws IOException{
        byte[] buf = new byte[1024];
        FileInputStream in = new FileInputStream(location+path);
        // Add ZIP entry to output stream.
        zipOut.putNextEntry(new ZipEntry(path));
        // Transfer bytes from the file to the ZIP file
        int len;
        while ((len = in.read(buf)) > 0) {
            zipOut.write(buf, 0, len);
        }
        // Complete the entry
        zipOut.closeEntry();
        in.close();
    }

}
