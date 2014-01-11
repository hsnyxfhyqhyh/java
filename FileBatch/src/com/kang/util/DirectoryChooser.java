package com.kang.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class DirectoryChooser {
	public String askForOpen(String origDirectory) {
		String dirname = "";
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setCurrentDirectory(new File(origDirectory));
			fc.showOpenDialog(new JFrame());
			dirname = fc.getSelectedFile().getPath();
			// System.out.println(fc.getSelectedFile().getPath());
		} catch (Exception e) {
			dirname = null;
		}

		return dirname;
	}
}
