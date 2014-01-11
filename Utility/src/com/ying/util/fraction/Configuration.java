package com.ying.util.fraction;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.xpath.AXIOMXPath;

public class Configuration {

	private int style;
	public int getStyle() {
		return style;
	}

	public String[] getOperators() {
		return operators;
	}

	public int getOperatorsCount() {
		return operatorsCount;
	}

	public String getFormmaterName() {
		return formmaterName;
	}

	public int getMinf() {
		return minf;
	}

	public int getMaxf() {
		return maxf;
	}

	public int getMins() {
		return mins;
	}

	public int getMaxs() {
		return maxs;
	}

	private String[] operators ;
	private int operatorsCount ; 
	
	private String formmaterName;
	
	private int minf;
	private int maxf;
	private int mins;
	private int maxs;
	
	/**
	 * @param args
	 */
	public Configuration(int set) {

		try {
	    	File file = new File("bin\\configs.xml");
	    	
			FileInputStream fis = new FileInputStream(file);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(fis);
	
			StAXOMBuilder builder = new StAXOMBuilder(reader);
			OMElement root = builder.getDocumentElement();
	
			String xpathConfig = "//MathGenerator//config[@set='" + set + "']";
			
			AXIOMXPath xstyle = new AXIOMXPath(xpathConfig + "//style");
			OMElement eStyle = (OMElement) xstyle.selectSingleNode(root);
			style = Integer.parseInt(eStyle.getText());
	
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
			
			AXIOMXPath xFirstNumberMax = new AXIOMXPath(xpathConfig + "//numbers//firstNumber//max");
			OMElement eFirstNumberMax = (OMElement) xFirstNumberMax.selectSingleNode(root);
			maxf = Integer.parseInt(eFirstNumberMax.getText());
			
			AXIOMXPath xFirstNumberMin = new AXIOMXPath(xpathConfig + "//numbers//firstNumber//min");
			OMElement eFirstNumberMin = (OMElement) xFirstNumberMin.selectSingleNode(root);
			minf = Integer.parseInt(eFirstNumberMin.getText());
			
			AXIOMXPath xSecondNumberMax = new AXIOMXPath(xpathConfig + "//numbers//secondNumber//max");
			OMElement eSecondNumberMax = (OMElement) xSecondNumberMax.selectSingleNode(root);
			maxs = Integer.parseInt(eSecondNumberMax.getText());
			
			AXIOMXPath xSecondNumberMin = new AXIOMXPath(xpathConfig + "//numbers//secondNumber//min");
			OMElement eSecondNumberMin = (OMElement) xSecondNumberMin.selectSingleNode(root);
			mins = Integer.parseInt(eSecondNumberMin.getText());
	
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
