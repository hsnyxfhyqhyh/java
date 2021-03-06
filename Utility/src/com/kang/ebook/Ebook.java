package com.kang.ebook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.kang.util.FileUtil;

/*
 * This is to extract a chaptered book quickly. 2013-02-22
 *  
 * In this site, chapter links start with a common prefix then appended with the chapter sequence
 * then appended with a common suffix.
 * 
 * The links array can be generated by viewing source of the first page, the 11th page .....
 * 
 * Finally write the pages in sequence to a file of html. 
 * 
 * ***********************************************************************************
 * Depends on how many links there are, the same amount of files will be generated.  * 
 * Then u need to create a index file, some thing like default.html 				 *
 * refer to the kindle books that i made, create the file structure and generate     * 
 * 																					 *
 * 					Kindle book														 *
 * ***********************************************************************************
 * 
 */
public class Ebook {
	//this is the html file going to be generated.
	private String bookFileName = "hymnsOfLife%s.html";
	
	private String headerMetaInfo = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=big5\" />";
	private String[] links = {
			"http://www.christianstudy.com/data/hymns/text/life001.html",
			"http://www.christianstudy.com/data/hymns/text/life002.html",
			"http://www.christianstudy.com/data/hymns/text/life535.html",
			"http://www.christianstudy.com/data/hymns/text/life536.html",
	};
	
	

	private String prefix = "<CENTER>";
	private String suffix = "<script language=\"JavaScript\">";
	
	private int chapterIntervals = 3000;
	
	public static void main(String[] args) {
		Ebook ebook = new Ebook();
		/*
		for (int i=1; i<=536 ; i++){
			String si = "" + i;
			while (si.length()< 3){
				si = "0" + si; 
			}
				
				//	<item id="item1" media-type=text/x-oeb1-document" href="deshengebook01.html"></item>
					
					//<itemref idref="item0"/>
				 
			
			System.out.println("\"http://www.christianstudy.com/data/hymns/text/life" + si + ".html\"" + ",");
			
		}
		
		*/
		System.out.println("------Done-----");
	}

	public Ebook() {
		for (int i=1; i<= links.length; i++) {
			StringBuffer finalResult = new StringBuffer();

			finalResult.append(String.format("<html><head>%s</head><body>", headerMetaInfo));
			
			String link = (String) links[i-1];

			try {
				String pageContent = getPageDetails(link);
				finalResult.append("<p>");
				finalResult.append(pageContent);
				finalResult.append("<p>");

				Thread.sleep(chapterIntervals); // sleep for  mil-second before retrieving the page
									

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			finalResult.append("</body></html>");
			
			String si = "" + i;
			while (si.length()<3){
				si = "0" + si;
			}
			FileUtil.writeFile(finalResult.toString(), String.format(bookFileName, si));
			System.out.println("Done with " + link);
		}

		
		
	}

	

	/*
	 * Has the http link of a page, get the content as a formatted html
	 * string
	 */
	private String getPageDetails(String link) {
		String pageFullContent = getWebPageInFullHttpResponse(link);
		
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

		index = input.indexOf(prefix) ;

		input = input.substring(index);

		index = input.indexOf(suffix);

		part = input.substring(0, index);

		return part;
	}

}
