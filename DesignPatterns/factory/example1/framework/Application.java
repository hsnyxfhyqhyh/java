package factory.example1.framework;

import java.util.ArrayList;

public abstract class Application {
	ArrayList aDocs = new ArrayList();

	public void newDocument() {
		System.out.println("Application -- > newDocument ()");
		Document aDoc = createDocument();
		aDocs.add(aDoc);
	}
	
	public abstract Document createDocument();
	
	public void close() {

	}
}






