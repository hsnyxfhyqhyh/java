package com.kang.ebook;

public class JingDongURLOrganizer {
	public static void main (String args[]){
		String prefixUrl = "http://bible.kyhs.me/chajing/Old%20Testament/24Jer/24DT";
		String suffixUrl = ".htm";
		int initNumber = 0;
		for (int i=0; i< 60; i++){
			System.out.println("\"" + prefixUrl + paddingLeadingzeros (2, initNumber + i )+ suffixUrl + "\",");
		}
	}
	
	private static String paddingLeadingzeros(int length, int number) { 
		
		 String n = number + "";
		 while (n.length() < length) {
			 n = "0" + n;
		 }
		
		 return n;
	}
}
