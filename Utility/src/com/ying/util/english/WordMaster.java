package com.ying.util.english;

import java.io.File;
import java.util.ArrayList;

import com.kang.util.FileUtil;

public class WordMaster {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList lines = new ArrayList();
		File wf  = new File("words.txt");
		FileUtil.getContents(lines, wf);
		
		int filesize=72;
		
		if (lines.size() >72){
			System.out.println("Please break the file into filegroup, each contains 72 words and less");
			System.exit(-1);
		}
		
		HTML3ColumnFormatter formatter = new HTML3ColumnFormatter ();
		
		int pagesize = formatter.getColumnCount() * formatter.getRowCount();
		
		int count = lines.size();
		
		int page = count / pagesize;
		int remainder = count % pagesize;
		
		String[]  words ;
		if (remainder != 0 ){
			page ++;
			words = new String[page * pagesize];
		} else {
			words = new String[lines.size()];	
		}
				
		
		for (int i=0; i<lines.size(); i++){
				words[i] = (String)lines.get(i);
		}
		
		if (remainder!=0){
			for (int i=lines.size(); i<page*pagesize; i++ ){
				words[i] = " ";
			}
		}
		
		String output = formatter.format(words);
		FileUtil.writeFile(output, "./output/formattedWords.html");
		System.out.println("DONE");
	}
	
	

}
