package com.ying.util.fraction;

/***************************************************************
 * History: 
 * 		1/14/2015 - add variable isComplicated to represent 复分数 when value is true
 **************************************************************/


import java.io.*;
import java.lang.Math;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.xpath.AXIOMXPath;

import com.kang.util.FileUtil;

/*
 * To get a number beween max and min, including max or min itself, use the following code:
 * 			Min + (int)(Math.random() * ((Max - Min) + 1))
 */
public class QuestionGenerator {
//	private static boolean isShufflable = false;		//set to true when you want to massage multiple sets of questions.
	
	private int total;
	
	private String[] questions;
	
	public String[] getQuestions() {
		return questions;
	}

	//1-straight forward; 0-fill the blank.
	private int style;		
	
	private String[] operators ;
	private int operatorsCount ; 
	
	private int tminf;
	private int tmaxf;
	private int bminf;
	private int bmaxf;
	private int tmins;
	private int tmaxs;
	private int bmins;
	private int bmaxs;
	private int wholef;
	private int wholes;
	
	private boolean isComplicated = false;
	
	private static final String OPERATOR_ADDITION = "+";
	private static final String OPERATOR_SUBTRACTION = "-";
	private static final String OPERATOR_MULTIPLICATION = "�";
	private static final String OPERATOR_DIVISION = "�";
	
	private static boolean debug = true;
	
	public QuestionGenerator(int set, int total)
	{
		try {
			this.total = total;
			questions = new String[total];
			
        	File file = new File("bin\\com\\ying\\util\\fraction\\fractionconfigs.xml");
        	
			FileInputStream fis = new FileInputStream(file);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(fis);

			StAXOMBuilder builder = new StAXOMBuilder(reader);
			OMElement root = builder.getDocumentElement();

			String xpathConfig = "//MathGenerator//config[@set='" + set + "']";
			
			AXIOMXPath xstyle = new AXIOMXPath(xpathConfig + "//style");
			OMElement eStyle = (OMElement) xstyle.selectSingleNode(root);
			
			AXIOMXPath xoperator = new AXIOMXPath(xpathConfig + "//operators//operator");
			List eOperators = xoperator.selectNodes(root);
			operatorsCount = eOperators.size();
			operators = new String[operatorsCount];
			
			for (int i= 0 ; i< eOperators.size(); i++){
				operators[i] = ((OMElement)eOperators.get(i)).getText();
				System.out.println(operators[i]);
			}
			
			AXIOMXPath xFirstNumberTopMax = new AXIOMXPath(xpathConfig + "//numbers//firstNumber//topmax");
			OMElement eFirstNumberTopMax = (OMElement) xFirstNumberTopMax.selectSingleNode(root);
			tmaxf = Integer.parseInt(eFirstNumberTopMax.getText());
			
			AXIOMXPath xFirstNumberTopMin = new AXIOMXPath(xpathConfig + "//numbers//firstNumber//topmin");
			OMElement eFirstNumberTopMin = (OMElement) xFirstNumberTopMin.selectSingleNode(root);
			tminf = Integer.parseInt(eFirstNumberTopMin.getText());
			
			AXIOMXPath xFirstNumberBottomMax = new AXIOMXPath(xpathConfig + "//numbers//firstNumber//bottommax");
			OMElement eFirstNumberBottomMax = (OMElement) xFirstNumberBottomMax.selectSingleNode(root);
			bmaxf = Integer.parseInt(eFirstNumberBottomMax.getText());
			
			AXIOMXPath xFirstNumberBottomMin = new AXIOMXPath(xpathConfig + "//numbers//firstNumber//bottommin");
			OMElement eFirstNumberBottomMin = (OMElement) xFirstNumberBottomMin.selectSingleNode(root);
			bminf = Integer.parseInt(eFirstNumberBottomMin.getText());
			
			AXIOMXPath xSecondNumberTopMax = new AXIOMXPath(xpathConfig + "//numbers//secondNumber//topmax");
			OMElement eSecondNumberTopMax = (OMElement) xSecondNumberTopMax.selectSingleNode(root);
			tmaxs = Integer.parseInt(eSecondNumberTopMax.getText());
			
			AXIOMXPath xSecondNumberTopMin = new AXIOMXPath(xpathConfig + "//numbers//secondNumber//topmin");
			OMElement eSecondNumberTopMin = (OMElement) xSecondNumberTopMin.selectSingleNode(root);
			tmins = Integer.parseInt(eSecondNumberTopMin.getText());
			
			AXIOMXPath xSecondNumberBottomMax = new AXIOMXPath(xpathConfig + "//numbers//secondNumber//bottommax");
			OMElement eSecondNumberBottomMax = (OMElement) xSecondNumberBottomMax.selectSingleNode(root);
			bmaxs = Integer.parseInt(eSecondNumberBottomMax.getText());
			
			AXIOMXPath xSecondNumberBottomMin = new AXIOMXPath(xpathConfig + "//numbers//secondNumber//bottommin");
			OMElement eSecondNumberBottomMin = (OMElement) xSecondNumberBottomMin.selectSingleNode(root);
			bmins = Integer.parseInt(eSecondNumberBottomMin.getText());
			
			AXIOMXPath xIscomplicated = new AXIOMXPath(xpathConfig + "//isComplicated");
			OMElement eIscomplicated = (OMElement) xIscomplicated.selectSingleNode(root);
			if (Integer.parseInt(eIscomplicated.getText())==1) {
				isComplicated = true;
			}
			
			println(tmaxf);
			println(tminf);
			println(bmaxf);
			println(bminf);
			println(tmaxs);
			println(tmins);
			println(bmaxs);
			println(bmins);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		int i = 0;
		
		//if the last array element is not filled with a valid question, keep looping.
		while (questions[total - 1]==null || questions[total -1].equals("")){
			String tempQuestion = getQuestion();
			
			//for the "-" operator, eliminate the questions that having the number equals to each other
			if (tempQuestion==null){
				continue;
			}
			
			//make sure the randomly generated question won't repeat in the last 6 occurrence
			if (i>6){
				if (tempQuestion.equals(questions[i-1]) || tempQuestion.equals(questions[i-2]) || tempQuestion.equals(questions[i-3]) ||
						tempQuestion.equals(questions[i-4]) || tempQuestion.equals(questions[i-5]) || tempQuestion.equals(questions[i-6])){
					continue;
				}
			}
			
			questions[i] = tempQuestion;
			i ++;
		}
	}
	
	private String getQuestion() {
		int firstNumbertop = getNumberInRange(tminf, tmaxf);
		int firstNumberbottom = getNumberInRange(bminf, bmaxf);
		
		String operator = getOperator();
		int secondNumbertop = getNumberInRange(tmins, tmaxs);
		int secondNumberbottom = getNumberInRange(bmins, bmaxs);

//		boolean isValid = isValidQuestion(firstNumbertop, firstNumberbottom, operator, secondNumbertop, secondNumberbottom);
//		while (!isValid){
//			System.out.println(firstNumber + "" + operator + secondNumber +  " is not valid");
//			secondNumber = getNumberInRange(mins, maxs);
//			if (isValidQuestion(firstNumber, operator, secondNumber)){
//				isValid = true;
//			}
//		}
		
		String fQuestion = formatQuestion(firstNumbertop, firstNumberbottom, operator, secondNumbertop, secondNumberbottom); 
		return fQuestion ;
	}
	
	private boolean isValidQuestion(int firstNumbertop, int firstNumberbottom, String operator, 
						int secondNumbertop, int secondNumberbottom) {
		return true;
		
//		if (operator.equals(OPERATOR_ADDITION) && (firstNumber + secondNumber <= summax)){
//			return true;
//		} else if (operator.equals(OPERATOR_SUBTRACTION) && (firstNumber >= secondNumber)){
//			return true;
//		} else if (operator.equals(OPERATOR_MULTIPLICATION) || operator.equals(OPERATOR_DIVISION)){
//			return true;
//		}
//			
//		return false;
	}

	private String formatQuestion(int firstNumbertop, int firstNumberbottom, String operator, 
			int secondNumbertop, int secondNumberbottom){
		
		Fraction left = new Fraction(getWholeNumber(), firstNumbertop, firstNumberbottom);
		Fraction right = new Fraction(getWholeNumber(), secondNumbertop, secondNumberbottom);
		
		if (!isComplicated) {
			left.setWhole(0);
			right.setWhole(0);
		}

		if (operator.equals(OPERATOR_SUBTRACTION)){
			if (left.compare(right) <0) {
				Fraction temp = right;
				right =left; 
				left = temp;
			}
		}
		
		if (left.getBottom() == left.getTop() || right.getBottom()==right.getTop()){
			return null;
		}
		
		if (left.getBottom() == 0 || left.getTop() ==0 ){
			return null;
		}
		
		if (right.getBottom() == 0 || right.getTop() ==0 ){
			return null;
		}
		
		String fOperator = " " + operator + " ";
		
		println(left.toString() + fOperator + right.toString());
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellspacing=0 cellpadding=0>");
		sb.append("<tr >");
		if (left.getWhole()!=0){
			sb.append("<td align=right width=30px>").append(left.getWhole()).append("</td>");
		} else {
			sb.append("<td width=30px>&nbsp;</td>");
		}
		if (left.getTop() !=0 ) {
			sb.append("<td align=center><u> ").append(left.getTop()).append("</u><br>").append(left.getBottom()).append("</td>");	
		}else {
			sb.append("<td width=30px>&nbsp;</td>");
		}
		
		sb.append("<td align=center width=50px>").append(fOperator).append("</td>");
		
		if (right.getWhole()!=0){
			sb.append("<td align=right width=30px>").append(right.getWhole()).append("</td>");
		}else {
			sb.append("<td width=30px>&nbsp;</td>");
		}
		
		if (right.getTop()!=0){
			sb.append("<td align=center><u>").append(right.getTop()).append("</u><br>").append(right.getBottom()).append("</td>");	
		}else {
			sb.append("<td width=30px>&nbsp;</td>");
		}
		
		sb.append("<td align=center>").append("    = ").append("</td>");
		sb.append("</tr></table>");
		
		return sb.toString();
//		if (operator.equals(OPERATOR_SUBTRACTION) || operator.equals(OPERATOR_ADDITION) || operator.equals(OPERATOR_MULTIPLICATION)) {
//			return firstNumber + fOperator + secondNumber + " = ";
//		} else {
//			return (firstNumber * secondNumber + fOperator+ firstNumber + " = ");
//		}
		
	}
//	
//	/*
//	 * returns a number in range of min and max, including min and max.
//	 */
//	private int getFirstNumber(int min, int max) {
//		return getNumberInRange(min, max);
//	}
	
//	private String simplify(int top, int bottom){
////		if 
//	}
	
	private String getOperator(){
		//get a random number between 1 and 0 to determine the operator 
		int number = getNumberInRange(1, operatorsCount);
		
		String operator = operators[number -1];
		
		return operator;
	}

	private int getWholeNumber(){
		int r = getNumberInRange(1, 5);
		if (r % 5 ==0 ){
			return getNumberInRange(1,7);
		}
		
		return 0;
		
	}
	
	
	/*
	 * returns a number in range of min and max, including min and max.
	 */
	private int getNumberInRange(int min, int max) {
		int number = min + (int)(Math.random() * ((max - min) + 1));
		return number;
	}
	
	private void println(String s){
		if (debug){
			System.out.println(s);
		}
	}
	
	private void println(int i){
		if (debug){
			System.out.println(i);
		}
	}
}


