package com.kang.system;

import java.io.File;

import com.kang.util.DirectoryChooser;
import com.kang.util.FileChooser;

import junit.framework.TestCase;

public class Test extends TestCase {
	public void testFileChooser() {
		FileChooser thisChooser = new FileChooser();
		thisChooser.dialog.setTitle("XML Document to Load");
		thisChooser.askForOpen();
		String filename = thisChooser.dialog.getDirectory().concat(
				thisChooser.dialog.getFile());
		if (filename == null) {
			System.out.println("");
		} else {
			System.out.println(filename);
		}
	}
	
	public void testDirectoryChooser() {
		DirectoryChooser dc = new DirectoryChooser();
		dc.askForOpen("G:\\");
	}
	
	public void testTraversingFilesAndDirectories(){
		DirectoryChooser dc = new DirectoryChooser();
		String dirname = dc.askForOpen("G:\\");
		
		File file = new File(dirname);

		if (file.isDirectory()) {
			System.out.println("Directory is  " + dirname);
			String str[] = file.list();
			for (int i = 0; i < str.length; i++) {
				File f = new File(dirname + "\\" + str[i]);
				if (f.isDirectory()) {
					System.out.println(str[i] + " is a directory");
				} else {
					System.out.println(str[i] + " is a file");
				}
			}
		} else {
			System.out.println(dirname + " is not a directory");
		}
	}
}
