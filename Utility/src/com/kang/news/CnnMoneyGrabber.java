package com.kang.news;

import java.util.ArrayList;

import com.kang.util.FileUtil;
import com.kang.util.HttpUtil;
import com.kang.util.StringUtil;

public class CnnMoneyGrabber {

	public static void main(String[] args) {
		String siteLinkBase = "http://money.cnn.com";
		
		//after the following call, the http response is like: cnnMoneyGrabber_parent_code.jpg
		String parentPageFullContent = HttpUtil.getWebPageInFullHttpResponse(siteLinkBase);
		
		//Please refer to cnnMoneyGrabber_parent.jpg
		//		prefix is the Latest News's style class
		//		suffix is the text of "See all latest news" link
		String prefix = "boxHeading";
		String suffix = "See All Latest News";

		String myContent = parentPageFullContent.substring(parentPageFullContent
				.indexOf(prefix), parentPageFullContent.indexOf(suffix));
		
		//the following line is not needed because we are going to parse the links
		myContent = "<html><body>" + "<div class=\"" + myContent
				+ "See All Latest News</a></div>" + "</body></html>";

		//now the myContent is similar to the content in cnnMoneyGrabber_parent_html_formatted.jpg
		
		//System.out.println(myContent);
		
		parseLinks(myContent, siteLinkBase);
		
		System.out.println("--DONE--");
		
	}
	
	private static void parseLinks(String myHomepageContent, String siteLinkBase) {
		//since every link in the list is a "li" in the "ul" tag, we are going to get the lis
		String uiPrefix="<ul>";
		String uiSuffix="</ul>";
		
		int indexULPrefix = myHomepageContent.indexOf(uiPrefix) + uiPrefix.length();
		int indexULSuffix = myHomepageContent.indexOf(uiSuffix) ;
		
		String rawLis = myHomepageContent.substring(indexULPrefix, indexULSuffix);
		
		//after we have the lis as one complete string, we parse it and save lis in an arraylist
		ArrayList listLis = getLis(rawLis);
		
		//we retrieve the valid news html links from the lis.
		ArrayList listLinks = getValidLinks(listLis, siteLinkBase);
		
		//----------------------------------------------------------------------
		//format the final output as html, with the stylesheet      --- start
		String html = "<html>" + StringUtil.getStyleSheet() + "<body>";
		
		//loop through all the valid links and get news details
		for (int i=0; i<listLinks.size(); i++){
			try{
				Thread.sleep(3000);		//sleep for 3 second before retrieving the news
				String result = getNewsDetails((String)listLinks.get(i));
				html = html + result;
			}catch(Exception e){
				
			}
		}
		html = html + "</body></html>";
		
		//format the final output as html, with the stylesheet      --- end
		//----------------------------------------------------------------------
		
		//write out the news into a html file
		FileUtil.writeFile(html, StringUtil.getDateOfToday() + "_" + "moneyCnnCom" + ".html");
		
	}
	
	/*
	 * get the lis from the raw lis string and save them into the arraylist, including the invalid links
	 */
	private static ArrayList getLis(String rawLis){
		String liPrefix = "<li";
		String liSuffix = "</li>";
		
		ArrayList listLis = new ArrayList();
		
		//has more will always be true until there is no li tag anymore, then the while loop will be broken out
		boolean hasMore = true;
		while(hasMore){
			int indexLi = rawLis.indexOf(liPrefix);
			if (indexLi<0){
				break;
			}
			
			//the li string will contain the complete tag <li>......</li>
			//	such as: <li time="1233862218000">
			//				<a href="/2009/02/05/news/economy/jobs_outlook/index.htm?postversion=2009020514">
			//					The jobs problem you don't know about
			//				</a>
			//			 </li>
			String li = rawLis.substring(0, rawLis.indexOf(liSuffix) +liSuffix.length());
			
			//add to the arraylist
			listLis.add(li);
			
			//the parsed li will be removed by the substring function
			rawLis = rawLis.substring(rawLis.indexOf(liSuffix) +liSuffix.length());
		}
		
		return listLis;
	}
	
	/*
	 * if the li contains video links then remove it from the arraylist,
	 * 		for those lis which have [   alt="video"	]
	 */
	private static ArrayList getValidLinks(ArrayList listLis, String siteLinkBase){
		String invalidLinkToken = "alt=\"video\"";
		
		String litagPrefix = "<li time";
		
		//li string is something like:
		//	<li time="1233862218000"><a href="/2009/02/05/news/economy/jobs_outlook/index.htm?postversion=2009020514">The jobs problem you don't know about</a></li>
		int lilinkPrefixLength = 34;	//which is the length of [ <li time="1233862218000"><a href=" ]
		
		//we are using the double quote around the href value to get the links
		
		ArrayList listLinks = new ArrayList();
		
		for (int i =0; i< listLis.size(); i++){
			String li = (String)listLis.get(i);
			
			if(li.indexOf(invalidLinkToken)>0) {
				continue;
			}
			
			//if get to here, then the link is valid
			int indexLi = li.indexOf(litagPrefix);
			
			//we remove the open double quote for the href attribute and front
			li = li.substring(indexLi + lilinkPrefixLength);
			
			//the first letter of li now is a first character in a valid link, 
			//we do substring with the end position which is the end double quote's index.
			String link = li.substring(0, li.indexOf("\""));
			
			//now we add the link to the arraylist
			listLinks.add(siteLinkBase + link);
		}
		
		return listLinks;
	}
	
	/*
	 * Has the http link of a news, get the news content as a formatted html string 
	 */
	private static String getNewsDetails (String link){
		String titlePrefix = "storyheadline\">";
		String titleSuffix = "</h1>";
		
		String summaryPrefix = "storysubhead\">";
		String summarySuffix = "</h2>";
		
		String newsContentPrefix = "<!--endclickprintexclude--><!-- /REAP -->";
		String newsContentSuffix = "<a href=\"#TOP\">";
		
		String HTML_TITLE_FORMAT_PREFIX = "<br><font class=newsTitle><b><p>"; 
		String HTML_TITLE_FORMAT_SUFFIX = "</p></b></font>";
		
		String HTML_SUMMARY_FORMAT_PREFIX = "<font class=newsSummary><b><i><p>" ;
		String HTML_SUMMARY_FORMAT_SUFFIX = "</p></i></b></font>" ;
		
		String HTML_NEWS_FORMAT_PREFIX = "<font class=newsContent>" ;
		String HTML_NEWS_FORMAT_SUFFIX = "</font><br>#######################";
		
		//the full http response for the news detail
		String pageContent; 			
		
		//get the news web page by http call and save it to a string
		pageContent = HttpUtil.getWebPageInFullHttpResponse(link);
		
		//below will get the title from string starts like: 
		//			<h1 class="storyheadline">GDP dropped 0.5% this summer</h1>
		String title = StringUtil.getWebPart(pageContent, titlePrefix, titleSuffix);
		
		/*
		 * below will get the summary from a string starts like:
		<h2 class="storysubhead">
			GDP drops 0.5% in third quarter, according to final revision from government; 
			figure remains unchanged from prior revision.
		</h2>
		*/
		String summary = StringUtil.getWebPart(pageContent, summaryPrefix, summarySuffix);
		
		//below will get the news detail
		String news = StringUtil.getWebPart(pageContent, newsContentPrefix, newsContentSuffix);
		
		//these are for debugging purpose
		System.out.println("");
		System.out.println(title);
		System.out.println(summary);
		System.out.println(news);
		
		System.out.println("############################");
		
		String result = HTML_TITLE_FORMAT_PREFIX 	+ 	title 	+ HTML_TITLE_FORMAT_SUFFIX 		+
						HTML_SUMMARY_FORMAT_PREFIX 	+ 	summary + HTML_SUMMARY_FORMAT_SUFFIX 	+ 
						HTML_NEWS_FORMAT_PREFIX 	+ 	news + HTML_NEWS_FORMAT_SUFFIX		;
		
		return result;
	}
}
