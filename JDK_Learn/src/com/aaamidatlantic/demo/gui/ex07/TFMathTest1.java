package com.aaamidatlantic.demo.gui.ex07;

import java.awt.*;
import java.awt.event.*;

public class TFMathTest1 {
	public static void main(String[] args) {
		new TFFrame1().launchFrame();
	}
}

class TFFrame1 extends Frame {
	TextField num1, num2, num3;

	public void launchFrame() {
		num1 = new TextField(10);
		num2 = new TextField(10);
		num3 = new TextField(15);
		
		Label lblPlus = new Label("+");
		
		Button btnEqual = new Button("=");
		
		btnEqual.addActionListener(new MyMonitor());
		
		setLayout(new FlowLayout());
		add(num1);
		add(lblPlus);
		add(num2);
		add(btnEqual);
		add(num3);
		pack();
		setVisible(true);
	}

	public class MyMonitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int n1 = Integer.parseInt(num1.getText());
			int n2 = Integer.parseInt(num2.getText());
			num3.setText("" + (n1 + n2));
		}
	}

}

