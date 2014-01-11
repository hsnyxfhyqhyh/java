package com.aaamidatlantic.scea.patterns.adapter;

public interface Contact 
{	
	    public String getFirstName();
	    public String getLastName();
	    public String getTitle();
	    public String getOrganization();
	    
	    public void setContact(KonkaniContact newContact);
	    public void setFirstName(String newFirstName);
	    public void setLastName(String newLastName);
	    public void setTitle(String newTitle);
	    public void setOrganization(String newOrganization);	
}
