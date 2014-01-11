package com.aaamidatlantic.scea.patterns.prototype;

public class PrototypeClient {

	
		  
		public static void main(String[] arguments) {
		    
		    Address address1 = new Address("8445 Silverado Trail", "Rutherford",
		        "CA", "91734");
		    System.out.println("First address created.");
		    System.out.println("    Hash code = " + address1.hashCode());
		    System.out.println(address1);
		    System.out.println();

		    System.out.println("Creating second address using the clone() method.");
		    Address address2 = (Address) address1.copy();
		    System.out.println("Second address created.");
		    System.out.println("    Hash code = " + address2.hashCode());
		    System.out.println(address2);
		    System.out.println();

		 
		}

}
