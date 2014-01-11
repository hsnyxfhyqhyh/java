package com.kang.ebook;

public class BibleDO {
	

	private String bookName;
	private String bookFULLName ;
	public String getBookFULLName() {
		return bookFULLName;
	}

	private int chapterCount;
	private int index ;
	public int getIndex() {
		return index;
	}

	public String getTestament() {
		return testament;
	}

	private String testament = "OLD";
	
	public BibleDO(int index){
		this.index = index;
		if (this.index > 39) {
			this.testament = "NEW";
		}
		
		switch (index){
		case 40:
			bookName= "Matthew";
			chapterCount = 28;
			break;
		case 41:
			bookName= "Mark";
			chapterCount = 16;
			break;
		case 42:
			bookName= "Luke";
			chapterCount = 24;
			break;
		case 43:
			bookName= "John";
			chapterCount = 21;
			break;
		case 44:
			bookName= "Acts";
			chapterCount = 28;
			break;
		case 45:
			bookName= "Romans";
			chapterCount = 16;
			break;
		case 46:
			bookName= "Corinthians1";
			chapterCount = 16;
			break;
		case 47:
			bookName= "Corinthians2";
			chapterCount = 13;
			break;
		case 48:
			bookName= "Galatians";
			chapterCount = 6;
			break;
		case 49:
			bookName= "Ephesians";
			chapterCount = 6;
			break;
		case 50:
			bookName= "Philippians";
			chapterCount = 4;
			break;
		case 51:
			bookName= "Colossians";
			chapterCount = 4;
			break;
		case 52:
			bookName= "Thessalonians1";
			chapterCount = 5;
			break;
		case 53:
			bookName= "Thessalonians2";
			chapterCount = 3;
			break;
		case 54:
			bookName= "Timothy1";
			chapterCount = 6;
			break;
		case 55:
			bookName= "Timothy2";
			chapterCount = 4;
			break;
		case 56:
			bookName= "Titus";
			chapterCount = 3;
			break;
		case 57:
			bookName= "Philemon";
			chapterCount = 1;
			break;
		case 58:
			bookName= "Hebrews";
			chapterCount = 13;
			break;
		case 59:
			bookName= "James";
			chapterCount = 5;
			break;
		case 60:
			bookName= "Peter1";
			chapterCount = 5;
			break;
		case 61:
			bookName= "Peter2";
			chapterCount = 3;
			break;
		case 62:
			bookName= "John1";
			chapterCount = 5;
			break;
	
		case 63:
			bookName= "John2";
			chapterCount = 1;
			break;
		case 64:
			bookName= "John3";
			chapterCount = 1;
			break;
		case 65:
			bookName= "Jude";
			chapterCount = 1;
			break;
		case 66:
			bookName= "Revelation";
			chapterCount = 22;
			break;
		
		}
		
		this.bookFULLName = this.testament + "-" +  this.bookName; 
	}
	
	public String getBookName() {
		return bookName;
	}

	

	public int getChapterCount() {
		return chapterCount;
	}
	
	public String toString() {
		
		return this.index + "\t" +  this.bookName + "\t" + this.bookFULLName + "\t" + this.chapterCount ;
	
	}
	
}