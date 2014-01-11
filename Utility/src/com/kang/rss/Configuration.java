package com.kang.rss;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.xpath.AXIOMXPath;

public class Configuration {

	
	private String link;
	private String fileName;
	private String prefix;
	private String suffix;
	private String charset;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPrefix() {
		return prefix.replace("&gt;", ">").replace("&lt;", "<");
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix.replace("&gt;", ">").replace("&lt;", "<");
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
}
