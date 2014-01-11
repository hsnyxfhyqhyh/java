package com.kang.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	public static void main(String args[]){
		String[] filenames = new String[]{"Philly_news20120416.html", "USAToday_20120416sports_stories.html", "USAToday_20120416topstories.html", "USAToday_Money20120416_topstories.html", "USAToday_nba20120416_topstories.html", "USAToday_tech20120416_topstories.html"};
		zipFiles(filenames, "news_20120416.zip");
		System.out.println("Done");
	}
	
	public static void zipFiles(Object[] filenames, String zipFileName){
		// These are the files to include in the ZIP file
		

		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
		    // Create the ZIP file
		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));

		    // Compress the files
		    for (int i=0; i<filenames.length; i++) {
		    	String filename = (String)filenames[i];
		        FileInputStream in = new FileInputStream(filename);

		        // Add ZIP entry to output stream.
		        out.putNextEntry(new ZipEntry(filename));

		        // Transfer bytes from the file to the ZIP file
		        int len;
		        while ((len = in.read(buf)) > 0) {
		            out.write(buf, 0, len);
		        }

		        // Complete the entry
		        out.closeEntry();
		        in.close();
		    }

		    // Complete the ZIP file
		    out.close();
		} catch (IOException e) {
		}
	}
}
