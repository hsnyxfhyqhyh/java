package com.ying.bible;

import java.sql.*;

public class CopyOfDBParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		StringBuffer sb = new StringBuffer();
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/BIBLE?"
							+ "user=chccc&password=53chccc2004");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rs = statement
					.executeQuery("select book_Number, book_chapter_index, verse_index, verse_niv , verse_hhb from verses where book_number=40 order by book_number asc , book_chapter_index asc, verse_index asc, verse_id asc");

			int bookNumber = 0;
			int chapterNumber = 0;
			
			int verseNumber = 0;
			
//			sb.append("<HTML><BODY><TABLE>");
			
			while (rs.next()) {
				int bn = rs.getInt("book_Number");
				if (bn!= bookNumber){
					bookNumber = bn;
					BibleDO bo = new BibleDO(bookNumber);
					sb.append(bo.getBookName() + "\n\n");
					chapterNumber = 0;
				}
				
				int cn = rs.getInt("book_chapter_index");
				if (cn!= chapterNumber){
					chapterNumber = cn;
					
					sb.append("Chapter " + chapterNumber + "\n\n");
					verseNumber=0;
				}
				
				int vn = rs.getInt("verse_index");
				if (vn!= verseNumber){
					verseNumber = vn;
					sb.append(verseNumber + "\n");
					
					String hhb_text = rs.getString("verse_hhb");
					String niv_text = rs.getString("verse_niv");
					
					sb.append(getVerseText(niv_text, hhb_text + "\n\n"));
					
				} else {
					String hhb_text = rs.getString("verse_hhb");
					String niv_text = rs.getString("verse_niv");
					sb.append("\n" + getVerseText(niv_text, hhb_text) + "\n\n");
				}
				 
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			      if (rs != null) {
			        rs.close();
			      }

			      if (statement != null) {
			        statement.close();
			      }

			      if (connect != null) {
			        connect.close();
			      }
			    } catch (Exception e) {

			    }
		}
		FileUtil.writeFile(sb.toString(), "script.txt");
		System.out.println("DONE");
	}
	
	public static String getVerseText(String niv, String hhb){
		if (niv==null || niv.trim().equals("")){
			return hhb;
		} else {
			return niv;
		}
	}

}
