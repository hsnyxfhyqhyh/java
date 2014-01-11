package com.aaamidatlantic.util;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BigFileExtractor {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length< 5) {
			System.out.println("invalid parameters");
		}
//		String startDate = "[2013-04-24";
//		String endDate =  "[2013-04-24 14";
		
		String startDate = args[0];
		String endDate = args[1];
		String inputFilePath = args[2];	//"C:/Documents and Settings/yhu/Desktop/424prod/esb2/
		String inputFileName = args[3];	//nohup.out
		String outputFileName = args[4];  //1.out
		
		boolean startflag = false;
		boolean endflag = false;
		
		String line = "";

		try {
			FileInputStream fs = new FileInputStream(inputFilePath + inputFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			
			int count = 0;
			
			FileOutputStream fos = new FileOutputStream(inputFilePath + outputFileName);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			while ((line = br.readLine()) != null) {
				count++;
				
				if (count % 10000 == 0 ) {
					System.out.println("reading line " + count);
				}
				String mine = line.trim();

				if (mine.startsWith(startDate)) {
					startflag = true;
				} 
				
				if (mine.startsWith(endDate)){
					break;
				}
				
				if (startflag ) {
					bw.write(line);
					bw.newLine();
					bw.flush();
				}
				

			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		
		System.out.println("Done");
	}

}
