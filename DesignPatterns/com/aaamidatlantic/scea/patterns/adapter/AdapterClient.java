package com.aaamidatlantic.scea.patterns.adapter;

public class AdapterClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	        System.out.println("Creating a new ContactAdapter. This will, by extension,");
	        System.out.println(" create an instance of KonkaniContactImpl which will provide");
	        System.out.println(" the underlying Contact implementation.");
	        Contact contact = new ContactAdapter();
	        System.out.println();
	        
	        System.out.println("ContactAdapter created. Setting contact data.");
	        contact.setFirstName("Seema");
	        contact.setLastName("Pai");
	        contact.setTitle("Developer");
	        contact.setOrganization("AAA MidAtlantic");
	        System.out.println();
	        
	        System.out.println("ContactAdapter data has been set. Printing out Contact data.");
	        System.out.println();
	        System.out.println(contact.toString());
	}

}
