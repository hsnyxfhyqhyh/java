package com.ying.bible;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BibleParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String lines = CopyOfFileUtil_EN.getContents(new File("ercv1.txt"));
		
		String lines = FileUtil.getContents(new File("crcv_note_chuang.txt"));
		
		System.out.println("done with chinese");
		
		HashMap hm = FileUtil_EN.getContents(new File("ercv.txt"));
		System.out.println("done with english");
		
		// Get a set of the entries 
		Set set = hm.entrySet(); 
		// Get an iterator 
		Iterator i = set.iterator(); 
		
		// Display elements 
		while(i.hasNext()) { 
			Map.Entry me = (Map.Entry)i.next(); 
			String key = (String) me.getKey();
			String value = (String) me.getValue();
			System.out.println(key + " # " + value);
			lines = lines.replace(key, value);
		} 
		
//		String lines = FileUtil_EN.getContents(new File("ercv.txt"));
//		String pattern = "[A-Za-z0-9]+";
//		lines = lines.replaceAll(pattern, "");
		//{gnaCaA}
		
//		while (lines.indexOf("{gna")>=0){
//			lines = lines.substring(0, lines.indexOf("{gna")) + lines.substring(lines.indexOf("}"));
//		}
		FileUtil.writeFile(lines, "bible_tc.html");
//		FileUtil.writeFile(lines, "bible_en.html");
		
		System.out.println("Done");

	}
}
