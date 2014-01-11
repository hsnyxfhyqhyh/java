package com.ying.util.decimal;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.xpath.AXIOMXPath;

public class Configuration {

	
	public String[] getOperators() {
		return operators;
	}

	public int getOperatorsCount() {
		return operatorsCount;
	}

	public String getFormmaterName() {
		return formmaterName;
	}

	

	private String[] operators ;
	private int operatorsCount ; 
	
	private String formmaterName;
	
	
	
	/**
	 * @param args
	 */
	public Configuration(int set) {

		try {
	    	File file = new File("bin\\com\\ying\\util\\decimal\\configs.xml");
	    	
			FileInputStream fis = new FileInputStream(file);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(fis);
	
			StAXOMBuilder builder = new StAXOMBuilder(reader);
			OMElement root = builder.getDocumentElement();
	
			String xpathConfig = "//MathGenerator//config[@set='" + set + "']";
		
			AXIOMXPath xformat = new AXIOMXPath(xpathConfig + "//format");
			OMElement eFormat = (OMElement) xformat.selectSingleNode(root);
			formmaterName = eFormat.getText();
			
			AXIOMXPath xoperator = new AXIOMXPath(xpathConfig + "//operators//operator");
			List eOperators = xoperator.selectNodes(root);
			operatorsCount = eOperators.size();
			operators = new String[operatorsCount];
			
			for (int i= 0 ; i< eOperators.size(); i++){
				operators[i] = ((OMElement)eOperators.get(i)).getText();
				System.out.println(operators[i]);
			}
			
			
	
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
