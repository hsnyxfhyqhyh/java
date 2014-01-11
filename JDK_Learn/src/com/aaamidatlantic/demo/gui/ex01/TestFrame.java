package com.aaamidatlantic.demo.gui.ex01;

/*	范例名称：Frame 应用举例
 * 	源文件名称：TestFrame.java
 *	要  点：Frame组件的创建及显示设置
 */

import java.awt.*;

public class TestFrame {
	public static void main(String args[]) {
		// title of the window will be "My First Test"
		Frame f = new Frame("My First Test");

		// frame左上角点的坐标，单位是像素
		f.setLocation(300, 300);

		// 单位是像素
		f.setSize(170, 100);

		// 背景色为蓝色
		f.setBackground(Color.blue);

		// 缺省是true
		f.setResizable(false);

		// 缺省是看不见的， 所以要有下面这行
		f.setVisible(true);
	}
}