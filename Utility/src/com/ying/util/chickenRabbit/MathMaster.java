package com.ying.util.chickenRabbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MathMaster {
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ArrayList<Question> al = new ArrayList();
		
		String questions = "";
		String answers = "";
		
		int loop = 2;
		
		//load the questions
//		loadFractionQuestions(al, loop);
		loadCategory1Questions(al, loop);
//		loadCategory2Questions(al, loop);
		
		//shuffle the questions.
		Collections.shuffle(al);
		
		for (int i=0; i<al.size(); i++){
			Question q = (Question)al.get(i);
			int count = i+1;
			questions = questions +  count + "> \n" + q.getQuestion() + "\n\n";
			
			answers = answers + count + "> \n" + q.getAnswer() + "\n\n";
		}
		
		System.out.println(questions + "\n\n\n\n");
		System.out.println(answers);
	}
	
	private static void loadFractionQuestions(ArrayList al, int loop) {
		if (loop <1) return;
		
		FractionQuestions fq = new FractionQuestions();
		for (int i=0; i<loop; i++ ){
			al.add(fq.FractionGen());
			al.add(fq.FractionGen1());
			al.add(fq.BrickGen());
			al.add(fq.HarryPotterGen());
			al.add(fq.ChickenGen());
		}
	}
	
	private static void loadCategory1Questions(ArrayList al, int loop) {
		if (loop <1) return;
		
		Category1 c1 = new Category1();
		
		for (int i=0; i<loop; i++ ){
			al.add(c1.FruitGen());
			al.add(c1.TeaGen());
			al.add(c1.bottleGen());
			al.add(c1.coinGen());
			al.add(c1.chickenRabbitGen());
			al.add(c1.chickenRabbitGen2());
			al.add(c1.SquirrelGen());		
			al.add(c1.redBluePencilGen());
			al.add(c1.chessGen());
			
			al.add(c1.balloonGen());
			al.add(c1.stampGen());
			al.add(c1.mathExamGen());
			al.add(c1.mathGen2 ());
			al.add(c1.projectGen());
			al.add(c1.typingGen());
			/*
			//3 variables
			al.add(c1.insectGen());
			al.add(c1.snackGen());
			*/
		}
	}

	//3 variables, 2 equations
	private static void loadCategory2Questions(ArrayList al, int loop) {
		if (loop <1) return;
		
		Category2 c2 = new Category2();
		
		for (int i=0; i<loop; i++ ){

			al.add(c2.mathGen3());
			al.add(c2.busTripGen() );
		}
	}
}
