package com.aaamidatlantic.scea.patterns.strategy;

import java.io.*;
public class DataRetriever {
	public static Object deserializeData(String fileName) {
	    Object returnValue = null;
	    try {
	      File inputFile = new File(fileName);
	      if (inputFile.exists() && inputFile.isFile()) {
	        ObjectInputStream readIn = new ObjectInputStream(
	            new FileInputStream(fileName));
	        returnValue = readIn.readObject();
	        readIn.close();
	      } else {
	        System.err.println("Unable to locate the file " + fileName);
	      }
	    } catch (ClassNotFoundException exc) {
	      exc.printStackTrace();

	    } catch (IOException exc) {
	      exc.printStackTrace();

	    }
	    return returnValue;
	  }
}
