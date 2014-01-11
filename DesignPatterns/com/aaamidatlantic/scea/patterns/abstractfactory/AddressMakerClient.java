package com.aaamidatlantic.scea.patterns.abstractfactory;

public class AddressMakerClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddressMaker addrMaker = new AddressMaker("US");
		Address addr = addrMaker.getAddress().createAddress();
		
		addr.setCity("Paris");
		addr.setPostalCode("LK531");
		addr.setRegion("South");
		addr.setStreet("Rue Trois");
		
		System.out.println(addr.getFullAddress());
		
		
	}

}
