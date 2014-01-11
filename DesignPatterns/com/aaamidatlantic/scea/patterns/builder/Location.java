package com.aaamidatlantic.scea.patterns.builder;

import java.io.Serializable;

public interface Location extends Serializable {
	public String getLocation();
    public void setLocation(String newLocation);
}
