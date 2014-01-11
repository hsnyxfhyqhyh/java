package com.aaamidatlantic.scea.patterns.iterator;

import java.io.Serializable;

import java.util.Iterator;

interface Iterating extends Serializable {
	 public Iterator getIterator();
}
