package com.ying.bible;

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
	
	private BibleDO() {
	
	}
	
	public BibleDO(int index){
		this.index = index;
		if (this.index > 39) {
			this.testament = "NEW";
		}
		
		switch (index){
		case 1: 
			bookName = "Genesis";
			chapterCount = 50;
			break;
		case 2: 
			bookName = "Exodus";
			chapterCount = 40;
			break;
		case 3: 
			bookName = "Leviticus";
			chapterCount = 27;
			break;
		case 4: 
			bookName = "Numbers";
			chapterCount = 36;
			break;
		case 5: 
			bookName = "Deuteronomy";
			chapterCount = 34;
			break;
		case 6: 
			bookName = "Joshua";
			chapterCount = 24;
			break;
		case 7: 
			bookName = "Judges";
			chapterCount = 21;
			break;
		case 8: 
			bookName = "Ruth";
			chapterCount = 4;
			break;
		case 9: 
			bookName = "Samuel1";
			chapterCount = 31;
			break;
		case 10: 
			bookName = "Samuel2";
			chapterCount = 24;
			break;
			
			
		case 11: 
			bookName = "Kings1";
			chapterCount = 22;
			break;
		case 12: 
			bookName = "Kings2";
			chapterCount = 25;
			break;
		case 13: 
			bookName = "Chronicles1";
			chapterCount = 29;
			break;
		case 14: 
			bookName = "Chronicles2";
			chapterCount = 36;
			break;
		case 15: 
			bookName = "Ezra";
			chapterCount = 10;
			break;
		case 16: 
			bookName = "Nehemiah";
			chapterCount = 13;
			break;
		case 17: 
			bookName = "Esther";
			chapterCount = 10;
			break;
		case 18: 
			bookName = "Job";
			chapterCount = 42;
			break;
		case 19: 
			bookName = "Psalms";
			chapterCount = 150;
			break;
		case 20: 
			bookName = "Proverbs";
			chapterCount = 31;
			break;
			
		case 21: 
			bookName = "Ecclesiastes";
			chapterCount = 12;
			break;
		case 22: 
			bookName = "Song of Songs";
			chapterCount = 8;
			break;
		case 23: 
			bookName = "Isaiah";
			chapterCount = 66;
			break;
		case 24: 
			bookName = "Jeremiah";
			chapterCount = 52;
			break;
		case 25: 
			bookName = "Lamentations";
			chapterCount = 5;
			break;
		case 26: 
			bookName = "Ezekiel";
			chapterCount = 48;
			break;
		case 27: 
			bookName = "Daniel";
			chapterCount = 12;
			break;
		case 28: 
			bookName = "Hosea";
			chapterCount = 14;
			break;
		case 29: 
			bookName = "Joel";
			chapterCount = 3;
			break;
		case 30: 
			bookName = "Amos";
			chapterCount = 9;
			break;
			
		case 31: 
			bookName = "Obadiah";
			chapterCount = 1;
			break;
		case 32: 
			bookName = "Jonah";
			chapterCount = 4;
			break;
		case 33: 
			bookName = "Micah";
			chapterCount = 7;
			break;
		case 34: 
			bookName = "Nahum";
			chapterCount = 3;
			break;
		case 35: 
			bookName = "Habakkuk";
			chapterCount = 3;
			break;
		case 36: 
			bookName = "Zephaniah";
			chapterCount = 3;
			break;
		case 37: 
			bookName = "Haggai";
			chapterCount = 2;
			break;
		case 38: 
			bookName = "Zechariah";
			chapterCount = 14;
			break;
		case 39: 
			bookName = "Malachi";
			chapterCount = 4;
			break;
			
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
	
	public static BibleDO findBibleDOByBookName(String bn) {
		BibleDO bo = new BibleDO() ;
		if (bn.equals("Genesis")) {
			bo.chapterCount = 50;
			bo.index = 1;
		}
		
		else if (bn.equals("Matthew")) {
			bo.chapterCount = 28;
			bo.index = 40;
		}

		else if (bn.equals("Mark")) {
			bo.chapterCount = 16;
			bo.index = 41;
		}

		else if (bn.equals("Luke")) {
			bo.chapterCount = 24;
			bo.index = 42;
		}

		else if (bn.equals("John")) {
			bo.chapterCount = 21;
			bo.index = 43;
		}

		else if (bn.equals("Acts")) {
			bo.chapterCount = 28;
			bo.index = 44;
		}

		else if (bn.equals("Romans")) {
			bo.chapterCount = 16;
			bo.index = 45;
		}

		else if (bn.equals("Corinthians1")) {
			bo.chapterCount = 16;
			bo.index = 46;
		}

		else if (bn.equals("Corinthians2")) {
			bo.chapterCount = 13;
			bo.index = 47;
		}

		else if (bn.equals("Galatians")) {
			bo.chapterCount = 6;
			bo.index = 48;
		}

		else if (bn.equals("Ephesians")) {
			bo.chapterCount = 6;
			bo.index = 49;
		}

		else if (bn.equals("Philippians")) {
			bo.chapterCount = 4;
			bo.index = 50;
		}

		else if (bn.equals("Colossians")) {
			bo.chapterCount = 4;
			bo.index = 51;
		}

		else if (bn.equals("Thessalonians1")) {
			bo.chapterCount = 5;
			bo.index = 52;
		}

		else if (bn.equals("Thessalonians2")) {
			bo.chapterCount = 3;
			bo.index = 53;
		}

		else if (bn.equals("Timothy1")) {
			bo.chapterCount = 6;
			bo.index = 54;
		}

		else if (bn.equals("Timothy2")) {
			bo.chapterCount = 4;
			bo.index = 55;
		}

		else if (bn.equals("Titus")) {
			bo.chapterCount = 3;
			bo.index = 56;
		}

		else if (bn.equals("Philemon")) {
			bo.chapterCount = 1;
			bo.index = 57;
		}

		else if (bn.equals("Hebrews")) {
			bo.chapterCount = 13;
			bo.index = 58;
		}

		else if (bn.equals("James")) {
			bo.chapterCount = 5;
			bo.index = 59;
		}

		else if (bn.equals("Peter1")) {
			bo.chapterCount = 5;
			bo.index = 60;
		}

		else if (bn.equals("Peter2")) {
			bo.chapterCount = 3;
			bo.index = 61;
		}

		else if (bn.equals("John1")) {
			bo.chapterCount = 5;
			bo.index = 62;
		}

		else if (bn.equals("John2")) {
			bo.chapterCount = 1;
			bo.index = 63;
		}

		else if (bn.equals("John3")) {
			bo.chapterCount = 1;
			bo.index = 64;
		}

		else if (bn.equals("Jude")) {
			bo.chapterCount = 1;
			bo.index = 65;
		}

		else if (bn.equals("Revelation")) {
			bo.chapterCount = 22;
			bo.index = 66;
		}
		
		else {
			return null;
		}
		bo.bookName = bn;
		
		bo.testament = "OLD";
		if (bo.index >39) {
			bo.testament = "NEW";
		}
		
		bo.bookFULLName = bo.testament + "-" +  bo.bookName; 
		
		return bo;
	}
	
}