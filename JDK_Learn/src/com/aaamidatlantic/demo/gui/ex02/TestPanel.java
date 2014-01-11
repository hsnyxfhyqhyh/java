package com.aaamidatlantic.demo.gui.ex02;

import java.awt.*;

public class TestPanel {
	public static void main(String args[]) {
		Frame f = new Frame("Java Frame with Panel");

		Panel p = new Panel(null);
		f.setLayout(null);
		f.setBounds(300, 300, 500, 500);
		f.setBackground(new Color(0, 0, 102));
		p.setBounds(50, 50, 400, 400);

		// panel的坐标是根据装载它的容器的坐标来决定的。
		p.setBackground(new Color(204, 204, 255));
		f.add(p);
		f.setVisible(true);
	}
}
