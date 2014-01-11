package com.ying.util.english;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import com.kang.util.FileUtil;

public class ArticleParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList lines = new ArrayList();
		ArrayList words = new ArrayList();
		
		lines = FileUtil.getContents(lines,new File("article.txt"));
		
//		printWords(lines);
		
		for (int j=0; j<lines.size(); j++){
			
			String line = (String)lines.get(j);
			words = parseLine(line,words);
		}
		
		Collections.sort(words);
		printWords(words);
	}
	
	public static ArrayList parseLine(String line, ArrayList words){
		StringTokenizer st = new StringTokenizer(line, " .,");
		
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			boolean hasmatch = false;
			
			for (int i=0; i<words.size(); i++){
				String word = words.get(i).toString().toUpperCase().trim();
				if (token.toUpperCase().equals(word)) {
					hasmatch = true;
				}
			}
			
			if (!hasmatch) words.add(token) ;
		}
		
		return words;
		
	}
	
	public static void printWords(ArrayList words){
		for (int j=0; j<words.size(); j++){
			System.out.println((String)words.get(j));
		}
	}
}
