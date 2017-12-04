package com.ying.chap00.innerclass;

public class Membership {
	private String coverage = "PL";
	
	public Member pm = new Member("Yinghui", "Hu");
	public Member am = new Member("Xuemei", "Zhang");
	
	public Membership(){
	}
	private static class Member {
		private String coverage = "BS";
		private String firstName = "";
		private String lastName = "";
//		private Membership parent = null;
			
		private Member(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
			
		}
		
		private static void calculate(int x, int y) {
		}
		
		private String toName() {
			return firstName + " " + lastName;
		}
	}
	
	
	
	public void printMemberName() {
		System.out.println(pm.toName());
		System.out.println(am.toName());
		System.out.println(pm.coverage);
		System.out.println(am.coverage);
	}
	
	
}
