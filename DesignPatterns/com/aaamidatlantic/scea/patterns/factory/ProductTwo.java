package com.aaamidatlantic.scea.patterns.factory;

public class ProductTwo implements Product {

	public Product getProduct() {
		// TODO Auto-generated method stub
		System.out.println("Product Two");
		return new ProductTwo();
	}

}
