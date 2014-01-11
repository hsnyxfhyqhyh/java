package com.aaamidatlantic.demo.reflection;

import java.lang.reflect.Method;

public class DumpMethods
{
    public static void main(String args[]) throws Exception
    {
    	String s = "java.lang.String";
    	
        //load and initialized the class of String
        Class<?> classType = Class.forName(s);

        //get all methods defined in the class String
        Method methods[] = classType.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
        {
            System.out.println(methods[i].toString());
        }
    }
}
