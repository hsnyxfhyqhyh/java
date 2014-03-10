package com.ying.file;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;



public class FilePropagator {
	public static void main(String args[]) {
		duplicateFiles("%s01-13-%s.mp3");
		duplicateFiles("%s01-14-%s.mp3");
		duplicateFiles("%s01-15-%s.mp3");
		duplicateFiles("%s01-16-%s.mp3");
		duplicateFiles("%s01-17-%s.mp3");
		duplicateFiles("%s01-18-%s.mp3");
		duplicateFiles("%s01-19-%s.mp3");
		duplicateFiles("%s01-20-%s.mp3");
		duplicateFiles("%s01-21-%s.mp3");
		duplicateFiles("%s01-22-%s.mp3");
		duplicateFiles("%s01-23-%s.mp3");
		
		System.out.println("ALL DONE");
		
	}
	
	
	private static void duplicateFiles(String fileNameTemplate) {
		int count = 1;
		
		try {
				for (count = 1; count <11; count++) {
					File currentFilepath = new File(String.format(fileNameTemplate, "以弗所", ""));
					
					String number = padLeadingZero(count);
					
					File newFilePath = new File(String.format(fileNameTemplate, "以弗所", number));

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
