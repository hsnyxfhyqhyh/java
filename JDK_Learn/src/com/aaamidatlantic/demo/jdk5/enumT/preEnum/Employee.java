package com.aaamidatlantic.demo.jdk5.enumT.preEnum;

public class Employee {
	private String name;

	public Employee(String name) {
		this.name = name;
	}

	public String canExecute(int rank) {
		if (rank == Rank.CAN_READ) {
			return "can read";
		} else if (rank == Rank.CAN_WRITE) {
			return "can write";
		} else if (rank == Rank.FULL_ACCESS) {
			return "can read and write";
		} else {
			return "not sure";
		}
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			int rank = Integer.parseInt(args[0]);
			Employee e = new Employee("John");
			System.out.println(e.canExecute(rank));
		} else {
			System.out.println("Please provide an integer as parameter");
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Rank {
	public static final int CAN_READ = 1;

	public static final int CAN_WRITE = 2;

	public static final int FULL_ACCESS = 5;
}
