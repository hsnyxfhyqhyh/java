package com.aaamidatlantic.scea.patterns.factory;

public class ProductOne implements Product {

	public Product getProduct() {
		// TODO Auto-generated method stub
		System.out.println("Product One");
		return new ProductOne();
	}

}
