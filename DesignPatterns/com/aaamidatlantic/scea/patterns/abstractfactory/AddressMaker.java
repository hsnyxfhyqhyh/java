package com.aaamidatlantic.scea.patterns.abstractfactory;

public class AddressMaker {

	String addressType;
	
	public AddressMaker(String type)
	{
		addressType = type;
	}
	
	public AddressFactory getAddress()
	{
		if (addressType.equals("US"))
			return new USAddressFactory();
		else if( addressType.equals("FRENCH"))
			return new FrenchAddressFactory();
		else return null;
	}
}
