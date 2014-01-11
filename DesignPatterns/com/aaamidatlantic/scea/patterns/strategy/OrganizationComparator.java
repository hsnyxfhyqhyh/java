package com.aaamidatlantic.scea.patterns.strategy;


import java.text.Collator;
import java.util.Comparator;

public class OrganizationComparator implements Comparator{
	private Collator textComparator = Collator.getInstance();

    public int compare(Object o1, Object o2) {
      Contact c1, c2;
      if ((o1 instanceof Contact) && (o2 instanceof Contact)) {
        c1 = (Contact) o1;
        c2 = (Contact) o2;
        int compareResult = textComparator.compare(
            c1.getOrganization(), c2.getOrganization());
        if (compareResult == 0) {
          compareResult = textComparator.compare(c1.getLastName(), c2
              .getLastName());
        }
        return compareResult;
      } else
        return textComparator.compare(o1, o2);
    }

    public boolean equals(Object o) {
      return textComparator.equals(o);
    }
}
