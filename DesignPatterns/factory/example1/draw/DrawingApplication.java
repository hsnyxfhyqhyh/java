package factory.example1.draw;

import factory.example1.framework.*;

public class DrawingApplication extends Application {

	//implement the super class's createDocument method
	public Document createDocument () {
		System.out.println("DrawingApplication -- > createDocument()");
		return new DrawingDocument();
	}

	public void drawApplicationRelatedMethods() {
		//.....
	}
}

