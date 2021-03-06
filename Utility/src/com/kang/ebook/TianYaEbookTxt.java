package com.kang.ebook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/*
 * please use TianYaEBook to process first, use the html generated by TianYaEbook in the 
 * browser, ctrl-a and copy to save to a txt file  
 * 
 * 
 */
public class TianYaEbookTxt {
	//the value needed to be changed.
	private String filePath = "G:\\JAVA\\Projects\\Money\\EbookGrabber\\新概念水浒.txt";
	private String newfileName = "新概念水浒1.txt";
	private String authorName = "作者：曲昌春";
	
	
	private String authorPrefix = "作者";
	
	public static void main(String[] args) {
		TianYaEbookTxt ebook = new TianYaEbookTxt();
		System.out.println("------Done-----");
	}

	public TianYaEbookTxt() {
		String result = "";
		
		boolean isStartPoint = false;
		
		try {
			StringBuffer fileData = new StringBuffer(1000);
	        BufferedReader reader = new BufferedReader(new FileReader(filePath));
	        
	        String line;
	        while((line = reader.readLine())!= null){
	            String temp = line.trim();
	            if (temp.indexOf(authorName)>=0){
	            	isStartPoint = true;
	            }
	            
	            if (temp.indexOf(authorPrefix)>=0 && temp.indexOf(authorName)<0){
	            	isStartPoint = false;
	            }
	            
	            if (isStartPoint){
	            	fileData.append(line +"\n" );
	            }
	            
	        }
	        reader.close();
	        
	        result = fileData.toString();
		} catch(Exception e){
			
		}

		writeFile(result, newfileName);
	}


	private void writeFile(String content, String filename) {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));

			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}

}
