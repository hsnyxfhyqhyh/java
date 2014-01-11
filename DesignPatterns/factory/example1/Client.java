package factory.example1; 

import factory.example1.draw.*; 
import factory.example1.framework.*;

public class Client {
	public static void main (String args[]) {
		System.out.println("Factory Pattern Tracker:");
		
		Application app = new DrawingApplication();
		// or
		//DrawingApplication app = new DrawingApplication();

		//click event of a button ...
		app.newDocument();

		//close the application
		app.close();

	}
}