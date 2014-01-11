package com.ying.util.math;

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
	
	private int minf;
	private int maxf;
	private int mins;
	private int maxs;
	
	private int summax;

	private static final String OPERATOR_ADDITION = "+";
	private static final String OPERATOR_SUBTRACTION = "-";
	private static final String OPERATOR_MULTIPLICATION = "×";
	private static final String OPERATOR_DIVISION = "÷";
	
	private static boolean debug = false;
	
	public QuestionGenerator(int set, int total)
	{
		try {
			this.total = total;
			questions = new String[total];
			
        	File file = new File("bin\\com\\ying\\util\\decimal\\configs.xml");
			
			//File file = new File("bin\\configs.xml");
        	
			FileInputStream fis = new FileInputStream(file);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(fis);

			StAXOMBuilder builder = new StAXOMBuilder(reader);
			OMElement root = builder.getDocumentElement();

			String xpathConfig = "//MathGenerator//config[@set='" + set + "']";
			
			AXIOMXPath xstyle = new AXIOMXPath(xpathConfig + "//style");
			OMElement eStyle = (OMElement) xstyle.selectSingleNode(root);
			style = Integer.parseInt(eStyle.getText());

			println(style);
			
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
			
			AXIOMXPath xSumMax = new AXIOMXPath(xpathConfig + "//numbers//sumMax");
			OMElement eSumMax = (OMElement) xSumMax.selectSingleNode(root);
			summax = Integer.parseInt(eSumMax.getText());
			
			

			println(maxf);
			println(minf);
			println(maxs);
			println(mins);
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
		int firstNumber = getNumberInRange(minf, maxf);
		String operator = getOperator();
		int secondNumber = 0;
		
		//if (mins == maxs && mins ==25) {		//this is for set 45, for multiplication as calculate the square.
			//secondNumber = firstNumber; 
		//} else {
			secondNumber  = getNumberInRange(mins, maxs);
		//}
		
		boolean isValid = isValidQuestion(firstNumber, operator, secondNumber);
		while (!isValid){
			System.out.println(firstNumber + "" + operator + secondNumber +  " is not valid");
			secondNumber = getNumberInRange(mins, maxs);
			if (isValidQuestion(firstNumber, operator, secondNumber)){
				isValid = true;
			}
		}
		
		return formatQuestion(firstNumber, operator, secondNumber);
	}
	
	private boolean isValidQuestion(int firstNumber, String operator, int secondNumber) {
		
		if (operator.equals(OPERATOR_ADDITION) && (firstNumber + secondNumber <= summax)){
			return true;
		} else if (operator.equals(OPERATOR_SUBTRACTION) && (firstNumber >= secondNumber)){
			return true;
		} else if (operator.equals(OPERATOR_MULTIPLICATION) || operator.equals(OPERATOR_DIVISION)){
			return true;
		}
			
		return false;
	}

	private String formatQuestion(int firstNumber,String operator, int secondNumber){
		String fOperator = " " + operator + " ";
		String blank = "_";
		String OPERATOR_ADDITION_REVERSE = " - ";
		String OPERATOR_SUBTRACTION_REVERSE = " + ";
		String OPERATOR_MULTIPLICATION_REVERSE = " ÷ ";
		String OPERATOR_DIVISION_REVERSE = " × ";
		
		if (style == 1){
			if (operator.equals(OPERATOR_SUBTRACTION)){
				if  (firstNumber==secondNumber) { 
					return null;
				}
			}
			
			if (operator.equals(OPERATOR_SUBTRACTION) || operator.equals(OPERATOR_ADDITION) || operator.equals(OPERATOR_MULTIPLICATION)) {
				return firstNumber + fOperator + secondNumber + " = ";
			} else {
				return (firstNumber * secondNumber + fOperator+ firstNumber + " = ");
			}
		} else {
			//fill the blank type
			int choice = getNumberInRange(1, 2);
			
			if (operator.equals(OPERATOR_SUBTRACTION)) {
				int result = firstNumber - secondNumber;
				
				if (choice == 1){
					return secondNumber + OPERATOR_SUBTRACTION_REVERSE  + blank + " = " + firstNumber;  
				} else {
					return result + OPERATOR_SUBTRACTION_REVERSE  + blank + " = " + firstNumber ;
				}
			} else if (operator.equals(OPERATOR_ADDITION) ) {
				int result = firstNumber + secondNumber;
				
				if (choice == 1){
					return result + OPERATOR_ADDITION_REVERSE  + blank + " = " + firstNumber;  
				} else {
					return result + OPERATOR_ADDITION_REVERSE  + blank + " = " + secondNumber ;
				}
			}	else {
				int result = firstNumber * secondNumber;
				return result + OPERATOR_MULTIPLICATION_REVERSE +  blank + " = " + secondNumber;  
			}
		}
	}
//	
//	/*
//	 * returns a number in range of min and max, including min and max.
//	 */
//	private int getFirstNumber(int min, int max) {
//		return getNumberInRange(min, max);
//	}
	
	private String getOperator(){
		//get a random number between 1 and 0 to determine the operator 
		int number = getNumberInRange(1, operatorsCount);
		
		String operator = operators[number -1];
		
		return operator;
	}
	
//	private int getSecondNumber(int min, int max, int first, String operator){
//		if (operator.equals(OPERATOR_ADDITION)) {
//			//calculate the 2nd number according to the max
//			int max2 = max - first - 1;
//			if (max2==0) return 0;
//			return getNumberInRange(1, max2);
//			
//		} else if (operator.equals(OPERATOR_SUBTRACTION)) {
//			//" - " 
//			int max2 = first;
//			return getNumberInRange(1, max2);
//		} else if (operator.equals(OPERATOR_MULTIPLICATION)) {
//			return getNumberInRange(min , max-1);
//		} else {
//			return getNumberInRange(min , max-1);
//		}
//	}
	
//	private int getSecondNumber(int min, int max, int first, String operator){
//		if (operator.equals(OPERATOR_ADDITION)) {
//			//calculate the 2nd number according to the max
//			int max2 = max - first;;
//			
//			if (min<max2) {
//				return getNumberInRange(min, max2);
//			} else {
//				return getNumberInRange(max2, min);
//			}
//		} else if (operator.equals(OPERATOR_SUBTRACTION)) {
//			//" - " 
//			int max2 = first;
//			if (min<max2) {
//				return getNumberInRange(min, max2);
//			} else {
//				return getNumberInRange(max2, min);
//			}
//		} else if (operator.equals(OPERATOR_MULTIPLICATION)) {
//			return getNumberInRange(min , max-1);
//		} else {
//			return getNumberInRange(min , max-1);
//		}
//	}
	
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


