package com.aaamidatlantic.scea.patterns.factory;

public class FactoryClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			FactoryBase client = new FactoryBase(1);
			Product prod = client.createProduct();
			prod.getProduct();
			
	}

}
