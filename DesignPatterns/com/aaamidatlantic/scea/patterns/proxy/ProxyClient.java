package com.aaamidatlantic.scea.patterns.proxy;

import java.util.*;
public class ProxyClient {
	public static void main(String[] args) {
	    OrderIF order = new OrderProxy();
	    Vector v = order.getAllOrders();
	    v = order.getAllOrders();
	    v = order.getAllOrders();
	    v = order.getAllOrders();
	    
	    System.out.println(v.size());
	  }
	
}
