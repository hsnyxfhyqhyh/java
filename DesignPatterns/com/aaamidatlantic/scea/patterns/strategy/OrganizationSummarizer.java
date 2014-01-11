package com.aaamidatlantic.scea.patterns.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class OrganizationSummarizer implements SummarizingStrategy 
{

	 private Comparator comparator = new OrganizationComparator();

	  public String summarize(Contact[] contactList) 
	  {
	    StringBuffer product = new StringBuffer();
	    Arrays.sort(contactList, comparator);
	    for (int i = 0; i < contactList.length; i++) 
	    {
	      product.append(contactList[i].getOrganization());
	      product.append(DELIMITER);
	      product.append(SPACE);
	      product.append(contactList[i].getFirstName());
	      product.append(SPACE);
	      product.append(contactList[i].getLastName());
	      product.append(EOL_STRING);
	    }
	    return product.toString();
	  }

	  public String[] makeSummarizedList(Contact[] contactList) 
	  {
	    Arrays.sort(contactList, comparator);
	    String[] product = new String[contactList.length];
	    for (int i = 0; i < contactList.length; i++) 
	    {
	      product[i] = contactList[i].getOrganization() + DELIMITER + SPACE
	          + contactList[i].getFirstName() + SPACE
	          + contactList[i].getLastName() + EOL_STRING;
	    }
	    return product;
	  }
}
