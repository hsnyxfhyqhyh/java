package com.aaamidatlantic.demo.jdk5.enumT.advanced;

public enum Coin
{
	penny("hello"), nickel("world"), dime("welcome"), quarter("nihao");

	private String value;

	public String getValue()
	{
		return value;
	}

	Coin(String value)
	{
		this.value = value;
	}

	public static void main(String[] args)
	{
		Coin coin = Coin.quarter;
		System.out.println(coin.getValue());
	}
}
