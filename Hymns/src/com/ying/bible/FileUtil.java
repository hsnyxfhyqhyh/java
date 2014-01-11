package com.ying.bible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class FileUtil {
	/*
	 * Write something to a file
	 */
	public static void writeFile(String content, String filename){
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));
	
			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}
	
	/*
	 * Get each line from the text file and add it to the arraylist passed in. 
	 */
	public static String getContents(File aFile) {
		boolean isFirst =true;
		boolean isFu = false;
		//isSupplement
		int songLineCounter = 1;
		
		StringBuffer contents = new StringBuffer ();
		
		//html header
		contents.append(getHtmlPrefix());
		
		contents.append(getHtmlSong50Groups());
		contents.append(getKindleMobiPageBreak());
		
		contents.append(getIntroduction());
		contents.append(getKindleMobiPageBreak());
		
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			int i = 0;
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null && i <100000) {
					if (line.indexOf("cTitle")>=0){
						continue;
					} else if (line.indexOf("c0")>=0){
						if (!isFirst){
							contents.append(getKindleMobiPageBreak());
						}

						isFirst = false;
						
						songLineCounter = 1;
						
						String songNumber = line.substring(2);
						contents.append(getHtmlParagraphBreak());
						if (isPlaceForHtmlSongGroupLinks(songNumber)){
//							contents.append(getKindleMobiPageBreak());
							contents.append("<a id='g" + songNumber + "'>" + getHtmlLineBreak());
							contents.append(getHtmlSongGroups(songNumber));
							contents.append(getKindleMobiPageBreak());
						}
						
						contents.append(getHtmlParagraphBreak() + "\n");
						contents.append("<a id='song" + songNumber + "'>" + getHtmlLineBreak());
						contents.append("<a href='#start'>" + songNumber + "</a>" + getHtmlLineBreak());
					} else if (line.indexOf("cf0")>=0){
						contents.append(getKindleMobiPageBreak());
						
						songLineCounter = 1;
						isFu = true;
						
						String songNumber = line.substring(5);
						contents.append(getHtmlParagraphBreak());
						
						contents.append("<a id='fu" + songNumber + "'>" + getHtmlLineBreak());
						contents.append("<a href='#start'>附" + songNumber + "</a>" + getHtmlLineBreak());
						
					} else if (line.indexOf("cs")>=0){
						if (!isFirst){
							contents.append(getKindleMobiPageBreak());
						}

						songLineCounter = 1;
						
						String songNumber = line.substring(2);
						
						contents.append(getHtmlParagraphBreak());
						
						if (isPlaceForHtmlSupplementSongGroupLinks(songNumber)){
//							contents.append(getKindleMobiPageBreak());
							contents.append("<a id='sg" + songNumber + "'>" + getHtmlLineBreak());
							contents.append(getHtmlSupplementSongGroups(songNumber));
							contents.append(getKindleMobiPageBreak());
						}
						
						contents.append(getHtmlParagraphBreak() + "\n");
						contents.append("<a id='songcs" + songNumber + "'>" + getHtmlLineBreak());
						contents.append("<a href='#start'>" + songNumber + "</a>" + getHtmlLineBreak());
						
					} else if (!line.trim().equals("")){
						if (!isFu) {
							if (songLineCounter == 1){
								contents.append("<b>" + line + "</b>" + getHtmlLineBreak());
								songLineCounter ++;
							} else if (songLineCounter == 2){
								contents.append("<I><font size='2'>" + line + "</font></I>" + getHtmlLineBreak());
								songLineCounter ++;
							} else {
								contents.append(line + getHtmlLineBreak());
							}
						} else {
							if (songLineCounter == 1){
								contents.append("<b>" + line + "</b>" + getHtmlLineBreak());
								songLineCounter ++;
							} else if (songLineCounter == 2){
								contents.append("<I><font size='2'>" + line + "</font></I>" + getHtmlLineBreak());
								songLineCounter ++;
							} else {
								contents.append(line + getHtmlLineBreak());
							}
						}
					}	

					i++;
					
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//html footer
		contents.append(getHtmlSuffix());
		return contents.toString();
	}

	

	private static String getHtmlSongGroups(String songNumber) {
		int start = Integer.parseInt(songNumber);
		int columns = 6; 
		int counter = 0;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n<a href='#start'>INDEX</a><br>");
		
		for (int i=start; i <start + 50; i++ ) {
			counter++;
			
			if (i==781){
//				sb.append();
				sb.append("\n<br>&nbsp;<br><a href='#fu1'>附1</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append("\n<a href='#fu2'>附2</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append("\n<a href='#fu3'>附3</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append("\n<a href='#fu4'>附4</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append("\n<a href='#fu5'>附5</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append("\n<a href='#fu6'>附6</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				break;
			}
			
			if ((counter % columns) == 1){
				sb.append("<br>&nbsp;<br>");
			}
			
			sb.append("\n<a href='#song" + padStringAtStart(i+"",3, "0") + "'>" + padStringAtStart(i+"",3, "0") + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return sb.toString();
	}

	private static boolean isPlaceForHtmlSongGroupLinks(String songNumber) {
		if (songNumber.equals("001") ||
				songNumber.equals("051") || 
				songNumber.equals("101") || 
				songNumber.equals("151") || 
				songNumber.equals("201") || 
				songNumber.equals("251") || 
				songNumber.equals("301") || 
				songNumber.equals("351") || 
				songNumber.equals("401") || 
				songNumber.equals("451") ||
				songNumber.equals("501") ||
				songNumber.equals("551") ||
				songNumber.equals("601") ||
				songNumber.equals("651") ||
				songNumber.equals("701") ||
				songNumber.equals("751") ){
			return true;
		}
		return false;
	}
	
	private static String getHtmlSupplementSongGroups(String songNumber) {
		
		int start = Integer.parseInt(songNumber);
		
		int stopper = 0;
		if (start==1) stopper = 28;
		if (start==101) stopper = 138;
		if (start==201) stopper = 248;
		if (start==301) stopper = 330;
		if (start==401) stopper = 430;
		if (start==501) stopper = 534;
		if (start==601) stopper = 619;
		if (start==701) stopper = 753;
		if (start==801) stopper = 850;
		if (start==901) stopper = 916;
		if (start==1001) stopper = 1005;
		
		
		int columns = 6;
		if (songNumber.length()==4) {
			columns = 5;
		}
		
		int counter = 0;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n<a href='#start'>INDEX</a><br>");
		
		for (int i=start; i <stopper; i++ ) {
			counter++;
			
			if ((counter % columns) == 1){
				sb.append("<br>&nbsp;<br>");
			}
			
			sb.append("\n<a href='#songcs" + padStringAtStart(i+"", 4, "0") + "'>" + padStringAtStart(i+"", 4, "0") + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return sb.toString();
	}

	private static boolean isPlaceForHtmlSupplementSongGroupLinks(String songNumber) {
		/*
		 contents.append("<a id='g" + songNumber + "'>" + getHtmlLineBreak());
							contents.append(getHtmlSongGroups(songNumber));
							contents.append(getKindleMobiPageBreak());
		 */
		
		if (songNumber.equals("0001") ||
				songNumber.equals("0101") || 
				songNumber.equals("0201") || 
				songNumber.equals("0301") || 
				songNumber.equals("0401") || 
				songNumber.equals("0501") || 
				songNumber.equals("0601") || 
				songNumber.equals("0701") || 
				songNumber.equals("0801") || 
				songNumber.equals("0901") ||
				songNumber.equals("1001") ){
			return true;
		}
		return false;
	}
	
	private static String getHtmlSong50Groups() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<a id='start'>\n");
		sb.append("<table cellspacing='0' cellpadding='0' border='0' width='80%'>\n" );
		sb.append("<tr><td colspan='4' align='left' height='100'>&nbsp;</td></tr>\n" );
		sb.append("<tr><td colspan='4' align='left'><h1>诗歌</h1></td></tr>\n" );
		sb.append("<tr><td colspan='4' align='left' height='50'>&nbsp;</td></tr>\n" );
		sb.append("<tr><td colspan='4' align='left'><a href='#introduction'>使用说明</a></td></tr>\n" );
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#g001'>001</a></td>\n");
		sb.append("<td><a href='#g051'>051</a></td>\n");
		sb.append("<td><a href='#g101'>101</a></td>\n");
		sb.append("<td><a href='#g151'>151</a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#g201'>201</a></td>\n");
		sb.append("<td><a href='#g251'>251</a></td>\n");
		sb.append("<td><a href='#g301'>301</a></td>\n");
		sb.append("<td><a href='#g351'>351</a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#g401'>401</a></td>\n");
		sb.append("<td><a href='#g451'>451</a></td>\n");
		sb.append("<td><a href='#g501'>501</a></td>\n");
		sb.append("<td><a href='#g551'>551</a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#g601'>601</a></td>\n");
		sb.append("<td><a href='#g651'>651</a></td>\n");
		sb.append("<td><a href='#g701'>701</a></td>\n");
		sb.append("<td><a href='#g751'>751</a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr><td colspan='4' align='left'><h1>补充本诗歌</h1></td></tr>\n" );
		sb.append("<tr><td colspan='4' align='left' height='50'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#sg0001'>0001</a></td>\n");
		sb.append("<td><a href='#sg0101'>0101</a></td>\n");
		sb.append("<td><a href='#sg0201'>0201</a></td>\n");
		sb.append("<td><a href='#sg0301'>0301</a><br></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#sg0401'>0401</a></td>\n");
		sb.append("<td><a href='#sg0501'>0501</a></td>\n");
		sb.append("<td><a href='#sg0601'>0601</a></td>\n");
		sb.append("<td><a href='#sg0701'>0701</a><br></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr><td colspan='4' align='left' height='30'>&nbsp;</td></tr>\n" );
		sb.append("<tr>\n");
		sb.append("<td><a href='#sg0801'>0801</a></td>\n");
		sb.append("<td><a href='#sg0901'>0901</a></td>\n");
		sb.append("<td><a href='#sg1001'>1001</a></td>\n");
		sb.append("<td>&nbsp;</td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n" );
		
	
		return sb.toString();
	}

	private static String getKindleMobiPageBreak() {
		return "\n" + "<mbp:pagebreak />";
//		return "\n<br>##############";
	}
	
	private static String getHtmlPrefix() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<html>");
		sb.append("\n<head>");
		sb.append("\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sb.append("\n</head>");
		sb.append("\n<body>");
		
		return sb.toString();
	}
	
	private static String getHtmlSuffix() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n</body>");
		sb.append("\n</html>");
		
		return sb.toString();
	}
	
	private static String getIntroduction() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n<a id='introduction'><h4>使用说明" + "</h4></a>");
		sb.append(getHtmlParagraphBreak());
		sb.append("\n本说明仅适用于Kindle touch：<br>&nbsp;<br>");
		sb.append("\n1. 在阅读时手指向下划会回到诗歌文件的最上方<br>");
		sb.append("\n2. 如果不小心划回到诗歌文件的最上方， 可以用Kindle的Back option回到原来的位置<br>");
		sb.append("\n3. 每首诗歌的序列号都是回到诗歌文件最上方的链接，以便选择不同的诗歌");
		sb.append("\n<br>&nbsp;<br><i>2012-05-09</i>");
		
		return sb.toString();
	}
	
	private static String getHtmlLineBreak(){
		return "\n<br>&nbsp;<br>";
	}
	
	private static String getHtmlParagraphBreak() {
		return "\n<p>";
	}
	
	private static String padStringAtStart(String original, int length, String ch){
//		String s = original + " --> ";
		if (original.length()>=length) return original;
		
		while (original.length()< length){
			original =  ch + original;
		}
		
//		System.out.println(s + original);
		return original;
	}
	
	
}
