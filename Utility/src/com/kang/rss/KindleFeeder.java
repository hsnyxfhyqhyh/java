package com.kang.rss;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.xpath.AXIOMXPath;

import com.kang.util.FileUtil;
import com.kang.util.HttpUtil;
import com.kang.util.MailUtil;
import com.kang.util.StringUtil;
import com.kang.util.ZipUtil;

public class KindleFeeder {

	static ArrayList configurations = new ArrayList();
	static String configurationFiles = "com\\kang\\rss\\rssConfigs.xml";
	static ArrayList oldNews = new ArrayList();
	static ArrayList todayNews = new ArrayList();
	static String oldNewsFile = "com\\kang\\rss\\oldNews.txt";
	
	static boolean hasNews = false;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//load the old news list from txt file
		oldNews = FileUtil.getContents(oldNews, new File(oldNewsFile));
		
		setConfigurations();
		ArrayList<String> filenames = new ArrayList();
		
		//loop through all the rss sources configued in the rssConfigs.xml
		for (int j = 0; j<configurations.size(); j++ ){
			Configuration config = (Configuration) configurations.get(j);
			System.out.println(config.getLink());
			
			String link = config.getLink();
			
			//south jersey link news has some problem.
			if(config.getLink().equals("http://www.courierpostonline.com/section/news01")){
				link = link + "?&template=rss_gd";
			}
			
			KindleRssParser kp = new KindleRssParser(link);
			
			StringBuffer sb = new StringBuffer();
			sb.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + config.getCharset() + "\" ></head><body>");
			
			StringBuffer sbNewsCollection = new StringBuffer();
			
			sb.append("<a name='top'><h1>" + kp.getChannel().getTitle() + "</h1></a>");
			
			for (int i = 0; i<kp.getItems().size(); i++) {
//			for (int i = 0; i<2; i++) {
				RssItemBean  item = kp.getItems().get(i);
				
				//if news link archived before, don't load it anymore
				if (!isNewsOld(item.getLink())){
					String news =null;
					try{
						System.out.println("Processing " + i);
						Thread.sleep(1000);		//sleep for 3 second before retrieving the news
						news = getItemContent(item.getLink(), i, config.getPrefix(), config.getSuffix());	
						
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					
					if (news!= null) {
						//set the boolean flag to true only if there are news 
						hasNews = true;
						
						sb.append("<a href='#news" + i + "'>##" + item.getTitle() + "</a><br>&nbsp;<br>");
						
						sbNewsCollection.append("<a name='news" + i +   "'/><a href='#top'>Go to top</a><div>");
						sbNewsCollection.append("<h2>" + item.getTitle() + "</h2><br>");
						sbNewsCollection.append(item.getPubDate() + "<br>");
						sbNewsCollection.append(news + "<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>");
					}
				}
				
			}
			
			sb.append(sbNewsCollection.toString());
			sb.append("</body></html>");
			
			String filename = String.format(config.getFileName(), getDate());
			filenames.add(filename);
			FileUtil.writeFile(sb.toString(), filename);
		}
		
		if (hasNews){
			//zip it and mail it.
			String zipFileName = String.format("news_%s.zip", getDate());
			ZipUtil.zipFiles(filenames.toArray(), zipFileName);
			MailUtil.SendMailWithAttachment(zipFileName, zipFileName);
			
			archiveLinks();
			System.out.println("DONE");
		} else {
			System.out.println("NO UPDATE");
		}
		
		
	}

	private static boolean isNewsOld(String link) {
		for (int i=0; i< oldNews.size(); i++){
			String oldLink = (String) oldNews.get(i);
			if (oldLink.trim().equals(link.trim())){
				return true;
			}
		}
		
		todayNews.add(link);
		return false;
	}
	
	private static void archiveLinks(){
		StringBuffer sb = new StringBuffer();
		
		if (todayNews.size()>0){
			for (int i=0; i< todayNews.size(); i++){
				sb.append((String) todayNews.get(i) + "\n");
			}
		} else {
			//if no new links return
			return;
		}
		
		sb.append("\n");
		
		for (int i=0; i< oldNews.size(); i++){
			sb.append((String) oldNews.get(i) + "\n");
		}
		
		FileUtil.writeFile(sb.toString(), oldNewsFile);
		
	}
	
	

	private static String getDate() {
		Calendar calendar = Calendar.getInstance();     
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String sDate = dateFormat.format(calendar.getTime());
		return sDate;
	}

	public static String getItemContent(String link, int index, String prefix, String suffix){
		String pageContent; 			
		pageContent = HttpUtil.getWebPageInFullHttpResponse(link +"?viewAll=y");
		
		try {
			String news = StringUtil.getWebPart(pageContent, prefix, suffix);
			return news;
		} catch(Exception e){
			return null;
		}
	}
	
	private static void setConfigurations(){
		try {
			File file = new File(configurationFiles);
	    	
			FileInputStream fis = new FileInputStream(file);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(fis);
	
			StAXOMBuilder builder = new StAXOMBuilder(reader);
			OMElement root = builder.getDocumentElement();
	
			String xpathConfig = "//RssFeeder//Config[@active='true']";
	
			AXIOMXPath xConfig = new AXIOMXPath(xpathConfig);
			List eConfigs = xConfig.selectNodes(root);
			
			for (int i=0; i<eConfigs.size(); i++){
				OMElement eConfig = (OMElement)eConfigs.get(i);
				Configuration config = new Configuration();
				
				Iterator itr = eConfig.getChildElements();
				while(itr.hasNext()){
					
					OMElement eChild = (OMElement) itr.next();
				
					if (eChild.getLocalName().equals("Link")){
						config.setLink(eChild.getText());
					}
					
					if (eChild.getLocalName().equals("FileName")){
						config.setFileName(eChild.getText());
					}
					
					if (eChild.getLocalName().equals("Prefix")){
						config.setPrefix(eChild.getText());
					}
					
					if (eChild.getLocalName().equals("Suffix")){
						config.setSuffix(eChild.getText());
					}
					
					if (eChild.getLocalName().equals("language")){
						config.setCharset(eChild.getText());
					}
				}
				configurations.add(config);
			}
		} catch (Exception e) {
	        e.printStackTrace();
		}
    }
}
