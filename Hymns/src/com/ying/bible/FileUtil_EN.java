package com.ying.bible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.HashMap;

public class FileUtil_EN {
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
	public static HashMap getContents(File aFile) {
		HashMap hm = new HashMap();
//		StringBuffer contents = new StringBuffer ();
		
		//html header
//		contents.append(getHtmlPrefix());
		
		//this is the mobi table of content
//		contents.append(getBookLinks());
		
		//give it a break so the swipe in touch can work
//		contents.append(getKindleMobiPageBreak());
		
//		contents.append(getIntroduction());
		
//		contents.append(getKindleMobiPageBreak());
		
		int chapterCounter = 0;
		int verseCounter = 1;
		String bookName = "";
		String bookLink = "";

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			int i = 0;
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null && i <99999) {
					if (line.indexOf("<BOOK NAME=\"") >=0){
						verseCounter = 1;
						chapterCounter = 1;
						
						line = line.substring(line.indexOf("<BOOK NAME=\"") + "<BOOK NAME=\"".length());
						bookName = line.substring(0, line.indexOf("\""));
						
						bookLink = getBookLink(bookName);
//						contents.append(getHtmlParagraphBreak() + "<a id='" + bookLink +  "'/><a href='#start'><h3>" + bookName + "</h3></a>\n");
						
						//leave the space for chapter links
//						contents.append(getHtmlLineBreak() + getBookChapterLinks(bookLink));
//						contents.append(getHtmlLineBreak() + "<a id='" + bookLink +  chapterCounter + "'/><a href='#" + bookLink + "'><h4>" + bookName + "-第" + chapterCounter + "章" + "</h4></a>\n");
						
						
					} else if (line.indexOf("<CHAPTER>") >=0){
						chapterCounter ++;
//						contents.append(getHtmlLineBreak() + "<a id='" + bookLink +  chapterCounter + "'/><a href='#" + bookLink + "'><h4>" + bookName + "-第" + chapterCounter + "章" + "</h4></a>\n");
						 verseCounter = 1;
					} else if (line.indexOf("		<VERSE>")>=0) {
						hm.put("[" + bookLink + "-" + chapterCounter + "-" + verseCounter + "]", getVerseContent(line));
//						contents.append(getHtmlLineBreak() + chapterCounter + ":" + verseCounter + " " +  getVerseContent(line) );
						if (line.indexOf("</CHAPTER>") >=0){
							//the last verse of a chapter, provide a kindle swipe page break
//							contents.append(getKindleMobiPageBreak());
						}
						
						verseCounter++;
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
//		contents.append(getHtmlSuffix());
//		return contents.toString();
		return hm;
	}

	private static String getBookLink(String bookName) {
		if (bookName.equals("Genesis")){
			return "Genesis";
		} else if (bookName.equals("Exodus")){
			return "Exodus";
		} else if (bookName.equals("Leviticus")){
			return "Leviticus";
		} else if (bookName.equals("Numbers")){
			return "Numbers";
		} else if (bookName.equals("Deuteronomy")){
			return "Deuteronomy";
		} else if (bookName.equals("Joshua")){
			return "Joshua";
		} else if (bookName.equals("Judges")){
			return "Judges";
		} else if (bookName.equals("Ruth")){
			return "Ruth";
		} else if (bookName.equals("1 Samuel")){
			return "Samuel";
		} else if (bookName.equals("2 Samuel")){
			return "Samuel2-";
		} else if (bookName.equals("1 Kings")){
			return "Kings";
		} else if (bookName.equals("2 Kings")){
			return "Kings2-";
		} else if (bookName.equals("1 Chronicles")){
			return "Chronicles";
		} else if (bookName.equals("2 Chronicles")){
			return "Chronicles2-";
		} else if (bookName.equals("Ezra")){
			return "Ezra";
		} else if (bookName.equals("Nehemiah")){
			return "Nehemiah";
		} else if (bookName.equals("Esther")){
			return "Esther";
		} else if (bookName.equals("Job")){
			return "Job";
		} else if (bookName.equals("Psalm")){
			return "Psalms";
		} else if (bookName.equals("Proverbs")){
			return "Proverbs";
		} else if (bookName.equals("Ecclesiastes")){
			return "Ecclesiastes";
		} else if (bookName.equals("Song of Songs")){
			return "SongOfSongs";
		} else if (bookName.equals("Isaiah")){
			return "Isaiah";
		} else if (bookName.equals("Jeremiah")){
			return "Jeremiah";
		} else if (bookName.equals("Lamentations")){
			return "Lamentations";
		} else if (bookName.equals("Ezekiel")){
			return "Ezekiel";
		} else if (bookName.equals("Daniel")){
			return "Daniel";
		} else if (bookName.equals("Hosea")){
			return "Hosea";
		} else if (bookName.equals("Joel")){
			return "Joel";
		} else if (bookName.equals("Amos")){
			return "Amos";
		} else if (bookName.equals("Obadiah")){
			return "Obadiah";
		} else if (bookName.equals("Jonah")){
			return "Jonah";
		} else if (bookName.equals("Micah")){
			return "Micah";
		} else if (bookName.equals("Nahum")){
			return "Nahum";
		} else if (bookName.equals("Habakkuk")){
			return "Habakkuk";
		} else if (bookName.equals("Zephaniah")){
			return "Zephaniah";
		} else if (bookName.equals("Haggai")){
			return "Haggai";
		} else if (bookName.equals("Zechariah")){
			return "Zechariah";
		} else if (bookName.equals("Malachi")){
			return "Malachi";
		} else if (bookName.equals("Matthew")){
			return "Matthew";
		} else if (bookName.equals("Mark")){
			return "Mark";
		} else if (bookName.equals("Luke")){
			return "Luke";
		} else if (bookName.equals("John")){
			return "John";
		} else if (bookName.equals("Acts")){
			return "Acts";
		} else if (bookName.equals("Romans")){
			return "Romans";
		} else if (bookName.equals("1 Corinthians")){
			return "Corinthians";
		} else if (bookName.equals("2 Corinthians")){
			return "Corinthians2-";
		} else if (bookName.equals("Galatians")){
			return "Galatians";
		} else if (bookName.equals("Ephesians")){
			return "Ephesians";
		} else if (bookName.equals("Philippians")){
			return "Philippians";
		} else if (bookName.equals("Colossians")){
			return "Colossians";
		} else if (bookName.equals("1 Thessalonians")){
			return "Thessalonians";
		} else if (bookName.equals("2 Thessalonians")){
			return "Thessalonians2-";
		} else if (bookName.equals("1 Timothy")){
			return "Timothy";
		} else if (bookName.equals("2 Timothy")){
			return "Timothy2-";
		} else if (bookName.equals("Titus")){
			return "Titus";
		} else if (bookName.equals("Philemon")){
			return "Philemon";
		} else if (bookName.equals("Hebrews")){
			return "Hebrews";
		} else if (bookName.equals("James")){
			return "James";
		} else if (bookName.equals("1 Peter")){
			return "Peter";
		} else if (bookName.equals("2 Peter")){
			return "Peter2-";
		} else if (bookName.equals("1 John")){
			return "John1-";
		} else if (bookName.equals("2 John")){
			return "John2-";
		} else if (bookName.equals("3 John")){
			return "John3-";
		} else if (bookName.equals("Jude")){
			return "Jude";
		} else if (bookName.equals("Revelation")){
			return "Revelation";
		} 
		return "TTTT";
	}
	
	private static String getBookLinks() {
		StringBuffer sb = new StringBuffer();
		sb.append("<a id='start' />");
		sb.append("\n<h1>恢复本圣经</h1>");
		
		sb.append(getHtmlLineBreak());
		
		sb.append("<a href='#introduction'>使用说明</a>");
		
		sb.append(getHtmlLineBreak());
		
		sb.append("\n<h2>旧约</h2>");
		
		sb.append(getHtmlLineBreak());
		
		sb.append("\n<a href='#Genesis'>創</a>&nbsp;");
		sb.append("\n<a href='#Exodus'>出</a>&nbsp;");
		sb.append("\n<a href='#Leviticus'>利</a>&nbsp;");
		sb.append("\n<a href='#Numbers'>民</a>&nbsp;");
		sb.append("\n<a href='#Deuteronomy'>申</a>&nbsp;");
		sb.append("\n<a href='#Joshua'>書</a>&nbsp;");
		sb.append("\n<a href='#Judges'>士</a>&nbsp;");
		sb.append("\n<a href='#Ruth'>得</a>&nbsp;");
		sb.append("\n<a href='#Samuel'>撒上</a>&nbsp;");
		sb.append("\n<a href='#Samuel2-'>撒下</a>&nbsp;<br>");
		sb.append("\n<a href='#Kings'>王上</a>&nbsp;");
		sb.append("\n<a href='#Kings2-'>王下</a>&nbsp;");
		sb.append("\n<a href='#Chronicles'>代上</a>&nbsp;");
		sb.append("\n<a href='#Chronicles2-'>代下</a>&nbsp;");	
		sb.append("\n<a href='#Ezra'>拉</a>&nbsp;");
		sb.append("\n<a href='#Nehemiah'>尼</a>&nbsp;");
		sb.append("\n<a href='#Esther'>斯</a>&nbsp;");
		sb.append("\n<a href='#Job'>伯</a>&nbsp;");
		sb.append("\n<a href='#Psalms'>詩</a>&nbsp;");
		sb.append("\n<a href='#Proverbs'>箴</a>&nbsp;<br>");
		sb.append("\n<a href='#Ecclesiastes'>傳</a>&nbsp;");
		sb.append("\n<a href='#SongOfSongs'>歌</a>&nbsp;");
		sb.append("\n<a href='#Isaiah'>賽</a>&nbsp;");
		sb.append("\n<a href='#Jeremiah'>耶</a>&nbsp;");
		sb.append("\n<a href='#Lamentations'>哀</a>&nbsp;");
		sb.append("\n<a href='#Ezekiel'>結</a>&nbsp;");
		sb.append("\n<a href='#Daniel'>但</a>&nbsp;");
		sb.append("\n<a href='#Hosea'>何</a>&nbsp;");
		sb.append("\n<a href='#Joel'>珥</a>&nbsp;");
		sb.append("\n<a href='#Amos'>摩</a>&nbsp;<br>");
		sb.append("\n<a href='#Obadiah'>俄</a>&nbsp;");
		sb.append("\n<a href='#Jonah'>拿</a>&nbsp;");
		sb.append("\n<a href='#Micah'>彌</a>&nbsp;");
		sb.append("\n<a href='#Nahum'>鴻</a>&nbsp;");
		sb.append("\n<a href='#Habakkuk'>哈</a>&nbsp;");
		sb.append("\n<a href='#Zephaniah'>番</a>&nbsp;");	
		sb.append("\n<a href='#Haggai'>該</a>&nbsp;");
		sb.append("\n<a href='#Zechariah'>亞</a>&nbsp;");
		sb.append("\n<a href='#Malachi'>瑪</a>&nbsp;");
		
		sb.append(getHtmlLineBreak());
		
		sb.append("<hr>");
		
		sb.append("\n<h2>新约</h2>");
		
		sb.append(getHtmlLineBreak());
		
		sb.append("\n<a href='#Matthew'>太</a>&nbsp;");
		sb.append("\n<a href='#Mark'>可</a>&nbsp;");
		sb.append("\n<a href='#Luke'>路</a>&nbsp;");
		sb.append("\n<a href='#John'>約</a>&nbsp;");
		sb.append("\n<a href='#Acts'>徒</a>&nbsp;");
		sb.append("\n<a href='#Romans'>羅</a>&nbsp;");
		sb.append("\n<a href='#Corinthians'>林前</a>&nbsp;");
		sb.append("\n<a href='#Corinthians2-'>林後</a>&nbsp;");
		sb.append("\n<a href='#Galatians'>加</a>&nbsp;");
		sb.append("\n<a href='#Ephesians'>弗</a>&nbsp;<br>");
		sb.append("\n<a href='#Philippians'>腓</a>&nbsp;");
		sb.append("\n<a href='#Colossians'>西</a>&nbsp;");
		sb.append("\n<a href='#Thessalonians'>帖前</a>&nbsp;");
		sb.append("\n<a href='#Thessalonians2-'>帖後</a>&nbsp;");
		sb.append("\n<a href='#Timothy'>提前</a>&nbsp;");
		sb.append("\n<a href='#Timothy2-'>提後</a>&nbsp;");
		sb.append("\n<a href='#Titus'>多</a>&nbsp;");
		sb.append("\n<a href='#Philemon'>門</a>&nbsp;");
		sb.append("\n<a href='#Hebrews'>來</a>&nbsp;");
		sb.append("\n<a href='#James'>雅</a>&nbsp;<br>");
		sb.append("\n<a href='#Peter'>彼前</a>&nbsp;");
		sb.append("\n<a href='#Peter2-'>彼後</a>&nbsp;");
		sb.append("\n<a href='#John1-'>約壹</a>&nbsp;");
		sb.append("\n<a href='#John2-'>約貳</a>&nbsp;");
		sb.append("\n<a href='#John3-'>約參</a>&nbsp;");
		sb.append("\n<a href='#Jude'>猶</a>&nbsp;");
		sb.append("\n<a href='#Revelation'>啟</a>&nbsp;");
		
		return sb.toString();
	}

	private static String getKindleMobiPageBreak() {
		return "\n" + "<mbp:pagebreak />";
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
		sb.append("\n1. 在阅读时手指向下划会回到最上方选书的目录<br>");
		sb.append("\n2. 如果不小心划回选书目录， 可以用Kindle的Back option回到原来阅读的位置<br>");
		sb.append("\n3. 书的每一章标题都是回到该书最上方的链接，以便选择不同的章节<br>");
		sb.append("\n4. 每本书的标题都可以回到最上方选书的目录<br>");
		
		return sb.toString();
	}
	
	private static String getHtmlLineBreak(){
		return "\n<br>&nbsp;<br>";
	}
	
	private static String getHtmlParagraphBreak() {
		return "\n<p>&nbsp;<p><br>";
	}
	
	
	
	
//	private static String getBookLinks() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<a href='#Genesis'>創世記</a>&nbsp;");
//		sb.append("<a href='#Exodus'>出埃及記</a>&nbsp;");
//		sb.append("<a href='#Leviticus'>利未記</a>&nbsp;");
//		sb.append("<a href='#Numbers'>民數記</a>&nbsp;");
//		sb.append("<a href='#Deuteronomy'>申命記</a>&nbsp;");
//		sb.append("<a href='#Joshua'>約書亞記</a>&nbsp;");
//		sb.append("<a href='#Judges'>士師記</a>&nbsp;");
//		sb.append("<a href='#Ruth'>路得記</a>&nbsp;");
//		sb.append("<a href='#Samuel'>撒母耳記上</a>&nbsp;");
//		sb.append("<a href='#Samuel2-'>撒母耳記下</a>&nbsp;");
//		sb.append("<a href='#Kings'>列王紀上</a>&nbsp;");
//		sb.append("<a href='#Kings2-'>列王紀下</a>&nbsp;");
//		sb.append("<a href='#Chronicles'>歷代志上</a>&nbsp;");
//		sb.append("<a href='#Chronicles2-'>歷代志下</a>&nbsp;");	
//		sb.append("<a href='#Ezra'>以斯拉記</a>&nbsp;");
//		sb.append("<a href='#Nehemiah'>尼希米記</a>&nbsp;");
//		sb.append("<a href='#Esther'>以斯帖記</a>&nbsp;");
//		sb.append("<a href='#Job'>約伯記</a>&nbsp;");
//		sb.append("<a href='#Psalms'>詩篇</a>&nbsp;");
//		sb.append("<a href='#Proverbs'>箴言</a>&nbsp;");
//		sb.append("<a href='#Ecclesiastes'>傳道書</a>&nbsp;");
//		sb.append("<a href='#SongOfSongs'>雅歌</a>&nbsp;");
//		sb.append("<a href='#Isaiah'>以賽亞書</a>&nbsp;");
//		sb.append("<a href='#Jeremiah'>耶利米書</a>&nbsp;");
//		sb.append("<a href='#Lamentations'>耶利米哀歌</a>&nbsp;");
//		sb.append("<a href='#Ezekiel'>以西結書</a>&nbsp;");
//		sb.append("<a href='#Daniel'>但以理書</a>&nbsp;");
//		sb.append("<a href='#Hosea'>何西阿書</a>&nbsp;");
//		sb.append("<a href='#Joel'>約珥書</a>&nbsp;");
//		sb.append("<a href='#Amos'>阿摩司書</a>&nbsp;");
//		sb.append("<a href='#Obadiah'>俄巴底亞書</a>&nbsp;");
//		sb.append("<a href='#Jonah'>約拿書</a>&nbsp;");
//		sb.append("<a href='#Micah'>彌迦書</a>&nbsp;");
//		sb.append("<a href='#Nahum'>那鴻書</a>&nbsp;");
//		sb.append("<a href='#Habakkuk'>哈巴谷書</a>&nbsp;");
//		sb.append("<a href='#Zephaniah'>西番雅書</a>&nbsp;");	
//		sb.append("<a href='#Haggai'>哈該書</a>&nbsp;");
//		sb.append("<a href='#Zechariah'>撒迦利亞書</a>&nbsp;");
//		sb.append("<a href='#Malachi'>瑪拉基書</a>&nbsp;");
//
//		sb.append("<a href='#Matthew'>馬太福音</a>&nbsp;");
//		sb.append("<a href='#Mark'>馬可福音</a>&nbsp;");
//		sb.append("<a href='#Luke'>路加福音</a>&nbsp;");
//		sb.append("<a href='#John'>約翰福音</a>&nbsp;");
//		sb.append("<a href='#Acts'>使徒行傳</a>&nbsp;");
//		sb.append("<a href='#Romans'>羅馬書</a>&nbsp;");
//		sb.append("<a href='#Corinthians'>哥林多前書</a>&nbsp;");
//		sb.append("<a href='#Corinthians2-'>哥林多後書</a>&nbsp;");
//		sb.append("<a href='#Galatians'>加拉太書</a>&nbsp;");
//		sb.append("<a href='#Ephesians'>以弗所書</a>&nbsp;");
//		sb.append("<a href='#Philippians'>腓立比書</a>&nbsp;");
//		sb.append("<a href='#Colossians'>歌羅西書</a>&nbsp;");
//		sb.append("<a href='#Thessalonians'>帖撒羅尼迦前書</a>&nbsp;");
//		sb.append("<a href='#Thessalonians2-'>帖撒羅尼迦後書</a>&nbsp;");
//		sb.append("<a href='#Timothy'>提摩太前書</a>&nbsp;");
//		sb.append("<a href='#Timothy2-'>提摩太後書</a>&nbsp;");
//		sb.append("<a href='#Titus'>提多書</a>&nbsp;");
//		sb.append("<a href='#Philemon'>腓利門書</a>&nbsp;");
//		sb.append("<a href='#Hebrews'>希伯來書</a>&nbsp;");
//		sb.append("<a href='#James'>雅各書</a>&nbsp;");
//		sb.append("<a href='#Peter'>彼得前書</a>&nbsp;");
//		sb.append("<a href='#Peter2-'>彼得後書</a>&nbsp;");
//		sb.append("<a href='#John1-'>約翰壹書</a>&nbsp;");
//		sb.append("<a href='#John2-'>約翰貳書</a>&nbsp;");
//		sb.append("<a href='#John3-'>約翰參書</a>&nbsp;");
//		sb.append("<a href='#Jude'>猶大書</a>&nbsp;");
//		sb.append("<a href='#Revelation'>啟示錄</a>&nbsp;");
//		
//		return sb.toString();
//	}
	private static String getBookChapterLinks(String bookLink){
		int chapterTotal = 0;
		
		if (bookLink.equals("Genesis")){
			chapterTotal= 50;
		} else if (bookLink.equals("Exodus")){
			chapterTotal= 40;
		} else if (bookLink.equals("Leviticus")){
			chapterTotal= 27;
		} else if (bookLink.equals("Numbers")){
			chapterTotal= 36;
		} else if (bookLink.equals("Deuteronomy")){
			chapterTotal= 34;
		} else if (bookLink.equals("Joshua")){
			chapterTotal= 24;
		} else if (bookLink.equals("Judges")){
			chapterTotal= 21;
		} else if (bookLink.equals("Ruth")){
			chapterTotal= 4;
		} else if (bookLink.equals("Samuel")){
			chapterTotal= 31;
		} else if (bookLink.equals("Samuel2-")){
			chapterTotal= 24;
		} else if (bookLink.equals("Kings")){
			chapterTotal= 22;
		} else if (bookLink.equals("Kings2-")){
			chapterTotal= 25;
		} else if (bookLink.equals("Chronicles")){
			chapterTotal= 29;
		} else if (bookLink.equals("Chronicles2-")){
			chapterTotal= 36;
		} else if (bookLink.equals("Ezra")){
			chapterTotal= 10;
		} else if (bookLink.equals("Nehemiah")){
			chapterTotal= 13;
		} else if (bookLink.equals("Esther")){
			chapterTotal= 10;
		} else if (bookLink.equals("Job")){
			chapterTotal= 42;
		} else if (bookLink.equals("Psalms")){
			chapterTotal= 150;
		} else if (bookLink.equals("Proverbs")){
			chapterTotal= 31;
		} else if (bookLink.equals("Ecclesiastes")){
			chapterTotal= 12;
		} else if (bookLink.equals("SongOfSongs")){
			chapterTotal= 8;
		} else if (bookLink.equals("Isaiah")){
			chapterTotal= 66;
		} else if (bookLink.equals("Jeremiah")){
			chapterTotal= 52;
		} else if (bookLink.equals("Lamentations")){
			chapterTotal= 5;
		} else if (bookLink.equals("Ezekiel")){
			chapterTotal= 48;
		} else if (bookLink.equals("Daniel")){
			chapterTotal= 12;
		} else if (bookLink.equals("Hosea")){
			chapterTotal= 14;
		} else if (bookLink.equals("Joel")){
			chapterTotal= 3;
		} else if (bookLink.equals("Amos")){
			chapterTotal= 9;
		} else if (bookLink.equals("Obadiah")){
			chapterTotal= 1;
		} else if (bookLink.equals("Jonah")){
			chapterTotal= 4;
		} else if (bookLink.equals("Micah")){
			chapterTotal= 7;
		} else if (bookLink.equals("Nahum")){
			chapterTotal= 3;
		} else if (bookLink.equals("Habakkuk")){
			chapterTotal= 3;
		} else if (bookLink.equals("Zephaniah")){
			chapterTotal= 3;
		} else if (bookLink.equals("Haggai")){
			chapterTotal= 2;
		} else if (bookLink.equals("Zechariah")){
			chapterTotal= 14;
		} else if (bookLink.equals("Malachi")){
			chapterTotal= 4;
		} else if (bookLink.equals("Matthew")){
			chapterTotal= 28;
		} else if (bookLink.equals("Mark")){
			chapterTotal= 16;
		} else if (bookLink.equals("Luke")){
			chapterTotal= 24;
		} else if (bookLink.equals("John")){
			chapterTotal= 21;
		} else if (bookLink.equals("Acts")){
			chapterTotal= 28;
		} else if (bookLink.equals("Romans")){
			chapterTotal= 16;
		} else if (bookLink.equals("Corinthians")){
			chapterTotal= 16;
		} else if (bookLink.equals("Corinthians2-")){
			chapterTotal= 13;
		} else if (bookLink.equals("Galatians")){
			chapterTotal= 6;
		} else if (bookLink.equals("Ephesians")){
			chapterTotal= 6;
		} else if (bookLink.equals("Philippians")){
			chapterTotal= 4;
		} else if (bookLink.equals("Colossians")){
			chapterTotal= 4;
		} else if (bookLink.equals("Thessalonians")){
			chapterTotal= 5;
		} else if (bookLink.equals("Thessalonians2-")){
			chapterTotal= 3;
		} else if (bookLink.equals("Timothy")){
			chapterTotal= 6;
		} else if (bookLink.equals("Timothy2-")){
			chapterTotal= 4;
		} else if (bookLink.equals("Titus")){
			chapterTotal= 3;
		} else if (bookLink.equals("Philemon")){
			chapterTotal= 1;
		} else if (bookLink.equals("Hebrews")){
			chapterTotal= 13;
		} else if (bookLink.equals("James")){
			chapterTotal= 5;
		} else if (bookLink.equals("Peter")){
			chapterTotal= 5;
		} else if (bookLink.equals("Peter2-")){
			chapterTotal= 3;
		} else if (bookLink.equals("John1-")){
			chapterTotal= 5;
		} else if (bookLink.equals("John2-")){
			chapterTotal= 1;
		} else if (bookLink.equals("John3-")){
			chapterTotal= 1;
		} else if (bookLink.equals("Jude")){
			chapterTotal= 1;
		} else if (bookLink.equals("Revelation")){
			chapterTotal= 22;
		} 
		
		StringBuffer sb = new StringBuffer();
		for (int i=1; i<=chapterTotal; i++){
			sb.append("<a href='#" + bookLink + i + "'>" + i + "</a>&nbsp;");
		}
		
		return sb.toString();
	}
	

	private static String getVerseContent(String line) {
		String verseText  = "";
		if (line.indexOf("#VERSTEXT#") >= 0) {
			StringBuffer sb = new StringBuffer(); 
			line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
			
			if (line.indexOf("#DESCTEXT#")>0) {
				sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
				line = line.substring(line.indexOf("#DESCTEXT#")+ "#DESCTEXT#".length());
				
				while(line.indexOf("#VERSTEXT#") >= 0) {
					line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
					if (line.indexOf("#DESCTEXT#")>0) {
						sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
						line = line.substring(line.indexOf("#DESCTEXT#")+ "#VERSTEXT#".length());
					} else {
						sb.append(line.substring(0, line.indexOf("</VERSE>")).trim());
						line = line.substring(line.indexOf("</VERSE>")+ "</VERSE>".length());
					}  
				}
				
				line = sb.toString();
			} else {
				line = line.substring(0, line.indexOf("</VERSE>"));
			}
			
			verseText = line;
		} else {
			line = line.substring(line.indexOf("<VERSE>") + "<VERSE>".length());
			if (line.indexOf("#DESCTEXT#")>=0) { 
				line = line.substring(0, line.indexOf("#DESCTEXT#"));
			} else {
				line = line.substring(0, line.indexOf("</VERSE>"));
			}
			
			verseText = line;
		}
		
		while (verseText.indexOf("{")>=0){
			if (verseText.indexOf("{")==0 ){
				verseText = verseText.substring(verseText.indexOf("}") + 1);
			} else {
				verseText = verseText.substring(0, verseText.indexOf("{")) + verseText.substring(verseText.indexOf("}") + 1);
			}
			
		}
		
		verseText = verseText.replaceAll("/ ", "");
		return verseText;
	}
}
