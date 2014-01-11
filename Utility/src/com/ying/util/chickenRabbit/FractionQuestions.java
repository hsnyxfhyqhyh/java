package com.ying.util.chickenRabbit;

import java.util.ArrayList;

public class FractionQuestions extends QuestionTemplate{
	public Question FractionGen () {
		int x = getNumberInRange(10, 20);
		x = 10 * x;
		
		int _1stTime = x * getNumberInRange(2, 4);
		
		int total = x * getNumberInRange(7, 10);
		
		int _2ndTime = getNumberInRange(3, 10)* 10;
		
		int leftOver = total - _1stTime - _2ndTime;
		
		Fraction _1stFraction = new Fraction (0, _1stTime, total);
		Fraction _2ndFraction = new Fraction (0, _1stTime + _2ndTime, leftOver);
		
		_1stFraction.transform();
		_2ndFraction.transform();
		
		String questionTemplate = "At the beginning of year 2011, Katherine bought a lot of candies, " + lineBreak() +
									"Katherine ate %s of the total candies in the first half of the year 2011, " + lineBreak() +
									"then Katherine ate %s pieces of candies in the 2nd half of the year 2011. We " + lineBreak() +
									"also know in the whole year 2011 the total candies that katherine ate is %s of " + lineBreak() +
									"the total candies left over. Please find out how many candies that Katherine bought " + lineBreak() +
									"at the beginning of year 2011";

		Question q = new Question();
		q.setAnswer("ANSWER: total - " + total );
		q.setQuestion(String.format(questionTemplate, _1stFraction, _2ndTime, _2ndFraction));
		return q;
	}
	
	public Question FractionGen1 () {
		int x1 = getNumberInRange(3, 10);
		int z1 = getNumberInRange(1, 2);
		int y1 = x1 - z1;
		
		int x2 = getNumberInRange(3, 10);
		int z2 = getNumberInRange(1, 2);
		int y2 = x2 - z2;

		int x3 = getNumberInRange(3, 10);
		int z3 = getNumberInRange(1, 2);
		int y3 = x3 - z3;

		int x4 = getNumberInRange(3, 10);
		int z4 = getNumberInRange(1, 2);
		int y4 = x4 - z4;
		
		Fraction f1 = new Fraction (0, z1, x1);
		Fraction f2 = new Fraction (0, z2, x2);
		Fraction f3 = new Fraction (0, z3, x3);
		Fraction f4 = new Fraction (0, z4, x4);
		
		f1.transform();
		f2.transform();
		f3.transform();
		f4.transform();
		
		int leftover = y1*y2*y3*y4 * getNumberInRange(3, 5);
		
		int total = leftover * x1 * x2 * x3 * x4 /y1 /y2/y3/y4;

		String questionTemplate = "Horace mann bought a lot of candies from bjs for halloween. " + lineBreak() +
								"On Monday the kids ate %s of the candies. On Tuesday kids ate %s of the left over. " + lineBreak() +
								"On Wednesday kides ate %s of the leftover. On Thursday kids ate %s of the left over." + lineBreak() +
								"Now there are only %s pieces of candy left. Please find out how many pieces of candies " + lineBreak() +
								"did Horace Mann buy at the very beginning.";
		

		Question q = new Question();
		q.setAnswer("ANSWER: total - " + total );
		q.setQuestion(String.format(questionTemplate, f1, f2, f3, f4, leftover));
		return q;
	}
	
	
	public Question BrickGen () {
		
		int x1 = getNumberInRange(3, 10);
		int z1 = getNumberInRange(1, 2);
		int y1 = x1 - z1;
		
		int x2 = getNumberInRange(3, 10);
		int z2 = getNumberInRange(1, 2);
		int y2 = x2 - z2;

		int x3 = getNumberInRange(3, 10);
		int z3 = getNumberInRange(1, 2);
		int y3 = x3 - z3;

		
		Fraction f1 = new Fraction (0, z1, x1);
		Fraction f2 = new Fraction (0, z2, x2);
		Fraction f3 = new Fraction (0, z3, x3);
		
		f1.transform();
		f2.transform();
		f3.transform();
		
		int leftover = y1*y2*y3* getNumberInRange(3, 5);
		
		int total = leftover * x1 * x2 * x3 /y1 /y2 /y3;

		String questionTemplate = "Home depot bought a lot of bricks from American Construction Inc. The first day they arranged " + lineBreak() +
									"truck picking up %s of the bricks. The 2nd day they picked up %s of the left over bricks. The 3rd day they" + lineBreak() +
									"picked up %s of the leftover bricks. Now they still have %s pieces of bricks to be picked up. " + lineBreak() +
									"Please find out how many bricks home depot has bought.";
		

		Question q = new Question();
		q.setAnswer("ANSWER: total - " + total );
		q.setQuestion(String.format(questionTemplate, f1, f2, f3, leftover));
		return q;
	}
	

	public Question HarryPotterGen () {
		
		int x1 = getNumberInRange(3, 8);
		int z1 = getNumberInRange(1, 2);
		int y1 = x1 - z1;
		
		int x2 = getNumberInRange(3, 8);
		int z2 = getNumberInRange(1, 2);
		int y2 = x2 - z2;

		int x3 = getNumberInRange(3, 8);
		int z3 = getNumberInRange(1, 2);
		int y3 = x3 - z3;

		int x4 = getNumberInRange(3, 8);
		int z4 = getNumberInRange(1, 2);
		int y4 = x4 - z4;

		int x5 = getNumberInRange(3, 8);
		int z5 = getNumberInRange(1, 2);
		int y5 = x5 - z5;
		
		Fraction f1 = new Fraction (0, z1, x1);
		Fraction f2 = new Fraction (0, z2, x2);
		Fraction f3 = new Fraction (0, z3, x3);
		Fraction f4 = new Fraction (0, z4, x4);
		Fraction f5 = new Fraction (0, z5, x5);
		
		f1.transform();
		f2.transform();
		f3.transform();
		f4.transform();
		f5.transform();
		
		int leftover = y1*y2*y3*y4*y5 * getNumberInRange(3, 5);
		
		int total = leftover * x1 * x2 * x3 * x4 * x5 /y1 /y2 /y3 /y4 /y5 ;

		String questionTemplate = "Katherine is reading Harry potter. The first day she read %s of the book. " + lineBreak() +
								"The 2nd day she read %s of the rest of the book. The third day she read %s of " + lineBreak() +
								"the rest of the book. The 4th day she read %s of the rest of the book. The fifth "+ lineBreak() + 
								"day she read %s of the rest of the book.  We know she still have %s pages need to " + lineBreak() + 
								"be read, please find out how many pages this harry potter book has.";
		

		Question q = new Question();
		q.setAnswer("ANSWER: total - " + total );
		q.setQuestion(String.format(questionTemplate, f1, f2, f3, f4, f5, leftover));
		return q;
	}
	

	public Question ChickenGen () {
		int x1 = getNumberInRange(3, 8);
		
		int x2 = getNumberInRange(3, 8);
		if (x2==x1) {
			x2 = getNumberInRange(3, 8);
		}
		
		int x3 = getNumberInRange(3, 8);
		if (x3==x2 || x3==x1) {
			x3 = getNumberInRange(3, 8);
		}
		
		int x4 = getNumberInRange(3, 8);
		
		int bottom =  x1 * x2 * x3 * x4; 
		
		int multiple1 =  x2 * x3 * x4;
		int multiple2 =  x1 * x3 * x4;
		int multiple3 =  x1 * x2 * x4;
		int multiple4 =  x1 * x2 * x3 ;
		
		int top = multiple1 + multiple2 + multiple3 + multiple4; 
		
		ArrayList alPossiblePeopleCount = new ArrayList(); 
		
		int people = 0;
		for (int i = 3; i < 10000; i++ ){
			if ((i * top) % bottom == 0 ) {
//				System.out.println(i);
				alPossiblePeopleCount.add(new Integer(i));
			}
		}
		
		int index = 1; 
		if (alPossiblePeopleCount.size()>3) {
			index = 3; 
		} else {
			index = getNumberInRange (1, alPossiblePeopleCount.size());
		}
		
		int peopleCount = ((Integer)alPossiblePeopleCount.get(index-1)).intValue();
		
		int chickenCount = peopleCount * top / bottom;

		String questionTemplate = "On Independence day 4 groups of people were doing BBQ and watching the fireworks. " + lineBreak() +
			"People from Group A, every %s persons ate a chicken. " + lineBreak() +
			"People from Group B, every %s persons ate a chicken. " + lineBreak() +
			"People from Group C, every %s persons ate a chicken. " + lineBreak() +
			"People from Group D, every %s persons ate a chicken. " + lineBreak() +
			"In total all 4 groups together they ate %s chickens. Also we know all groups had the " + lineBreak() +
			"same amount of people. Please find out how many people were there for each every group. ";

		Question q = new Question();
		q.setAnswer("ANSWER: Every group has  " + peopleCount  + " people");
		q.setQuestion(String.format(questionTemplate, x1, x2, x3, x4, chickenCount));
		return q;
	}
}
