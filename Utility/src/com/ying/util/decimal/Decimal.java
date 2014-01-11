package com.ying.util.decimal;

public class Decimal {
	private String decimalString;
	public String getDecimalString() {
		return decimalString;
	}

	public void setDecimalString(String decimalString) {
		this.decimalString = decimalString;
	}

	public int getOriginalNumber() {
		return originalNumber;
	}

	public void setOriginalNumber(int originalNumber) {
		this.originalNumber = originalNumber;
	}

	private int originalNumber; 
	
	
	
	public Decimal (String decimalString, int originalNumber){
		this.decimalString = decimalString;
		this.originalNumber = originalNumber;
	}
	
	
	public boolean canPlus(com.ying.util.decimal.Decimal d)
	{
		//doesn't matter
		return true;
	}

	public boolean canMultiply(com.ying.util.decimal.Decimal d)
	{
		//doesn't matter
		return true;
	}
	
	public boolean canDivide(com.ying.util.decimal.Decimal d)
	{
		//already played with the data, no need to check any more
		return true;
	}
	
	public boolean canMinus(com.ying.util.decimal.Decimal second)
	{
		String ds1 = this.getDecimalString();
		String ds2 = second.getDecimalString();
		
		int firstWhole = 0;
		String firstDecimalParts = "";
		
		int secondWhole = 0;
		String secondDecimalParts = "";
		
		if (ds1.indexOf(".")<0) {
			firstWhole = this.getOriginalNumber();
			firstDecimalParts="00000000";
		} else {
			firstWhole = Integer.parseInt(ds1.substring(0, ds1.indexOf(".")));
			firstDecimalParts = ds1.substring(ds1.indexOf(".") + 1);
			
			firstDecimalParts = padStringAtEnd(firstDecimalParts, 8, "0");
		}
		
		
		if (ds2.indexOf(".")<0) {
			secondWhole = second.getOriginalNumber();
			secondDecimalParts="00000000";
		} else {
			secondWhole = Integer.parseInt(ds2.substring(0, ds2.indexOf(".")));
			secondDecimalParts = ds2.substring(ds2.indexOf(".") + 1);
			secondDecimalParts = padStringAtEnd(secondDecimalParts, 8, "0");
		}
		
		if (firstWhole > secondWhole){
			return true;
		} else if (firstWhole < secondWhole){
			return false;
		}
		
		//when whole parts equal, need to check the decimal 
		int fWhole = Integer.parseInt("1" + firstDecimalParts );
		int sWhole = Integer.parseInt("1" + secondDecimalParts );
	
		if (fWhole >= sWhole){
			return true;
		}
		
		return false;
	}
	
	private String padStringAtEnd(String original, int length, String ch){
//		String s = original + " --> ";
		if (original.length()>=length) return original;
		
		while (original.length()< length){
			original = original + ch;
		}
		
//		System.out.println(s + original);
		return original;
	}
}
