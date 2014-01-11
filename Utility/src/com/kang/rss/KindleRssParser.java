package com.kang.rss;

import java.util.List;

public class KindleRssParser {

	private RssChannelBean channel;
	private RssImageBean image;
	private List<RssItemBean> items;
	
	public KindleRssParser (String rssLink) {
		fileParse(rssLink, null);
	}
	
	public RssChannelBean getChannel() {
		return channel;
	}

	public RssImageBean getImage() {
		return image;
	}

	public List<RssItemBean> getItems() {
		return items;
	}


	public static void main(String args[]){
		
		
	}
	
	public void fileParse(String filename, String charset){
		RssParser rss = new RssParser(filename);
		if (charset!=null) rss.setCharset(charset);
		try {
			RssFeed obj = rss.load();
			this.channel = obj.getChannel(); //Gets the channel element
			this.items = obj.getItems(); //Gets a List of RssItemBean
			this.image = obj.getImage(); //Gets the image element
		}catch(Exception e){
			e.printStackTrace();
		}				
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Channel: " + channel.getTitle() + "\n");
		sb.append(channel.getPubDate());
		
		for (int i = 0; i< items.size(); i++){
			sb.append("\n");
			RssItemBean item = (RssItemBean)items.get(i);
			sb.append(item.getTitle() + "\n");
			sb.append(item.getDescription() + "\n");
			sb.append(item.getLink() +"\n");
			sb.append(item.getPubDate() + "\n");
			sb.append(item.getAuthor() + "\n");
		}
		
		
		return sb.toString();
	}
}
