package com.aaamidatlantic.scea.patterns.state;

public class Stage {

	 private Actor actor = new HappyActor();

	  public void change() {
	    actor = new ComicActor();
	  }

	  public void performPlay() {
	    actor.act();
	  }

}
