package com.kang.ebook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/*
 * This is for quick extract book from Yifan.com
 * 
 * In this site, chapter links start with a common prefix then appended with the chapter sequence
 * then appended with a common suffix.
 * 
 * So the getChapterLinks will assemble all the links and save them in sequence in an arraylist
 * 
 * After getting the arraylist of links, loop through to get each chapter details and parse the 
 * real content of the chapter by defining the boundary of the content, in yifan the chapter content
 * is contained in <pre></pre> tag pairs.
 * 
 * Finally write the chapters in sequence to a file of html. 
 * 
 * 
 */
public class YifanEbook {
	private ArrayList chapterLinks = new ArrayList();
	
	private String bookFileName = "Ê¥±­ÆæÄ±.html";
	private String linkPrefix = "http://www.shuku.net:8082/novels/foreign/shnsbebqiqm/shnsbebqiqm";
	private String linkSuffix = ".html";
	private int totalchapters = 49;
	private int chapterIntervals = 1000;

	public static void main(String[] args) {
		YifanEbook ebook = new YifanEbook();
		System.out.println("------Done-----");
	}

	public YifanEbook() {
		getChapterLinks();
		StringBuffer finalResult = new StringBuffer();

		finalResult.append("<html><body>");
		for (int i = 0; i < chapterLinks.size(); i++) {
			String link = (String) chapterLinks.get(i);

			try {
				String chapterContent = getChapterDetails(link);
				finalResult.append("<p>");
				finalResult.append(chapterContent);
				finalResult.append("<p>");

				Thread.sleep(chapterIntervals); // sleep for 1 second before retrieving the
									// news

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Done with " + link);
		}

		finalResult.append("</body></html>");

		writeFile(finalResult.toString(), bookFileName);
	}

	private void getChapterLinks() {
		for (int i = 1; i <= totalchapters; i++) {
			String linkSequence = i + "";
			if (i < 10) {
				linkSequence = "0" + i;
			}
			chapterLinks.add(linkPrefix + linkSequence + linkSuffix);
		}
	}

	/*
	 * Has the http link of a news, get the news content as a formatted html
	 * string
	 */
	private String getChapterDetails(String link) {
		String pageFullContent = getWebPageInFullHttpResponse(link);

		// the chapter content is contained in the unique <pre> </pre>tag pairs
		String prefix = "<pre>";
		String suffix = "</pre>";

		String pageContent = "";
		
		try {
			pageContent = getWebPart(pageFullContent, prefix, suffix);
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

		index = input.indexOf(prefix) + prefix.length();

		input = input.substring(index);

		index = input.indexOf(suffix);

		part = input.substring(0, index);

		part.replaceAll("\n", "<BR>&nbsp;<BR>");
		return part;
	}

}
