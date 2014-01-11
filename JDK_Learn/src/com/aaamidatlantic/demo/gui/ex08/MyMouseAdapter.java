package com.aaamidatlantic.demo.gui.ex08;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyMouseAdapter {
	public static void main(String args[]) {
		new MyFrame("drawing...");
	}
}

class MyFrame extends Frame {
	ArrayList points = null;

	MyFrame(String s) {
		super(s);
		points = new ArrayList();
		
		setLayout(null);
		setBounds(300, 300, 400, 300);
		
		this.setBackground(new Color(204, 204, 255));
		setVisible(true);
		
		this.addMouseListener(new Monitor());
	}

	public void paint(Graphics g) {
		Iterator i = points.iterator();
		while (i.hasNext()) {
			Point p = (Point) i.next();
			g.setColor(Color.BLUE);
			g.fillOval(p.x, p.y, 10, 10);
		}
	}

	public void addPoint(Point p) {
		points.add(p);
	}
}

class Monitor extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		MyFrame f = (MyFrame) e.getSource();
		f.addPoint(new Point(e.getX(), e.getY()));
		
		//让frame强制重画。 如果没有这个方法调用，这些点也已经存在，在切换到其他界面再回来时，就
		//可以看到这些点了，因为这个时候frame会自动重画
		f.repaint();
	}
}
