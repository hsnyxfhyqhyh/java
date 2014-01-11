package com.aaamidatlantic.scea.patterns.builder;

public class LocationImpl implements Location {
	public static final long serialVersionUID = 1;
	 private String location;
	    
	    public LocationImpl(){ }
	    public LocationImpl(String newLocation){
	        location = newLocation;
	    }
	    
	    public String getLocation(){ return location; }
	    
	    public void setLocation(String newLocation){ location = newLocation; }
	    
	    public String toString(){ return location; }

}
