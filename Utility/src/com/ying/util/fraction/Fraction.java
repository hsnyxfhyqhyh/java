package com.ying.util.fraction;

public class Fraction {
	private int whole = 0;
	public int getWhole() {
		return whole;
	}

	public int getTop() {
		return top;
	}

	public int getBottom() {
		return bottom;
	}

	private int top;
	private int bottom;
	
	public Fraction (int whole, int top, int bottom){
		if (bottom == 0){
			System.out.println("Invalid fraction because bottom is 0");
			System.exit(-1);
		}
		
		this.whole = whole;
		this.top = top;
		this.bottom = bottom;
		
		transform();
		
	}
	
	public void transform() {
		if (top > bottom) {
			int m = top / bottom;
			whole = m + whole;
			top = top % bottom;
		} else if (top==bottom){
			whole = whole +1;
		} 

		if (top >1){
			simplify();
		}
		
		
	}
	
	private void simplify(){
		//after transformation, top is smaller than bottom
		
		for (int i=2; i<=top; i++ ){
			if ((top % i ==0) && (bottom %i==0)){
				top = top  / i;
				bottom = bottom /i;
			}
					
		}
	}
	
	public static void main(String args[]){
		Fraction f = new Fraction (2,30,15);
	
		System.out.println(f.toString());
	}
	
	public String toString(){
		String w = "" + whole;
		if (w.equals("0")) {
			w = "";
		}
		
		if (top==0) {
			return w;
		} else {
			return w +" " + top + "/" + bottom;
		}
	}
	
	/*
	 * if f1 < f2, return -1
	 */
	public int compare(Fraction f2){
		if (this.whole > f2.whole) {
			return 1;
		} else if (this.whole < f2.whole) {
			return -1;
		} else {
			
			//f1.whole = f2.whole for all the code under this line.
			if (this.top ==0 && f2.top !=0) {
				return -1;
			} else if (this.top!=0 && f2.top==0) {
				return 1;
			} else if (this.top==0 && f2.top==0){
				return 1;
			} else {
				if ((this.top * f2.bottom) >= (this.bottom * f2.top)) {
					return 1; 
				} else {
					return -1;
				}
			}
		}
	}
}
