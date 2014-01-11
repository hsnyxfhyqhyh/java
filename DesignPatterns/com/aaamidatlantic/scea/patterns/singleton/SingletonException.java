package com.aaamidatlantic.scea.patterns.singleton;

public class SingletonException extends RuntimeException {

	public static final long serialVersionUID = 1;
//	new exception type for singleton classes
	public SingletonException()
	{
	super();
	}
//	-----------------------------------------------
	public SingletonException(String s)
	{
	super(s);
	}
	
}
