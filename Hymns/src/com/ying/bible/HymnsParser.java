package com.ying.bible;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HymnsParser {

	public static void main(String[] args) {
		String lines = FileUtil.getContents(new File("Sch_Hymn.txt"));
		FileUtil.writeFile(lines, "hymns_sc.html");
		System.out.println("Done");
	}
}
