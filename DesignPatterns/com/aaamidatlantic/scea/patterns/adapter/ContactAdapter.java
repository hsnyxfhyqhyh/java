package com.aaamidatlantic.scea.patterns.adapter;

public class ContactAdapter implements Contact{

	private KonkaniContact contact;
    
    public ContactAdapter(){
        contact = new KonkaniContactImpl();
    }
    public ContactAdapter(KonkaniContact newContact){
        contact = newContact;
    }
    
    public String getFirstName(){
        return contact.navHadi();
    }
    public String getLastName(){
        return contact.adNavHadi();
    }
    public String getTitle(){
        return contact.kamHadi();
    }
    public String getOrganization(){
        return contact.kamaNavHadi();
    }
    
    public void setContact(KonkaniContact newContact){
        contact = newContact;
    }
    public void setFirstName(String newFirstName){
        contact.davariNav(newFirstName);
    }
    public void setLastName(String newLastName){
        contact.davariAdNav(newLastName);
    }
    public void setTitle(String newTitle){
        contact.davariKam(newTitle);
    }
    public void setOrganization(String newOrganization){
        contact.davariKamaNav(newOrganization);
    }
    
    public String toString(){
        return contact.toString();
    }
	
}
