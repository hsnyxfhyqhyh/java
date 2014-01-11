package com.aaamidatlantic.demo.gui.ex05;

/*	范例名称：GridLayout应用举例
 * 	源文件名称：TestGridLayout
 *	要  点：GridLayout布局管理器的性质及用法
 */

import java.awt.*;

public class TestGridLayout {
	public static void main(String args[]) {
		Frame f = new Frame("GridLayout Example");
		Button b1 = new Button("b1");
		Button b2 = new Button("b2");
		Button b3 = new Button("b3");
		Button b4 = new Button("b4");
		Button b5 = new Button("b5");
		Button b6 = new Button("b6");
		f.setLayout(new GridLayout(3, 2));
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);

		// 这个pack（）是在所有的component上打包，正好让frame的尺寸能包容所有的component
		f.pack();
		f.setVisible(true);
	}
}
