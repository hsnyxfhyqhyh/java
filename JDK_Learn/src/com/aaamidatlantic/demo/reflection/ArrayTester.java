package com.aaamidatlantic.demo.reflection;

import java.lang.reflect.Array;

public class ArrayTester
{
    public static void main(String args[]) throws Exception
    {
        Class<?> classType = Class.forName("java.lang.String");
        //create an array for string type, size is 10
        Object array = Array.newInstance(classType, 10);
        
        //set the value of the 6th element of array to "hello" 
        Array.set(array, 5, "hello");
        
        //get the value of the 6th element and cast it to string type
        String s = (String) Array.get(array, 5);
        System.out.println(s);
    }
}
