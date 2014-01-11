package com.ying.bible;

import java.sql.*;

public class DBParser {

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
							+ "user=root&password=password");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rs = statement.executeQuery("select book_Number, book_chapter_index, verse_index, verse_niv , verse_hhb from verses where book_number=66 order by book_number asc , book_chapter_index asc, verse_index asc, verse_id asc");
//			rs = statement.executeQuery("select book_Number, book_chapter_index, verse_index, verse_niv , verse_hhb from verses  order by book_number asc , book_chapter_index asc, verse_index asc, verse_id asc");

			int bookNumber = 0;
			int chapterNumber = 0;
			
			int verseNumber = 0;
			
			sb.append("<HTML><BODY><TABLE align='center' width='80%'><meta charset='utf-8'>");
			
			while (rs.next()) {
				int bn = rs.getInt("book_Number");
				if (bn!= bookNumber){
					bookNumber = bn;
					BibleDO bo = new BibleDO(bookNumber);
					sb.append ("<TR><TD valign=middle height=100 colspan=2 class='book'>");
					sb.append(getBookChineseName(bo.getIndex()) + "\n\n");
					sb.append ("</TD></TR>");
					
					chapterNumber = 0;
				}
				
				int cn = rs.getInt("book_chapter_index");
				if (cn!= chapterNumber){
					chapterNumber = cn;
					sb.append("<TR><TD height=50></TD><TD></TD></TR>");
					sb.append ("<TR><TD colspan=2 class='chapter'>");
					sb.append("第 " + chapterNumber + "章\n\n");
					sb.append ("</TD></TR>");
					verseNumber=0;
				}
				
				sb.append("<TR><TD height=10></TD><TD></TD></TR>");
				sb.append ("<TR>");
				int vn = rs.getInt("verse_index");
				if (vn!= verseNumber){
					verseNumber = vn;
					sb.append("<TD valign=top align=left width=30 class='verseNumber'>");
					sb.append(verseNumber + "\n");
					sb.append("</TD>");
					String hhb_text = rs.getString("verse_hhb");
					String niv_text = rs.getString("verse_niv");
					sb.append("<TD class='verse'>");
					sb.append(getVerseText(niv_text, hhb_text + "\n\n"));
					sb.append("</TD>");
				} else {
					sb.append("<TD>");
					sb.append("</TD><TD class='verse'>");
					String hhb_text = rs.getString("verse_hhb");
					String niv_text = rs.getString("verse_niv");
					sb.append("\n" + getVerseText(niv_text, hhb_text) + "\n\n");
					sb.append("</TD>");
				}
				 
				sb.append("</TR>");
			}
			sb.append("</TABLE></BODY></HTML>");

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
		FileUtil.writeFile(sb.toString(), "script.html");
		System.out.println("DONE");
	}
	
	public static String getVerseText(String niv, String hhb){
		if (niv==null || niv.trim().equals("")){
			return hhb;
		} else {
			return niv;
		}
	}
	
	public static String getBookChineseName (int book_number) {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String book_cn_name = "";
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/BIBLE?"
							+ "user=root&password=password");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rs = statement
					.executeQuery("select book_Name from books where book_number=" + book_number);
			while (rs.next()) {
				book_cn_name =  rs.getString("book_Name");
			}
		}catch (Exception e) {
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
		
		return book_cn_name;
	}

}
