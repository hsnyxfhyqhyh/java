package com.aaamidatlantic.demo.i18n;

import java.util.*;

public class Test {

	public static void main(String[] args) {

		String language;
		String country;

		if (args.length != 2) {
			language = new String("en");
			country = new String("US");
		} else {
			language = new String(args[0]);
			country = new String(args[1]);
		}

		Locale currentLocale;
		ResourceBundle messages;

		currentLocale = new Locale(language, country);
		//currentLocale =Locale.CHINA;

		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
		System.out.println(messages.getString("greetings"));
		System.out.println(messages.getString("farewell"));
		System.out.println(messages.getString("inquiry"));
	}
}
