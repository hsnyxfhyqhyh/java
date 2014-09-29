package com.ying.file;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;



public class FilePropagatorBibleNoteData {
	public static void main(String args[]) {
		duplicateFiles("23-0%s.html");
				
		System.out.println("ALL DONE");
		
	}
	
	
	private static void duplicateFiles(String fileNameTemplate) {
		int count = 1;
		
		try {
				for (count = 2; count <= 66; count++) {
					File currentFilepath = new File(String.format(fileNameTemplate, "01"));
					
					String number = padLeadingZero(count);
					
					File newFilePath = new File(String.format(fileNameTemplate, number));

					FileChannel src = new FileInputStream(currentFilepath).getChannel();
					FileChannel dst = new FileOutputStream(newFilePath).getChannel();
					dst.transferFrom(src, 0, src.size());
					src.close();
					dst.close();
				}
		} catch (Exception e) {
		}
	}
	
	private static String padLeadingZero(int i) {
		String s = i + "";
		
		if (s ==null ) return "00";
		
		s = s.trim();
		while (s.length()<2) {
			s = "0" +s;
		}
		
		return s;
	}
}
