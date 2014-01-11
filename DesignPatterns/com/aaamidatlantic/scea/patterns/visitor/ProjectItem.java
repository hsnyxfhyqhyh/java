package com.aaamidatlantic.scea.patterns.visitor;

import java.io.Serializable;
import java.util.ArrayList;
interface ProjectItem extends Serializable {
	  public void accept(ProjectVisitor v);

	  public ArrayList getProjectItems();
}
