package com.ying.util.decimal;

import java.util.Arrays;
import java.util.Collections;

import com.kang.util.FileUtil;

public class MathMaster {
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//mix a set of single digit x* questions with a set 2 digits +- questions.
		int page = 100;
		
		//maximum to 20 addition and subtraction
		String [] sets = {"1"};
		
		printMixedSets(sets, page);
		System.out.println("DONE");
	}
	
	private static void printSingleSet(int set, int page){
		Configuration config = new Configuration(set);
		QuestionFormatter formatter =  new HTML3ColumnFormatter(); 
		
		int row = formatter.getRowCount();
		int column = formatter.getColumnCount();
		
		QuestionGenerator generator = new QuestionGenerator(set, page*row*column);
		
		String[] questions = generator.getQuestions();
		
		String output = formatter.format(questions);
		
		String filename = "SET-" + set + "-Questions.html";
		
		FileUtil.writeFile(output, filename);
		System.out.println("DONE");
	}
	
	private static void printMixedSets(String[] sets, int page){
		
		//determine the formatter by the first set configuration.
		int setTemp = Integer.parseInt(sets[0]);
		Configuration config = new Configuration(setTemp);
		QuestionFormatter formatter =null;
		if (config.getFormmaterName().equalsIgnoreCase("HTML3ColumnFormatter")){
			formatter = new HTML3ColumnFormatter(); 
		} else if (config.getFormmaterName().equalsIgnoreCase("HTMLVertical3ColumnFormatter")){
			formatter = new HTMLVertical3ColumnFormatter(); 
		}
		
		
		int i = 0;
		
		int row = formatter.getRowCount();
		int column = formatter.getColumnCount();
		
		String[] fQuestions = new String [row*column*page*(sets.length)]; 
		int fqCount = 0;
		
		while (i< sets.length){
			
			int set = Integer.parseInt(sets[i]);
			QuestionGenerator generator = new QuestionGenerator(set, page*row*column);
			
			String[] questions = generator.getQuestions();
			
			for (int j=0; j<questions.length; j++){
				fQuestions[fqCount] = questions[j];
				fqCount++;
			}
			i++;
		}
		
		//mixed the questions from different sets together
		Collections.shuffle(Arrays.asList(fQuestions));
		
		String output = formatter.format(fQuestions);
		
		FileUtil.writeFile(output, "MixedQuestions.html");
		System.out.println("DONE");
		
	}
	

}
