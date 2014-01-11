package com.ying.util.english;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.kang.util.FileUtil;

public class CompareLists {
	public static void main(String args[]){
		ArrayList w1 = new ArrayList();
		ArrayList w2 = new ArrayList();
		FileUtil.getContents(w1, new File("master-words.txt"));
		FileUtil.getContents(w2, new File("words.txt"));
		
//		printWordsinAButNotInB(w1, w2);
		printWordsinAButNotInB(w2, w1);
		
	}
	
	public static void printWordsinAButNotInB(ArrayList A, ArrayList B){
		for (int i=0; i<A.size(); i++){
			String s1 = (String) A.get(i);
			boolean hasmatch = false;
			
			for (int j=0; j<B.size(); j++){
				if (s1.toUpperCase().equals(((String)B.get(j)).toUpperCase())) {
					hasmatch = true;
					break;
				}
			}
			
			if (!hasmatch){
				System.out.println(s1);
			}
		}
	}
	
	public static void printWords(ArrayList lines){
		for (int j=0; j<lines.size(); j++){
			System.out.println((String)lines.get(j));
		}
	}
}
