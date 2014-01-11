package com.ying.util.chickenRabbit;

public class QuestionTemplate {
	public int getNumberInRange(int min, int max) {
		int number = min + (int)(Math.random() * ((max - min) + 1));
		return number;
	}
	
	public String centsToDollar(int totalPrice){
		String totalPriceS = Integer.toString(totalPrice);
		if (totalPriceS.length()>=3){
			totalPriceS = totalPriceS.substring(0, totalPriceS.length()-2) + "." + totalPriceS.substring(totalPriceS.length()-2);
		} else {
			totalPriceS = "0." + totalPriceS;
		}
		
		return totalPriceS;
	}
	
	public String lineBreak(){
		return "\n";
	}
}
