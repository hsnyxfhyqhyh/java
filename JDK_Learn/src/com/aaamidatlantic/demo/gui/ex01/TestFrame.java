package com.aaamidatlantic.demo.gui.ex01;

/*	�������ƣ�Frame Ӧ�þ���
 * 	Դ�ļ����ƣ�TestFrame.java
 *	Ҫ  �㣺Frame����Ĵ�������ʾ����
 */

import java.awt.*;

public class TestFrame {
	public static void main(String args[]) {
		// title of the window will be "My First Test"
		Frame f = new Frame("My First Test");

		// frame���Ͻǵ�����꣬��λ������
		f.setLocation(300, 300);

		// ��λ������
		f.setSize(170, 100);

		// ����ɫΪ��ɫ
		f.setBackground(Color.blue);

		// ȱʡ��true
		f.setResizable(false);

		// ȱʡ�ǿ������ģ� ����Ҫ����������
		f.setVisible(true);
	}
}