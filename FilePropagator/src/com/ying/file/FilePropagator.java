package com.ying.file;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;



public class FilePropagator {
	public static void main(String args[]) {
//		duplicateFiles("以弗所01-01-%s.mp3");
		duplicateFiles("以弗所01-02-%s.mp3");
		duplicateFiles("以弗所01-03-%s.mp3");
		duplicateFiles("以弗所01-04-%s.mp3");
		duplicateFiles("以弗所01-05-%s.mp3");
		duplicateFiles("以弗所01-06-%s.mp3");
	}
	
	
	private static void duplicateFiles(String fileNameTemplate) {
		int count = 1;
		
		try {
				for (count = 1; count <11; count++) {
					File currentFilepath = new File(String.format(fileNameTemplate, ""));
					
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
