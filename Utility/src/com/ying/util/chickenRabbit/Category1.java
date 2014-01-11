package com.ying.util.chickenRabbit;

public class Category1 extends QuestionTemplate{
	public Question FruitGen(){
		int pineappleCount = getNumberInRange(10, 15);
		int watermelonCount = getNumberInRange(12, 30);
		
		int pineapplePrice = getNumberInRange(250, 300);
		int priceDifference = getNumberInRange(20, 50);
		
		int watermelonPrice = pineapplePrice + priceDifference; 
		
		int totalPrice = pineappleCount * pineapplePrice + watermelonCount * watermelonPrice;
		
		String totalPriceS = centsToDollar(totalPrice);
		String priceDifferenceS = centsToDollar(priceDifference);
		
		String pineapplePriceS = centsToDollar(pineapplePrice);
		String watermelonPriceS = centsToDollar(watermelonPrice);
		
		String questionTemplate = "Katherine spent $%s to buy %s watermelons and %s pineapples for the homecoming party. " + lineBreak() + 
								"We know every watermelon costs $%s more than the pineapple. Please find out how much for a watermelon " + lineBreak() + 
								"and how much for a pineapple.";

		Question q = new Question();
		q.setAnswer("ANSWER: Watermelon - " + watermelonPriceS + " ; Pineapple - " + pineapplePriceS);
		q.setQuestion(String.format(questionTemplate, totalPriceS, watermelonCount, pineappleCount, priceDifferenceS));
		return q;
	}
	
	public Question TeaGen(){
		int ChineseCount = getNumberInRange(8, 20);
		int EnglishCount = getNumberInRange(5, 10);
		
		int ChinesePrice = getNumberInRange(250, 300);
		int EnglishPrice = getNumberInRange(100, 150);
		
		int totalPrice = ChineseCount * ChinesePrice + EnglishCount * EnglishPrice;
		int totalPound = ChineseCount + EnglishCount; 
		int priceDifference = ChineseCount * ChinesePrice - EnglishCount * EnglishPrice;;
		
		
		String questionTemplate = "Katherine went to China and decided to buy some tea for the school teachers. She bought %s pound of tea of " + lineBreak() +  
								"2 different kinds. The Chinese tea costs $%s per pound; the english breakfast tea costs $%s per pound. We know " + lineBreak() +
								"Katherine spent $%s dollars less on English tea than on Chinese tea. Can you find out how many pounds of chinese " + lineBreak() + 
								"tea Katherine bought? ";

		Question q = new Question();
		q.setAnswer("ANSWER: Chinese tea - " + ChineseCount + " ; English Tea - " + EnglishCount);
		q.setQuestion(String.format(questionTemplate, totalPound, ChinesePrice, EnglishPrice, priceDifference));
		return q;
	}
	
	public Question SquirrelGen(){
		int RainnyCount = getNumberInRange(5, 10);
		int SunnyCount = getNumberInRange(4, 8);
		
		int unit = getNumberInRange(3,5);
		int RainnyWork = unit * getNumberInRange(3,5);
		int SunnyWork = unit * getNumberInRange(6,8);
		
		int totalWork = SunnyCount * SunnyWork + RainnyCount * RainnyWork;
		int average = 0;
		
		
		while (totalWork % (SunnyCount + RainnyCount) !=0) {
			RainnyCount = getNumberInRange(5, 10);
			SunnyCount = getNumberInRange(4, 8);
			
			unit = getNumberInRange(3,5);
			RainnyWork = unit * getNumberInRange(3,5);
			SunnyWork = unit * getNumberInRange(6,8);
			
			totalWork = SunnyCount * SunnyWork + RainnyCount * RainnyWork;
			average = totalWork / (SunnyCount + RainnyCount); 
		}
		
		String questionTemplate = "Squirrel collected nuts for preparation for the winter. On a rainny day " +  lineBreak() +
							"it collected %s nuts per day; on a sunny day it collected %s per day. We know this squirrel " + lineBreak() +
							"collected %s nuts on a couple of days, also we know this squirrel collected %s nuts per day on average." + lineBreak() +
							"Please find out how many sunny days and how many rainny days the squirrel had during this period of time. ";

		Question q = new Question();
		q.setAnswer("ANSWER: Rainny - " + RainnyCount + " ; Sunny - " + SunnyCount);
		q.setQuestion(String.format(questionTemplate, RainnyWork, SunnyWork, totalWork, average));
		return q;
	}
	
	public Question chickenRabbitGen() { 
		int chickenCount = getNumberInRange(20, 50);
		int rabbitCount = getNumberInRange (30, 70);
		
		int headCount = chickenCount + rabbitCount; 
		int legCount = chickenCount * 2 + rabbitCount * 4;
		
		String questionTemplate = "In a farm there are Chickens and rabbits in the same cage. Every chicken has one head " + lineBreak() +
							"and 2 legs; every rabbit has one head and 4 legs. Now we know the head count is %s and " + lineBreak() +
							"leg count is %s, please find out how many chickens and how many rabbits are in the cage. ";
		
		Question q = new Question();
		q.setAnswer("ANSWER: Chicken - " + chickenCount + " ; Rabbit - " + rabbitCount);
		q.setQuestion(String.format(questionTemplate, headCount, legCount));
		return q;
	}
	
	public Question chickenRabbitGen2() { 
		int chickenCount = getNumberInRange(20, 50);
		int rabbitCount = getNumberInRange (30, 70);
		
		int headCount = chickenCount + rabbitCount; 
		int legDiffCount = rabbitCount * 4 - chickenCount * 2;
		
		String questionTemplate = "In a farm there are Chickens and rabbits in the same cage. Every chicken has one head " + lineBreak() +
							"and 2 legs; every rabbit has one head and 4 legs. Now we know the head count is %s and " + lineBreak() +
							"there are %s more rabbit legs than chicken legs. Please find out how many chickens and " + lineBreak() +
							"how many rabbits are in the cage. ";
		
		String ques = String.format(questionTemplate, headCount, legDiffCount);
		
		Question q = new Question();
		q.setAnswer("ANSWER: Chicken - " + chickenCount + " ; Rabbit - " + rabbitCount);
		q.setQuestion(ques);
		return q;
	}
	
	public Question redBluePencilGen() { 
		int redPencilCount = getNumberInRange (6, 15);
		int bluePencilCount = getNumberInRange(5, 10);
		
		int redPencilPrice = getNumberInRange(15, 22);
		int bluePencilPrice = getNumberInRange(11, 20);
		
		while (redPencilPrice==bluePencilPrice){
			redPencilPrice = getNumberInRange(15, 22);
			bluePencilPrice = getNumberInRange(11, 20);
		}
		
		int totalCount = bluePencilCount + redPencilCount; 
		int totalPrice = bluePencilCount * bluePencilPrice + redPencilCount * redPencilPrice;
		
		String totalPriceS = centsToDollar(totalPrice);
		
		
		String questionTemplate = "Red pencil is worth %s cents. Blue pencil is worth %s cents " + lineBreak() +
							"Now Katherine bought %s pencils and the total price is $%s, please " + lineBreak() + 
							"find out how many blue and red pencils Katherine bought separately";
		
		String ques = String.format(questionTemplate, redPencilPrice, bluePencilPrice, totalCount, totalPriceS);
		
		Question q = new Question();
		q.setAnswer("ANSWER: blue - " + bluePencilCount + " ; Red - " + redPencilCount);
		q.setQuestion(ques);
		return q;
	}
	
	public Question coinGen() {
		int _5Count = getNumberInRange(10, 30);
		int _10Count = _5Count * 4;
		
		int _10Price = _10Count * 10;
		int _5Price = _5Count * 5;
		
		int totalPrice = _10Price + _5Price;
		
		String totalPriceS = centsToDollar(totalPrice);
		
		String questionTemplate = "Katherine saved a lot of coins. All the coins are either nickel or dime. " + lineBreak() +
									"The total value of the coins is worth $%s. We also know the number of dime is " + lineBreak() +
									"4 times of nickel. Please tell how many nickels Katherine has.";

		String ques = String.format(questionTemplate, totalPriceS);
		
		Question q = new Question();
		q.setAnswer("ANSWER: Nickel - " + _5Count);
		q.setQuestion(ques);
		return q;
		
	}
	
	public Question bottleGen() {
		int bottleCount = getNumberInRange(25, 30) * 100;
		int damageBottleCount = getNumberInRange(1, 3) * 100 + getNumberInRange(1, 100);
		int fine = 100 * damageBottleCount;
		int shippingFee = 20 * (bottleCount - damageBottleCount);
		int driverWage = shippingFee - fine;
		
		String driverWageDollar = centsToDollar(driverWage);
		
		String questionTemplate = "Katherine is doing the delivery for snaples as a summer job. One day " + lineBreak() +
									"she was trying to deliver %s bottles of snapples. The contract said, for every " + lineBreak() +
									"bottle of snaple she delivers in perfect condition she got 20 cents of shipping fee, " + lineBreak() +
									"if she damages a bottle of snapple the fine will be a dollar. We know Katherine " + lineBreak() +
									"got $%s that day. Could you tell how many bottles Katherine has damaged that day?";
		
		String ques = String.format(questionTemplate, bottleCount, driverWageDollar);
		
		Question q = new Question();
		q.setAnswer("ANSWER: Damaged - " + damageBottleCount);
		q.setQuestion(ques);
		return q;
	}

	
	public Question chessGen(){
		int chineseCheckerCount = getNumberInRange(8, 15);
		int chessCount = getNumberInRange (15, 25);
		
		int gameCount = chineseCheckerCount + chessCount; 
		int peopleCount = chineseCheckerCount * 6 + chessCount * 2;

		String questionTemplate = "In the SACC there is a chess club. There are %s people in the club playing either chess " + lineBreak() +
									"or Chinese Checker. Every chess game table has 2 people playing. And every Chinese checker table " + lineBreak() +
									"has 6 people playing. Total there are %s tables. Please tell how many tables are " + lineBreak() +
									"for chess, and how many tables are for Chinese checker. ";
		
		String ques = String.format(questionTemplate, peopleCount, gameCount);
		
		Question q = new Question();
		q.setAnswer("ANSWER: chess - " + chessCount + " ; chinese checker - " + chineseCheckerCount);
		q.setQuestion(ques);
		return q;
	}
	
	public Question balloonGen() {
		int BNumber = getNumberInRange(10, 15);
		
		int msNumber = getNumberInRange(10, 15);
		int MNumber = msNumber * 2; 
		int SNumber = msNumber * 3;
		
		int msPrice = getNumberInRange(1, 5)* 10;
		int BPrice = msPrice * 6;
		int MPrice = msPrice * 3; 
		int SPrice = msPrice * 2;
		
		int totalPrice = BPrice*BNumber + MPrice*MNumber + SPrice*SNumber; 
		
		int balloonCount = BNumber + MNumber + SNumber; 
		
		String BPriceDollar = centsToDollar(BPrice);
		String MPriceDollar = centsToDollar(MPrice); 
		String SPriceDollar = centsToDollar(SPrice);
		
		String totalPriceDollar = centsToDollar(totalPrice);
		
		String questionTemplate = "Shoprite sells balloons for birthday party. The biggest size costs $%s each; " + lineBreak() +
									"middle size costs $%s each, and small size costs $%s each. Mrs. Kirk bought %s balloons with $%s. " + lineBreak() +
									"We also know Mrs. Kirk spent equal amount of money on the middle size balloon and small size balloon. " + lineBreak() +
									"Can you find out how many of each size balloons that Mrs. Kirk bought?";

		Question q = new Question();
		q.setAnswer("ANSWER: Big Size - " + BNumber + " ; Middle Size - " + MNumber + " ; Small Size - " + SNumber);
		q.setQuestion(String.format(questionTemplate, BPriceDollar, MPriceDollar, SPriceDollar, balloonCount, totalPriceDollar));
		return q;
	}
	
	public Question stampGen() {
		int _4StampCount = getNumberInRange(50, 80);
		int moreCount = getNumberInRange(20, 40);
		int _8StampCount = _4StampCount - moreCount;
		
		int totalPrice = _4StampCount * 4 + _8StampCount * 8;
		
		String totalPriceS = centsToDollar(totalPrice);
		
		String questionTemplate = "Katherine bought 4-cent stamps and 8-cent stamps. She spent $%s for the " + lineBreak() +
								"stamps all together. And we know she has %s more 4-cent stamps than the 8-cent stamps. " + lineBreak() +
								"Can you tell me how many 4-cent stamp she bought? ";
		
		Question q = new Question();
		q.setAnswer("ANSWER: 4 cent stamps - " + _4StampCount );
		q.setQuestion(String.format(questionTemplate, totalPriceS, moreCount));
		return q;
	}
	
	public Question projectGen(){
		int aSpeed = getNumberInRange (3, 8);
		int bSpeed = getNumberInRange (10, 15);
		int totalDays = getNumberInRange (aSpeed+1, bSpeed-1);

		String questionTemplate = "There was a project. It took Katherine alone %s days to finish. " + lineBreak() +
								"Also we know it took Rebecca alone %s days to finish. Katherine did a couple " + lineBreak() +
								"of days then Rebecca picked it up and finished it. We know together " + lineBreak() +
								"Katherine and Rebecca spent %s days. Please find out how many days " + lineBreak() +
								"Katherine and Rebecca spent separately.";
		
		String ques = String.format(questionTemplate, aSpeed, bSpeed, totalDays);
		
		Question q = new Question();
		q.setAnswer("ANSWER: ** " );
		q.setQuestion(ques);
		return q;
	}
	
	public Question typingGen() {

		int aSpeed = getNumberInRange (3, 8);
		int bSpeed = getNumberInRange (10, 15);
		int totalHour = getNumberInRange (aSpeed+1, bSpeed-1);

		String questionTemplate = "There was a document. It took Katherine alone %s hours to type. " + lineBreak() +
								"Also we know it took Rebecca alone %s hours to type, Katherine typed a couple of " + lineBreak() +
								"hours then Rebecca picked it up and finished it. We know together Katherine " + lineBreak() +
								"and Rebecca spent %s hours. Please find out how many hours Katherine and " + lineBreak() +
								"Rebecca spent separately.";
		
		String ques = String.format(questionTemplate, aSpeed, bSpeed, totalHour);
		
		Question q = new Question();
		q.setAnswer("ANSWER: ** " );
		q.setQuestion(ques);
		return q;
	}
	
	public Question insectGen(){
		int sCount = getNumberInRange(10, 20);
		int dCount = getNumberInRange(5, 15);
		int cCount =  getNumberInRange(7, 14);
		int headCount = sCount+dCount+cCount;
		int legsCount = sCount*8+ dCount*6+cCount*6;
		int wingsCount = dCount*2+ cCount;
		
		String questionTemplate = "**A LITTLE MORE DIFFICULT: \n" +
									"Spider has 8 legs, dragonfly has 6 legs and 2 pair of wings, Cicadidae has 6 legs and 1 pair of wings. " + lineBreak() +
							"Now we know there are %s insects of these different insects. They have total %s legs and %s pair of wings " + lineBreak() +
							"Can you tell how many for each every type of insects?";
		
		String ques = String.format(questionTemplate, headCount, legsCount, wingsCount);
		
		Question q = new Question();
		q.setAnswer("ANSWER: Spider - " + sCount + " ; dragonfly - " + dCount + " ; Cicadidae - " + cCount);
		q.setQuestion(ques);
		return q;
	}
	
	public Question snackGen() {
		int time = getNumberInRange(3, 5);
		
		int BNumber= getNumberInRange (15, 25);
		int ANumber= BNumber * time; 
		int CNumber= getNumberInRange (15, 25);
		
		int totalSnack = ANumber + BNumber + CNumber;
		
		int APrice = getNumberInRange (5, 7) * 10;
		int BPrice = getNumberInRange (21, 30) * 10;
		int CPrice = getNumberInRange (50, 70) * 10;
		
		String APriceS = centsToDollar(APrice);
		String BPriceS = centsToDollar(BPrice);
		String CPriceS = centsToDollar(CPrice);
		
		int totalPrice =ANumber * APrice + BNumber * BPrice + CNumber * CPrice;
		
		String totalPriceS = centsToDollar(totalPrice);
		
		String questionTemplate = "**A LITTLE MORE DIFFICULT: \n" +
									"Horace Mann is having a science fair and hands out snacks as prizes. The total snack " + lineBreak() +
									"count is %s. To buy these snacks school spent $%s. There are 3 different snacks Horace " + lineBreak() +
									"Mann bought, A,B and C. The amount of A is %s times of B. A costs $%s each, B costs $%s each " + lineBreak() +
									"and C costs $%s each. Please find out how many of each snacks Horace Mann has bought. ";
		
		String ques = String.format(questionTemplate, totalSnack, totalPriceS, time, APriceS, BPriceS, CPriceS);
		
		Question q = new Question();
		q.setAnswer("ANSWER: A - " + ANumber + " ; B - " + BNumber + " ; C - " + CNumber);
		q.setQuestion(ques);
		return q;
	}
	
	public Question mathGen2 () {
		int _1TotalQuestion = getNumberInRange(20, 30);
		int _2TotalQuestion =  getNumberInRange(15, 20);
		int _1CorrectQuestion = getNumberInRange(18, _1TotalQuestion-1);
		int _2CorrectQuestion = getNumberInRange(10, _2TotalQuestion-1);
		int totalCorrectCount = _1CorrectQuestion + _2CorrectQuestion;
		
		int _1Score = _1CorrectQuestion * 5 - (_1TotalQuestion - _1CorrectQuestion);
		int _2Score = _2CorrectQuestion * 8 - (_2TotalQuestion - _2CorrectQuestion) * 2;
		
		String questionTemplate = "Katherine took 2 exams recently on topic world history. The first exam " + lineBreak() +
									"has %s questions. For each question she answered correctly she got 5 points; " + lineBreak() +
									"otherwise she lost 1 point. As for the 2nd exam there were %s questions. " + lineBreak() +
									"For each question she answered correctly she got 8 points otherwise lost 2 points. " + lineBreak() + 
									"Katherine answered total %s questions correctly. And we know she got " + lineBreak() ;
		
		String suffix ="";
		if (_1Score == _2Score) {
			suffix = "equal scores in the 2 exams";
		} else if (_1Score > _2Score){
			suffix = (_1Score - _2Score) + " more points in the 1st than in the 2nd exam";
		} else if (_1Score < _2Score){
			suffix = (_2Score - _1Score) + " less points in the 1st than in the 2nd exam";
		}
		
		String suffix2 = ". Please find how many questions Katherine got in the 1st exam";
		String ques = String.format(questionTemplate + suffix + suffix2, _1TotalQuestion, _2TotalQuestion, totalCorrectCount);
		
		Question q = new Question();
		q.setAnswer("ANSWER: " + _2CorrectQuestion);
		q.setQuestion(ques);
		return q;
		
	}
	
	public Question mathExamGen() {
		int _1Count = getNumberInRange(5, 10);
		int _2Count = getNumberInRange(5, 10);
		int _3Count = _2Count;
		int _4Count = getNumberInRange(5, 10);
		int _5Count = getNumberInRange(5, 10);
		int headCount = _1Count + _2Count + _3Count+ _4Count + _5Count;
		int totalQuestionCorrectCount = _1Count + _2Count*2 + _3Count*3 + _4Count*4 + _5Count*5; 
	
		String questionTemplate = "One class took a 5-question exam. The class has %s people. The number of " + lineBreak() +
									"total questions answered correctly by the whole class was %s. And everybody in " + lineBreak() +
									"the class had answered at least one question correctly. We know %s people " + lineBreak() +
									"answered only 1 question correctly. %s people got all 5 questions correctly. " + lineBreak() +
									"The number of people who answered 2 questions correctly was the same as the " + lineBreak() +
									"number of people who answered 3 questions correctly. How many people answered " + lineBreak() +
									"4 questions correctly?" ;  
		
		String ques = String.format(questionTemplate, headCount, totalQuestionCorrectCount, _1Count, _5Count);
		
		Question q = new Question();
		q.setAnswer("ANSWER: 4 questions correct - " + _4Count);
		q.setQuestion(ques);
		return q;
	}

}
