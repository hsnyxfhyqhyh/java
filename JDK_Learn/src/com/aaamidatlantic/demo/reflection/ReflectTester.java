package com.aaamidatlantic.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTester {
	public Object copy(Object object) throws Exception {
		// get the object's class type
		Class<?> classType = object.getClass();
		System.out.println("Class:" + classType.getName());

		// by the default constructor to create another object of the same class
		Object objectCopy = classType.getConstructor(new Class[]{})
									.newInstance(new Object[]{});

		//get all properties defined in the class
		Field fields[] = classType.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			
			//assume every property in the java bean class will have a getter method and a setter method
			
			//get the getter method's name 
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			
			//get the setter method's name
			String setMethodName = "set" + firstLetter + fieldName.substring(1);

			//create a Method object for the getter method
			Method getMethod = classType.getMethod(getMethodName, new Class[] {});
			
			//create a Method object for the setter method
			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });

			//call the get method
			Object value = getMethod.invoke(object, new Object[] {});
			System.out.println(fieldName + ":" + value);
			
			//call the set method
			setMethod.invoke(objectCopy, new Object[] { value });
		}
		return objectCopy;
	}

	public static void main(String[] args) throws Exception {
		Customer customer = new Customer("Tom", 21);
		customer.setId(new Long(1));

		Customer customerCopy = (Customer) new ReflectTester().copy(customer);
		System.out.println("Copy information:" + customerCopy.getId() + " "
				+ customerCopy.getName() + " " + customerCopy.getAge());
	}
}

/*******************************************
 * 
 * This is a very simple java bean class
 * 
 *******************************************/
class Customer {
	private Long id;

	private String name;

	private int age;

	public Customer() {
	}

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
