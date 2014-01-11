package com.kang.util;

import javax.swing.JOptionPane;

public class ClientInteractionUtil {
	
	/*
	 * show the message
	 */
	public static void ShowMessageDialogBox(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}
	
	/*
	 * 0 -- yes
	 * 1 -- no
	 */
	public static int ShowConfirmationDialogBox(String title, String message) {
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		
	}
}
