package com.kang.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class FileUtil {
	/*
	 * write a string to a file.
	 */
	public static void writeFile(String questions, String filename){
		try {
			Writer output = null;
			File file = new File(filename);
			output = new BufferedWriter(new FileWriter(file));
			output.write(questions);
			output.close();
		} catch (Exception e){
			
		}
	}
	
	/*
	 * Get each line from the text file and add it to the arraylist passed in. 
	 */
	public static ArrayList getContents(ArrayList lines, File aFile) {
		StringBuilder contents = new StringBuilder();
		if (lines == null) {
			lines = new ArrayList();
		}

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop

				while ((line = input.readLine()) != null) {
					lines.add(line);
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lines;
	}
	
	//Remove blank line and line starts with comment symbol 
	public static ArrayList filterLines(ArrayList lines, String commentLineStarter) {
		ArrayList resultLines = new ArrayList();
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i) + ""; 
			if (line.startsWith(commentLineStarter)){
				//Do nothing
			} else if (line.trim().equals("")){
				//Do nothing
			} else {
				resultLines.add(line);
			}
		}
		
		return resultLines;
	}
	
	//print out the lines
	public static void printLines(ArrayList lines) {
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i));
		}
	}
	
}
