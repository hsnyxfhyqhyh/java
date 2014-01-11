package com.ying.util.decimal;

public class Test {

	public static void main(String[] args) {
//		int originalNumber1 = 927;
//		int digitsAfterDot1 = 2;
//		String ds1 = "0.0927";
//		
//		int firstWhole = 0;
//		String firstDecimalParts = "";
//		
//		if (ds1.indexOf(".")<0) {
//			firstWhole = originalNumber1;
//			firstDecimalParts="00000000";
//		} else {
//			firstWhole = Integer.parseInt(ds1.substring(0, ds1.indexOf(".")));
//			firstDecimalParts = ds1.substring(ds1.indexOf(".") + 1);
//		}
//		
//		System.out.println(firstWhole);
//		System.out.println(firstDecimalParts);
//		com.ying.util.decimal.Decimal first = new Decimal("0.00925", 92);
		
		
		for (int i = 0 ; i< 200; i++){
			int originalNumber1 = getNumberInRange(1, 1000);
			int digitsAfterDot1 = getNumberInRange(1, 5);
			String ds1 = getDecimal(digitsAfterDot1, originalNumber1);
			
			com.ying.util.decimal.Decimal first = new Decimal(ds1, originalNumber1);
			
			int originalNumber2 = getNumberInRange(1, 1000);
			int digitsAfterDot2 = getNumberInRange(1, 5);
			String ds2 = getDecimal(digitsAfterDot2, originalNumber2);
			
			com.ying.util.decimal.Decimal second = new Decimal(ds2, originalNumber2);

			if (first.canMinus(second)){
				System.out.println(first.getDecimalString() + " - " + second.getDecimalString() + " = \n");
			} else {
				System.out.println(second.getDecimalString() + " - " + first.getDecimalString() + " = \n");
			}
		}
		

	}
	
	//get a random number in range. 
	private static int getNumberInRange(int min, int max) {
		int number = min + (int)(Math.random() * ((max - min) + 1));
		return number;
	}

	private static int getTotalDigitsAfterDecimal() {
		return getNumberInRange(0, 5);
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
	
	public boolean canMinus(com.ying.util.decimal.Decimal d)
	{
		return true;
	}
}
