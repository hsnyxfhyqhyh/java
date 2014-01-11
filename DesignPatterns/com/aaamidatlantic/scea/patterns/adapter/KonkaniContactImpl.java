package com.aaamidatlantic.scea.patterns.adapter;

public class KonkaniContactImpl implements KonkaniContact {

	
	private String nav;
	private String adNav;
	private String kam;
	private String kamaNav;

	public KonkaniContactImpl(){
	}

	public KonkaniContactImpl(String nav, String adNav,String kam, String kamaNav) 
	{
	    this.nav = nav;
	    this.adNav = adNav;
	    this.kam = kam;
	    this.kamaNav = kamaNav;
	}
	 
	public String navHadi() {
		// TODO Auto-generated method stub
		return nav;
	}

	public String adNavHadi() {
		// TODO Auto-generated method stub
		return adNav;
	}

	public String kamHadi() {
		// TODO Auto-generated method stub
		return kam;
	}

	public String kamaNavHadi() {
		// TODO Auto-generated method stub
		return kamaNav;
	}

	public void davariNav(String nav) {
		// TODO Auto-generated method stub
		this.nav = nav;
	}

	public void davariAdNav(String adNav) {
		// TODO Auto-generated method stub
		this.adNav = adNav;
	}

	public void davariKam(String kam) {
		// TODO Auto-generated method stub
		this.kam = kam;
	}

	public void davariKamaNav(String kamaNav) {
		// TODO Auto-generated method stub
		this.kamaNav = kamaNav;
	}

	public String toString() {
	    return nav + " " + adNav + ": " + kam + ", " + kamaNav;
	  }
}
