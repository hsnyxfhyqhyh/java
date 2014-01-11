package com.ying.bible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CopyOfFileUtil {
	/*
	 * Write something to a file
	 */
	public static void writeFile(String content, String filename){
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));
	
			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}
	
	/*
	 * Get each line from the text file and add it to the arraylist passed in. 
	 */
	public static String getContents(File aFile) {
		StringBuffer contents = new StringBuffer ();
		
		int chapterCounter = 0;
		int verseCounter = 1;
		
		String bookLink = "";

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			int i = 0;
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null && i <99999) {
					if (line.indexOf("<BOOK NAME=\"") >=0){
						
						chapterCounter = 1;
						
						line = line.substring(line.indexOf("<BOOK NAME=\"") + "<BOOK NAME=\"".length());
						String bookName = line.substring(0, line.indexOf("\""));
						
						bookLink = getBookLink(bookName);
//						System.out.println("\n<br><a name='" + bookLink +  "'/>" + bookName + "\n");
//						System.out.println("\n<br><a name='" + bookLink +  chapterCounter + "'/>第" + chapterCounter + "章" + "\n");
						contents.append("\n<br><a name='" + bookLink +  "'/>" + bookName + "\n");
						
						//leave the space for chapter links
						contents.append("\n" + getBookChapterLinks(bookLink));
						contents.append("\n<p><a name='" + bookLink +  chapterCounter + "'/>第" + chapterCounter + "章" + "\n");
						
						
					} else if (line.indexOf("<CHAPTER>") >=0){
						chapterCounter ++;
//						System.out.println("\n第" + chapterCounter + "章" + "\n");
//						System.out.println("\n<br><a name='" + bookLink +  chapterCounter + "'/>第" + chapterCounter + "章" + "\n");
						contents.append("\n<p><a name='" + bookLink +  chapterCounter + "'/>第" + chapterCounter + "章" + "\n");
						 verseCounter = 1;
					} else if (line.indexOf("		<VERSE>")>=0) {
//						System.out.println("<br>" + chapterCounter + ":" + verseCounter + " " +  getVerseContent(line));
						contents.append("\n<br>" + chapterCounter + ":" + verseCounter + " " +  getVerseContent(line));
						verseCounter++;
					}
					
//					contents.append(line);
					i++;
					
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

	private static String getBookLink(String bookName) {
		// TODO Auto-generated method stub
		return "Genesis";
	}
	
	private static String getBookChapterLinks(String bookLink){
		int chapterTotal = 0;
		if (bookLink.equals("Genesis")) {
			chapterTotal= 50;
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i=1; i<=chapterTotal; i++){
			sb.append("<a href='#" + bookLink + i + "'>" + i + "</a>&nbsp;");
		}
		
		return sb.toString();
	}
	

	private static String getVerseContent(String line) {
		String verseText  = "";
		if (line.indexOf("#VERSTEXT#") >= 0) {
			StringBuffer sb = new StringBuffer(); 
			line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
			
			if (line.indexOf("#DESCTEXT#")>0) {
				sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
				line = line.substring(line.indexOf("#DESCTEXT#")+ "#VERSTEXT#".length());
				
				while(line.indexOf("#VERSTEXT#") >= 0) {
					line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
					if (line.indexOf("#DESCTEXT#")>0) {
						sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
						line = line.substring(line.indexOf("#DESCTEXT#")+ "#VERSTEXT#".length());
					}
				}
				
				line = sb.toString();
			} else {
				line = line.substring(0, line.indexOf("</VERSE>"));
			}
			
			verseText = line;
		} else {
			line = line.substring(line.indexOf("<VERSE>") + "<VERSE>".length());
			if (line.indexOf("#DESCTEXT#")>=0) { 
				line = line.substring(0, line.indexOf("#DESCTEXT#"));
			} else {
				line = line.substring(0, line.indexOf("</VERSE>"));
			}
			
			verseText = line;
		}
		return verseText;
	}
}
