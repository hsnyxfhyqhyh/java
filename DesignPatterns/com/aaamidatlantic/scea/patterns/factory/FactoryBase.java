package com.aaamidatlantic.scea.patterns.factory;

public class FactoryBase {
	
	int productType;
	
	public FactoryBase(int type)
	{
		productType = type;
	}
	public Product createProduct()
	{
		switch(productType)
		{
			case 1: return new ProductOne();
			
			case 2: return new ProductTwo();
			
			default: return null;
		}
		
	}
}
