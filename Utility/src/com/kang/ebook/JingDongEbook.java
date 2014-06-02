package com.kang.ebook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/*
 * This is for quick extract book from ���������ʷ 
 *  
 * In this site, chapter links start with a common prefix then appended with the chapter sequence
 * then appended with a common suffix.
 * 
 * The links array can be generated by viewing source of the first page, the 11th page .....
 * 
 * Finally write the pages in sequence to a file of html. 
 * 
 * 
 */
public class JingDongEbook {
	//this is the html file going to be generated.
	private String bookFileName = "jingdong.html";
	private String[] links = {
			"http://read.jd.com/7288/364398.html",
			"http://read.jd.com/7288/364399.html",
			"http://read.jd.com/7288/364400.html",
			"http://read.jd.com/7288/364401.html",
			"http://read.jd.com/7288/364402.html",
			"http://read.jd.com/7288/364403.html",
			"http://read.jd.com/7288/364404.html",
			"http://read.jd.com/7288/364405.html",
			"http://read.jd.com/7288/364406.html",
			"http://read.jd.com/7288/364407.html",
			"http://read.jd.com/7288/364408.html",
			"http://read.jd.com/7288/364409.html",
			"http://read.jd.com/7288/364410.html",
			"http://read.jd.com/7288/364411.html",
			"http://read.jd.com/7288/364412.html",
			"http://read.jd.com/7288/364413.html",
			"http://read.jd.com/7288/364414.html",
			"http://read.jd.com/7288/364415.html",
			"http://read.jd.com/7288/364416.html",
			"http://read.jd.com/7288/364417.html",
			"http://read.jd.com/7288/364418.html",
			"http://read.jd.com/7288/364419.html",
			"http://read.jd.com/7288/364420.html",
			"http://read.jd.com/7288/364421.html",
			"http://read.jd.com/7288/364422.html",
			"http://read.jd.com/7288/364423.html",
			"http://read.jd.com/7288/364424.html",
			"http://read.jd.com/7288/364425.html",
			"http://read.jd.com/7288/364426.html",
			"http://read.jd.com/7288/364427.html",
			"http://read.jd.com/7288/364428.html",
			"http://read.jd.com/7288/364429.html",
			"http://read.jd.com/7288/364430.html",
			"http://read.jd.com/7288/364431.html",
			"http://read.jd.com/7288/364432.html",
			"http://read.jd.com/7288/364433.html",

	};
	
	
	private String prefix = "<div id=\"zoom\">";
	private String suffix = "</div>";
	
	private int chapterIntervals = 1000;
	
	public static void main(String[] args) {
		JingDongEbook ebook = new JingDongEbook();
		System.out.println("------Done-----");
	}

	public JingDongEbook() {
		
		StringBuffer finalResult = new StringBuffer();

		finalResult.append("<html><body>");
		for (int i=0; i< links.length; i++) {
			String link = (String) links[i];

			try {
				String pageContent = getPageDetails(link);
				finalResult.append("<p>");
				finalResult.append(pageContent);
				finalResult.append("<p>");

				Thread.sleep(chapterIntervals); // sleep for  mil-second before retrieving the page
									

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Done with " + link);
		}

		finalResult.append("</body></html>");

		writeFile(finalResult.toString(), bookFileName);
	}

	

	/*
	 * Has the http link of a page, get the content as a formatted html
	 * string
	 */
	private String getPageDetails(String link) {
		String pageFullContent = getWebPageInFullHttpResponse(link);
		
		String pageContent = "";
		
		try {

			String chapterHeader = getWebPart(pageFullContent, "<h1>", "</h1>"); 
			
			pageContent = getWebPart(pageFullContent, prefix, suffix);
			
			pageContent = chapterHeader + "<Br>" + pageContent;
		} catch(Exception e){
			
		}
		return pageContent;
	}

	private void writeFile(String content, String filename) {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(filename));

			out.print(content);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
		}
	}

	private String getWebPageInFullHttpResponse(String sitelink) {
		StringBuffer webPageBuffer = new StringBuffer();

		try {
			URL url = new URL(sitelink);

			URLConnection uc = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(uc
					.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				webPageBuffer.append("<BR>" + inputLine);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error with link: " + sitelink);
		}

		return webPageBuffer.toString();
	}

	private String getWebPart(String input, String prefix, String suffix) {
		String part;

		int index = 0;

		index = input.indexOf(prefix) ;

		input = input.substring(index);

		index = input.indexOf(suffix);

		part = input.substring(0, index);

		return part;
	}

}