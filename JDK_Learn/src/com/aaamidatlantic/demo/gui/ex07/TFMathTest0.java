package com.aaamidatlantic.demo.gui.ex07;

import java.awt.*;
import java.awt.event.*;

//the actionListener won't know the text fields in the parent frame object. So the author
//creates a constructor in actionListener with a parameter for the frame. The actionListener can 
//visit the textFields in the frame whenever the button in parent frame is clicked. 
public class TFMathTest0 extends Frame {
	TextField num1;
	TextField num2;
	TextField sum;

	public static void main(String[] args) {
		new TFMathTest0().launchFrame();
	}

	public void launchFrame() {
		num1 = new TextField();
		num2 = new TextField();
		sum = new TextField();
		num1.setColumns(10);
		num2.setColumns(10);
		sum.setColumns(15);
		setLayout(new FlowLayout());
		// setSize(500, 30);
		Label lblPlus = new Label("+");
		Button btnEqual = new Button("=");
		btnEqual.addActionListener(new MyListener0(this));
		add(num1);
		add(lblPlus);
		add(num2);
		add(btnEqual);
		add(sum);
		pack();
		setVisible(true);
	}
}

class MyListener0 implements ActionListener {
	// private TFMathTest2 tfmt;

	private TextField num1, num2, sum;

	public MyListener0(TFMathTest0 tfmt) {
		this(tfmt.num1, tfmt.num2, tfmt.sum);
	}

	public MyListener0(TextField num1, TextField num2, TextField sum) {
		this.num1 = num1;
		this.num2 = num2;
		this.sum = sum;
	}

	public void actionPerformed(ActionEvent e) {
		String s1 = num1.getText();
		String s2 = num2.getText();
		int i1 = Integer.parseInt(s1);
		int i2 = Integer.parseInt(s2);
		sum.setText(String.valueOf(i1 + i2));
	}
}
