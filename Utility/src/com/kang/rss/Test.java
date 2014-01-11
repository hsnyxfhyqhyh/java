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
import com.kang.util.StringUtil;

public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getDate();
	}

	private static String getDate() {
		Calendar calendar = Calendar.getInstance();     
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String sDate = dateFormat.format(calendar.getTime());
		System.out.println(sDate);
		
		return "test";
	}

	}
