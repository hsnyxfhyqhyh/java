package com.kang.util;
import java.io.*;

public class TestTxt{
	public static void main(String[] args){
		try{
			File read = new File("c:\\1.txt");
			File write = new File("c:\\2.txt");
			BufferedReader br = new BufferedReader(
			new FileReader(read));
			BufferedWriter bw = new BufferedWriter(
			new FileWriter(write));
			String temp = null;
			temp = br.readLine();
			
			while(temp != null){
				bw.write(temp + "\r\n"); 
				temp = br.readLine();
			}
			bw.close();
			br.close();
		}catch(Exception e){			
		}
	}
}