package com.aaamidatlantic.demo.gui.ex07;

import java.awt.*;
import java.awt.event.*;

//输完字符后回车会打印出对应的值
public class TFActionEvent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TFFrame();
	}

}

class TFFrame extends Frame {
	TFFrame() {
		TextField tf = new TextField();
		add(tf);
		tf.addActionListener(new TFActionListener());
		pack();
		setVisible(true);
	}
}

class TFActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		TextField tf = (TextField) e.getSource();
		System.out.println(tf.getText());
		tf.setText("");
	}
}
