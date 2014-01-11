package com.aaamidatlantic.scea.patterns.iterator;


import java.util.HashMap;
import java.util.Iterator;


public class ToDoListCollectionImpl implements ToDoListCollection {

	public static final long serialVersionUID= 1;
	 private HashMap lists = new HashMap();

	  public void add(ToDoList list) {
	    if (!lists.containsKey(list.getListName())) {
	      lists.put(list.getListName(), list);
	    }
	  }

	  public void remove(ToDoList list) {
	    if (lists.containsKey(list.getListName())) {
	      lists.remove(list.getListName());
	    }
	  }

	  public int getNumberOfItems() {
	    return lists.size();
	  }

	  public Iterator getIterator() {
	    return lists.values().iterator();
	  }

	  public String toString() {
	    return getClass().toString();
	  }
}
