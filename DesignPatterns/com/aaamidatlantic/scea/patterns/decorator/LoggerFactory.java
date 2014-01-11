package com.aaamidatlantic.scea.patterns.decorator;

import java.util.Properties;
import java.io.IOException;

public class LoggerFactory {

	public boolean isFileLoggingEnabled() {
	    //Properties p = new Properties();
	   // try {
	   //   p.load(ClassLoader.getSystemResourceAsStream("Logger.properties"));
	   //   String fileLoggingValue = p.getProperty("FileLogging");
	    	String fileLoggingValue = "ON";
	      if (fileLoggingValue.equalsIgnoreCase("ON") == true)
	        return true;
	      else
	        return false;
	 //   } catch (IOException e) {
	 //     return false;
	 //   }
	  }

	  public Logger getLogger() {
	    if (isFileLoggingEnabled()) {
	      return FileLogger.getFileLogger();
	    } else {
	      return new ConsoleLogger();
	    }
	  }
}
