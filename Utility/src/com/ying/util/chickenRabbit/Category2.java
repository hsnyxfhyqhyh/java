package com.ying.util.chickenRabbit;

public class Category2 extends QuestionTemplate{
	
	
	
	public Question mathGen3() {
		int set1Question = getNumberInRange(5, 6)*5;
		int set2Question = getNumberInRange(20, 23);
		int set3Question = getNumberInRange(15, 16);
		int set1Times = getNumberInRange(3, 5);
		int set2Times = getNumberInRange(5, 7);
		int set3Times = getNumberInRange(12, 15);
		
		int set1Total = set1Question * set1Times;
		int set2Total = set2Question * set2Times;
		int set3Total = set3Question * set3Times;
		
		int totalTimes = set1Times + set2Times + set3Times;
		int totalQuestions = set1Total + set2Total + set3Total;
	
		String questionTemplate = "One exam was taken %s times with a total number of %s questions asked. " + lineBreak() +
								"There were either %s, or %s or %s questions asked each every time. Please find " + lineBreak() +
								"out how many times there were %s questions asked in the exam." ;  

		String ques = String.format(questionTemplate, totalTimes, totalQuestions, set1Question, set2Question, set3Question, set1Question);
		
		Question q = new Question();
		q.setAnswer("ANSWER: " + set1Times);
		q.setQuestion(ques);
		return q;
	}
	
	public Question busTripGen() {
		int subwayPrice = getNumberInRange(6, 7) * 100;
		int shuttlePrice = getNumberInRange(3, 4) * 100;
		int carPrice = getNumberInRange(10, 14) * 10;

		int subwayCount = getNumberInRange(5, 8);
		int shuttleCount = getNumberInRange(6, 10);
		int carCount = getNumberInRange(20, 30);
		
		int subwayTotal = subwayPrice * subwayCount;
		int shuttleTotal = shuttlePrice * shuttleCount;
		int carTotal = carPrice * carCount;
		
		int totalTimes = subwayCount + shuttleCount + carCount;
		int totalPrice = subwayTotal + shuttleTotal + carTotal;
		
//		System.out.println("ANSWER: subway - " + subwayCount + " ; shuttle - " + shuttleCount + " ; car - " + carCount);
		String questionTemplate = "%s kids went for swimming tournament. We know per person the cost for " + lineBreak() +
							"taking subway is $%s, the cost for shuttle is $%s and the cost for driving is $%s. " + lineBreak() +
							"We also know the total cost that the kids spent for the trip is $%s. Please find out " + lineBreak() +
							"how many kids took the shuttle service?" ;  

		String totalPriceS = centsToDollar(totalPrice);
		String subwayPriceS = centsToDollar(subwayPrice);
		String shuttlePriceS = centsToDollar(shuttlePrice);
		String carPriceS = centsToDollar(carPrice);
		
		String ques = String.format(questionTemplate, totalTimes, subwayPriceS, shuttlePriceS, carPriceS, totalPriceS);
		
		Question q = new Question();
		q.setAnswer("ANSWER: " + shuttleCount);
		q.setQuestion(ques);
		return q;
	}
	
	
	
	
}
