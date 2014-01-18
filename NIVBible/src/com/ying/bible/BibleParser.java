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
		FileUtil.getContents(new File("nivCn_old2.txt"));
		
//		// Get a set of the entries 
//		Set set = hm.entrySet(); 
//		
//		// Get an iterator 
//		Iterator i = set.iterator(); 
//		
//		// Display elements 
//		while(i.hasNext()) { 
//			Map.Entry me = (Map.Entry)i.next(); 
//			String key = (String) me.getKey();
//			String value = (String) me.getValue();
//			System.out.println(key + " # " + value);
//			lines = lines.replace(key, value);
//		} 
//		
//		FileUtil.writeFile(lines, "bible_tc.html");
//		
		System.out.println("Done");

	}
}
