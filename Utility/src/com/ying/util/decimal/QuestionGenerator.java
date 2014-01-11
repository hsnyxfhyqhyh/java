package com.ying.util.decimal;

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
	
	private int min;
	private int max;
	private int decimalDigitsMax;
	
	private static final String OPERATOR_ADDITION = "+";
	private static final String OPERATOR_SUBTRACTION = "-";
	private static final String OPERATOR_MULTIPLICATION = "×";
	private static final String OPERATOR_DIVISION = "÷";
	
	private static boolean debug = true;
	
	public QuestionGenerator(int set, int total)
	{
		try {
			this.total = total;
			questions = new String[total];
			
        	File file = new File("bin\\com\\ying\\util\\decimal\\configs.xml");
        	
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
			
			AXIOMXPath xMin = new AXIOMXPath(xpathConfig + "//numbers//min");
			OMElement eMin = (OMElement) xMin.selectSingleNode(root);
			min = Integer.parseInt(eMin.getText());
			
			AXIOMXPath xMax = new AXIOMXPath(xpathConfig + "//numbers//max");
			OMElement eMax = (OMElement) xMax.selectSingleNode(root);
			max = Integer.parseInt(eMax.getText());
			
			AXIOMXPath xDecimalDigitsMax = new AXIOMXPath(xpathConfig + "//numbers//decimalDigitsMax");
			OMElement eDecimalDigitsMax = (OMElement) xDecimalDigitsMax.selectSingleNode(root);
			decimalDigitsMax = Integer.parseInt(eDecimalDigitsMax.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		int i = 0;
		
		//if the last array element is not filled with a valid question, keep looping.
		while (questions[total - 1]==null || questions[total -1].equals("")){
			String tempQuestion = getQuestion();
			
			//for the "-" operator, eliminate the questions that having the number equals to each other
			if (tempQuestion==null){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				println("not valid");
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
			
			println(tempQuestion);
			i ++;
		}
	}
	
	private String getQuestion() {
		String operator = getOperator();
		
		int originalNumber2 = getNumberInRange(min, max);
		int digitsAfterDot2 = getNumberInRange(1, decimalDigitsMax);
		String ds2 = getDecimal(digitsAfterDot2, originalNumber2);
		
		com.ying.util.decimal.Decimal second = new Decimal(ds2, originalNumber2);
		
		int originalNumber1 = getNumberInRange(min, max);
		
		if (operator.equals(OPERATOR_DIVISION)) {
			originalNumber1 = originalNumber1 * originalNumber2;
		}
		
		int digitsAfterDot1 = getNumberInRange(1, decimalDigitsMax);
		String ds1 = getDecimal(digitsAfterDot1, originalNumber1);
		
		com.ying.util.decimal.Decimal first = new Decimal(ds1, originalNumber1);
		
		boolean isValid = false;
		if (operator.equals(OPERATOR_ADDITION) && first.canPlus(second)){
			isValid = true;
		} else if (operator.equals(OPERATOR_SUBTRACTION) && first.canMinus(second)){
			isValid = true;
		} else if (operator.equals(OPERATOR_MULTIPLICATION) && first.canMultiply(second)){
			isValid = true;
		} else if (operator.equals(OPERATOR_DIVISION) && first.canDivide(second)){
			isValid = true;
		}
		
		if (isValid) {
			return formatQuestion(ds1, operator, ds2);
		} else if (operator.equals(OPERATOR_SUBTRACTION)) {
			return formatQuestion(ds2, operator, ds1);
		}
		
		return null;
	}
	
	private String formatQuestion(String ds1, String operator, String ds2){
		
		return ds1 + " " + operator + " " + ds2 + " = ";
	}

	private String getOperator(){
		//get a random number between 1 and 0 to determine the operator 
		int number = getNumberInRange(1, operatorsCount);
		
		String operator = operators[number -1];
		
		return operator;
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
	
	private static String getDecimal(int digitsAfterDecimal, int number) {
		String sn = String.valueOf(number);
		
		if (digitsAfterDecimal==0) {
			//do nothing, return original
		} else {
			if (sn.length() > digitsAfterDecimal) {
				sn = sn.substring(0, sn.length() - digitsAfterDecimal) + "." + sn.substring(sn.length() - digitsAfterDecimal);
			} else if (sn.length() == digitsAfterDecimal){
				sn = "0." + sn;
			} else {
				while (sn.length()<= digitsAfterDecimal){
					sn = "0" + sn;
				}
				
				sn = sn.substring(0, sn.length() - digitsAfterDecimal) + "." + sn.substring(sn.length() - digitsAfterDecimal);
			}
		}
		
		return sn;
	}
}


