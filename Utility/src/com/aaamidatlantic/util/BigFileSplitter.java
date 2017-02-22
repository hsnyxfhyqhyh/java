package com.aaamidatlantic.util;

import java.io.*;
import java.util.Scanner;

public class BigFileSplitter {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if (args.length< 3) {
			System.out.println("invalid parameters");
		}

		String inputFilePath = args[0];	//"C:/Documents and Settings/yhu/Desktop/424prod/esb2/
		String inputFileName = args[1];	//nohup.out
		String outputFileName = args[2];  //1.out
		
		String line = "";

		try {
			FileInputStream fsCounter = new FileInputStream(inputFilePath + inputFileName);
			BufferedReader brCounter = new BufferedReader(new InputStreamReader(fsCounter));
			
			int count = 0;
			
			FileOutputStream fos = new FileOutputStream(inputFilePath + outputFileName);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			while ((line = brCounter.readLine()) != null) {
				count++;
				
				if (count % 10000 == 0 ) {
					System.out.println("reading line " + count);
				}
			}
			
			System.out.println("TOTAL LINE COUNTED = " + count );
			count = 0;
			
			System.out.println("PLEASE INPUT AN NUMBER IN THOUSANDs TO START EXTRACTING: ");
			
			Scanner keyboard = new Scanner(System.in);
			int startLine = keyboard.nextInt() * 1000;
	
			FileInputStream fs = new FileInputStream(inputFilePath + inputFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			while ((line = br.readLine()) != null) {
				count++;
				
				if (count > startLine) {
					String mine = line.trim();
					
					bw.write(mine);
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
