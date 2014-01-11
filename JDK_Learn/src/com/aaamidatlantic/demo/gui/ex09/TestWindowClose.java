package com.aaamidatlantic.demo.gui.ex09;

import java.awt.*;
import java.awt.event.*;

public class TestWindowClose {
	public static void main(String args[]) {
		new MyFrame("MyFrame");
	}
}

class MyFrame extends Frame {
	MyFrame(String s) {
		super(s);
		
		setLayout(null);
		setBounds(300, 300, 400, 300);
		this.setBackground(new Color(204, 204, 255));
		setVisible(true);

		//方法内的内部类
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				
				//0--正常退出， “-1”--
				System.exit(0);
			}
		});

	}
	
}
