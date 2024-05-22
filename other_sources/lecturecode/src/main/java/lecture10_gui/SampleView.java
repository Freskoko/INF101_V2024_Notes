package lecture10_gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SampleView extends JPanel{

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g.drawRect(50, 50, 20, 30);
		g2.drawRect(100, 100, 70, 70);
		
	}
}
