package com.ying.bible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FileUtil{
	
	private static String bookName_template = "niv%s.xml";
	
	private static String bookLine_start_template = "<Book number=\"%s\" name=\"%s\" englishName=\"%s\" chapters=\"%s\">\n";
	private static String bookLine_end_template = "</Book>\n";
	
	private static String chapterLine_start_template = "<Chapter number=\"%s\">\n";
	private static String chapterLine_end_template = "</Chapter>\n";
	
	private static String verseLine_end_template = "<Line number=\"%s\">%s</Line>\n";
	
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
	 *  
	 */
	public static void getContents(File aFile) {
//		for (int iii =1; iii<45; iii++) {
////			BibleDO boo = new BibleDO(iii);
////			
////			System.out.println(boo.getBookName() + " | " +  boo.getIndex() + " | " + boo.getChapterCount() + "\t constructor line " );
////			
////			String bkName = boo.getBookName();
////			
////			BibleDO boo2 = BibleDO.findBibleDOByBookName(bkName);
////			System.out.println(boo2.getBookName() + " | " +  boo2.getIndex() + " | " + boo2.getChapterCount() + "\t Find line  ");
////			
//			System.out.println(String.format("<Line number=\"%s\"></Line>", iii + ""));
//		}
//		
//		if (true) {
//			return;
//		}
		int bookIndex = 0;
		int chapter = 0;
		
		int verseIndex = 0;
		String verse = "";
		
		BibleDO bo = null;
		StringBuffer sb = new StringBuffer();
		
		boolean isFirst = true;
		
		int i = 0;
		String line = null; // not declared within while loop
		
		boolean firstChapter = true;
		
		/*
		 <Book number="40" name="马太福音" englishName="Matthew" chapters="28">
			<Chapter number="1">
				<Line number="1">亚伯拉罕的后裔、大卫的子孙、耶稣基督的家谱．〔后裔子孙原文都作儿子下同〕</Line>
		
		 */
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			
			try {
				
				while ((line = input.readLine()) != null && i <99999) {
					line = line.trim();
		
					if (line.equals("")){
						// blank ignore
						continue;
					} else if (isChapterLine(line)){
						//chapter lines with number up to 3 digit only
						
						if (firstChapter) {
							chapter = Integer.parseInt(line);
							sb.append(String.format(chapterLine_start_template, chapter));
							
							firstChapter = false;
						} else {
							chapter = Integer.parseInt(line);
							sb.append(chapterLine_end_template);
							sb.append(String.format(chapterLine_start_template, chapter));	
						}
						
					} else if ( BibleDO.findBibleDOByBookName(line)!=null){
						
						firstChapter = true;
						if (!isFirst) {
							sb.append(chapterLine_end_template);
							sb.append(bookLine_end_template);
							
							writeFile(sb.toString(), String.format(bookName_template, paddingBookIndex(bookIndex)));
						} else {
							isFirst= false;
						}
						//book name line:
						bo = BibleDO.findBibleDOByBookName(line);
						bookIndex = bo.getIndex();
						
						sb = new StringBuffer();
						sb.append(String.format(bookLine_start_template, paddingBookIndex(bookIndex), bo.getBookNameCN(), bo.getBookName(), bo.getChapterCount()));
						
						
					} else {
						//verse line in txt file: : 	"1:1	起初　神创造天地。
						//verse line to xml: 		<Line number="2">亚伯拉罕生以撒．以撒生雅各．雅各生犹大和他的弟兄．</Line>
						
						StringTokenizer st = new StringTokenizer(line);
						
						String verseIndexPart = st.nextToken();
						verseIndex  = trimVerseIndex(verseIndexPart);
						verse = line.substring(verseIndexPart.length()).trim();
						
						sb.append(String.format(verseLine_end_template, verseIndex, verse));
						
						
					}
					i++;
					
				}
				
				sb.append(chapterLine_end_template);
				sb.append(bookLine_end_template);
				
				writeFile(sb.toString(), String.format(bookName_template, paddingBookIndex(bookIndex)));
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		writeFile(sb.toString(), "queryCn.txt");
//		return hm;
	}

	
	public static void getContents1(File aFile) {
//		HashMap hm = new HashMap();
		int bookIndex = 0;
		int chapter = 0;
		
		int verseIndex = 0;
		String verse = "";
		
		BibleDO bo = null;
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			int i = 0;
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null && i <99999) {
					line = line.trim();
					// blank ignore
					if (line.equals("")){
						continue;
					} else if (isChapterLine(line)){
						chapter = Integer.parseInt(line);
					} else if ( BibleDO.findBibleDOByBookName(line)!=null){
						bo = BibleDO.findBibleDOByBookName(line);
						if (bo!=null){
							bookIndex = bo.getIndex();
						}
						
					} else {
						StringTokenizer st = new StringTokenizer(line);
						
						String verseIndexPart = st.nextToken();
						verseIndex  = trimVerseIndex(verseIndexPart);
						verse = line.substring(verseIndexPart.length()).trim();
						
						
						sb.append(printVerse(bookIndex, chapter, verseIndex, verse));
						
						
					}
					i++;
					
				}
			} finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		writeFile(sb.toString(), "queryCn.txt");
//		return hm;
	}
	
	private static int trimVerseIndex(String verseIndexPart) {
		StringTokenizer st = new StringTokenizer(verseIndexPart, ":");
		
		String junk = st.nextToken();
		return Integer.parseInt(st.nextToken());
	}
	
	private static String printVerse(int bookIndex, int chapter,
			int verseIndex, String verse) {
		//System.out.println(bookIndex + " -  " + chapter + " -  " + verseIndex + " -  " +  verse);
		return "INSERT INTO `bible`.`verses` (`book_Number`, `book_chapter_index`, `verse_index`, `verse_hhb`) VALUES ('"
				+ bookIndex + "', '" + chapter + "', '" + verseIndex + "', '"  + verse.replaceAll("'", "''") + "');\n";
		
	}

	private static boolean isChapterLine(String line) {
		boolean result = false;
		
		line = line.trim();
		if (line.length()<=3){
			try {
				int chapterNumber = Integer.parseInt(line);
				result = true;
			} catch (Exception e){
				
			}
		}
		
		return result;
	}
	

//	private static String getBookChapterLinks(String bookLink){
//		int chapterTotal = 0;
//		
//		if (bookLink.equals("Genesis")){
//			chapterTotal= 50;
//		} else if (bookLink.equals("Exodus")){
//			chapterTotal= 40;
//		} else if (bookLink.equals("Leviticus")){
//			chapterTotal= 27;
//		} else if (bookLink.equals("Numbers")){
//			chapterTotal= 36;
//		} else if (bookLink.equals("Deuteronomy")){
//			chapterTotal= 34;
//		} else if (bookLink.equals("Joshua")){
//			chapterTotal= 24;
//		} else if (bookLink.equals("Judges")){
//			chapterTotal= 21;
//		} else if (bookLink.equals("Ruth")){
//			chapterTotal= 4;
//		} else if (bookLink.equals("Samuel")){
//			chapterTotal= 31;
//		} else if (bookLink.equals("Samuel2-")){
//			chapterTotal= 24;
//		} else if (bookLink.equals("Kings")){
//			chapterTotal= 22;
//		} else if (bookLink.equals("Kings2-")){
//			chapterTotal= 25;
//		} else if (bookLink.equals("Chronicles")){
//			chapterTotal= 29;
//		} else if (bookLink.equals("Chronicles2-")){
//			chapterTotal= 36;
//		} else if (bookLink.equals("Ezra")){
//			chapterTotal= 10;
//		} else if (bookLink.equals("Nehemiah")){
//			chapterTotal= 13;
//		} else if (bookLink.equals("Esther")){
//			chapterTotal= 10;
//		} else if (bookLink.equals("Job")){
//			chapterTotal= 42;
//		} else if (bookLink.equals("Psalms")){
//			chapterTotal= 150;
//		} else if (bookLink.equals("Proverbs")){
//			chapterTotal= 31;
//		} else if (bookLink.equals("Ecclesiastes")){
//			chapterTotal= 12;
//		} else if (bookLink.equals("SongOfSongs")){
//			chapterTotal= 8;
//		} else if (bookLink.equals("Isaiah")){
//			chapterTotal= 66;
//		} else if (bookLink.equals("Jeremiah")){
//			chapterTotal= 52;
//		} else if (bookLink.equals("Lamentations")){
//			chapterTotal= 5;
//		} else if (bookLink.equals("Ezekiel")){
//			chapterTotal= 48;
//		} else if (bookLink.equals("Daniel")){
//			chapterTotal= 12;
//		} else if (bookLink.equals("Hosea")){
//			chapterTotal= 14;
//		} else if (bookLink.equals("Joel")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("Amos")){
//			chapterTotal= 9;
//		} else if (bookLink.equals("Obadiah")){
//			chapterTotal= 1;
//		} else if (bookLink.equals("Jonah")){
//			chapterTotal= 4;
//		} else if (bookLink.equals("Micah")){
//			chapterTotal= 7;
//		} else if (bookLink.equals("Nahum")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("Habakkuk")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("Zephaniah")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("Haggai")){
//			chapterTotal= 2;
//		} else if (bookLink.equals("Zechariah")){
//			chapterTotal= 14;
//		} else if (bookLink.equals("Malachi")){
//			chapterTotal= 4;
//		} else if (bookLink.equals("Matthew")){
//			chapterTotal= 28;
//		} else if (bookLink.equals("Mark")){
//			chapterTotal= 16;
//		} else if (bookLink.equals("Luke")){
//			chapterTotal= 24;
//		} else if (bookLink.equals("John")){
//			chapterTotal= 21;
//		} else if (bookLink.equals("Acts")){
//			chapterTotal= 28;
//		} else if (bookLink.equals("Romans")){
//			chapterTotal= 16;
//		} else if (bookLink.equals("Corinthians")){
//			chapterTotal= 16;
//		} else if (bookLink.equals("Corinthians2-")){
//			chapterTotal= 13;
//		} else if (bookLink.equals("Galatians")){
//			chapterTotal= 6;
//		} else if (bookLink.equals("Ephesians")){
//			chapterTotal= 6;
//		} else if (bookLink.equals("Philippians")){
//			chapterTotal= 4;
//		} else if (bookLink.equals("Colossians")){
//			chapterTotal= 4;
//		} else if (bookLink.equals("Thessalonians")){
//			chapterTotal= 5;
//		} else if (bookLink.equals("Thessalonians2-")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("Timothy")){
//			chapterTotal= 6;
//		} else if (bookLink.equals("Timothy2-")){
//			chapterTotal= 4;
//		} else if (bookLink.equals("Titus")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("Philemon")){
//			chapterTotal= 1;
//		} else if (bookLink.equals("Hebrews")){
//			chapterTotal= 13;
//		} else if (bookLink.equals("James")){
//			chapterTotal= 5;
//		} else if (bookLink.equals("Peter")){
//			chapterTotal= 5;
//		} else if (bookLink.equals("Peter2-")){
//			chapterTotal= 3;
//		} else if (bookLink.equals("John1-")){
//			chapterTotal= 5;
//		} else if (bookLink.equals("John2-")){
//			chapterTotal= 1;
//		} else if (bookLink.equals("John3-")){
//			chapterTotal= 1;
//		} else if (bookLink.equals("Jude")){
//			chapterTotal= 1;
//		} else if (bookLink.equals("Revelation")){
//			chapterTotal= 22;
//		} 
//		
//		StringBuffer sb = new StringBuffer();
//		for (int i=1; i<=chapterTotal; i++){
//			sb.append("<a href='#" + bookLink + i + "'>" + i + "</a>&nbsp;");
//		}
//		
//		return sb.toString();
//	}
	

//	private static String getVerseContent(String line) {
//		String verseText  = "";
//		if (line.indexOf("#VERSTEXT#") >= 0) {
//			StringBuffer sb = new StringBuffer(); 
//			line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
//			
//			if (line.indexOf("#DESCTEXT#")>0) {
//				sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
//				line = line.substring(line.indexOf("#DESCTEXT#")+ "#DESCTEXT#".length());
//				
//				while(line.indexOf("#VERSTEXT#") >= 0) {
//					line = line.substring(line.indexOf("#VERSTEXT#") + "#VERSTEXT#".length());
//					if (line.indexOf("#DESCTEXT#")>0) {
//						sb.append(line.substring(0, line.indexOf("#DESCTEXT#")).trim());
//						line = line.substring(line.indexOf("#DESCTEXT#")+ "#VERSTEXT#".length());
//					} else {
//						sb.append(line.substring(0, line.indexOf("</VERSE>")).trim());
//						line = line.substring(line.indexOf("</VERSE>")+ "</VERSE>".length());
//					}  
//				}
//				
//				line = sb.toString();
//			} else {
//				line = line.substring(0, line.indexOf("</VERSE>"));
//			}
//			
//			verseText = line;
//		} else {
//			line = line.substring(line.indexOf("<VERSE>") + "<VERSE>".length());
//			if (line.indexOf("#DESCTEXT#")>=0) { 
//				line = line.substring(0, line.indexOf("#DESCTEXT#"));
//			} else {
//				line = line.substring(0, line.indexOf("</VERSE>"));
//			}
//			
//			verseText = line;
//		}
//		
//		while (verseText.indexOf("{")>=0){
//			if (verseText.indexOf("{")==0 ){
//				verseText = verseText.substring(verseText.indexOf("}") + 1);
//			} else {
//				verseText = verseText.substring(0, verseText.indexOf("{")) + verseText.substring(verseText.indexOf("}") + 1);
//			}
//			
//		}
//		
//		verseText = verseText.replaceAll("/ ", "");
//		return verseText;
//	}
	
	private static String paddingBookIndex (int index) {
		if (index<10) {
			return "0" + index;
		}
		else 
			return "" + index;
	}
}
