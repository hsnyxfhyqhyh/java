package iterator.employee;

import java.util.ArrayList;

public class Client {

	public static void main(String[] args) {
		List l = new EmployeeList();
		Iterator i = l.createIterator(2);
		
		l.append(new Employee("Peter", "Smith1", 21));
		l.append(new Employee("Peter", "Smith2", 22));
		l.append(new Employee("Peter", "Smith3", 23));
		l.append(new Employee("Peter", "Smith4", 24));
		
		for (i.first(); !i.isDone(); i.next()){
			ListItem li = i.currentItem();
			
			li.print();
		}

	}

}

//#########################
abstract class List{
	
	public abstract int count();
	public abstract Iterator createIterator(int type);
	public abstract void append(ListItem li);
	public abstract void remove();
	public abstract ListItem get(int index);
}

//#########################
class EmployeeList extends List{
	private ArrayList al;
	
	Iterator i;
	
	public EmployeeList(){
		al = new ArrayList();
	}
	
	public int count(){
		return al.size();
	}
	
	public Iterator createIterator(int type){
		switch (type){
		case 1:
			i = new ListIterator(this);
			return i;
		case 2: 
			i = new ReverseListIterator(this);
			return i;
		}
		
		return null;
	}
	
	public void append(ListItem li){
		al.add(li);
	}
	
	public ListItem get(int index){
		return (ListItem)al.get(index);
	}
	
	public void remove(){
		
	}
}

//#########################
abstract class Iterator {
	public abstract boolean first();
	public abstract boolean last();
	public abstract boolean isDone();
	public abstract void next();
	public abstract ListItem currentItem();
}

//#########################
class ListIterator extends Iterator{
	private List myList;
	private int index;
	
	public ListIterator (List l){
		this.myList = l;
	}
	
	public boolean first(){
		index = 0 ;
		if (myList.count() == 0) {
			return false;
		} else {
			return true;
		}
	}
	public boolean last(){
		index = 0;
		if (myList.count() == 0) {
			return false;
		} else {
			index = myList.count()- 1;
			return true;
		}
	}
	public boolean isDone(){
		if (myList.count() == 0) return true;
		
		if (myList.count() == (index )){
			return true;
		} else {
			return false;
		}
	}
	public void next(){
		//System.out.println(index);
		
		if (myList.count() == 0) {
			index = 0;
		} else {
			index = index + 1;
		}
	}
	
	public ListItem currentItem(){
		if (myList.count() == 0) {
			return null;
		} else {
			return (ListItem)myList.get(index);
		}
	}
}

//#########################
class ReverseListIterator extends Iterator{
	private List myList;
	private int index;
	
	public ReverseListIterator (List l){
		this.myList = l;
	}
	
	public boolean first(){
		index = 0 ;
		if (myList.count() == 0) {
			return false;
		} else {
			index = myList.count() -1;
			return true;
		}
	}
	public boolean last(){
		if (myList.count() == 0) {
			return false;
		} else {
			index = 0;
			return true;
		}
	}
	public boolean isDone(){
		if (myList.count() == 0) return true;
		
		if (index < 0){
			return true;
		} else {
			return false;
		}
	}
	public void next(){
		//System.out.println(index);
		
		if (myList.count() == 0) {
			index = 0;
		} else {
			index = index - 1;
		}
	}
	
	public ListItem currentItem(){
		if (myList.count() == 0) {
			return null;
		} else {
			return (ListItem)myList.get(index);
		}
	}
}

//#########################
abstract class ListItem {
	abstract void print();
}

//#########################
class Employee extends ListItem {
	String lastname;
	String firstname;
	int age;
	
	public Employee(){}
	
	public Employee (String lastname, String firstname, int age) {
		this.lastname = lastname;
		this.firstname= firstname;
		this.age = age;
	}
	
	public void print(){
		System.out.println("Last name = " + lastname + "\t\t First name = " 
				+ firstname + "\t\t Age = " + age);
	}
}