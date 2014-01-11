package com.kang.ebook;

public class YiDuURLOrganizer {
	public static void main (String args[]){
		String prefixUrl = "http://www.yi-see.com/read_";
		String suffixUrl = "_12692.html";
		int initNumber = 117893;
		for (int i=0; i< 70; i++){
			System.out.println("\"" + prefixUrl + (initNumber + i )+ suffixUrl + "\",");
		}
	}
}
