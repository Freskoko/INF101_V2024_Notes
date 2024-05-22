package lecture10_gui;

import javax.swing.JFrame;

public class UseGraphics {

	public static void main(String[] args) {
		JFrame frame = new JFrame("My GUI app");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,500);
		frame.add(new SampleView());
		frame.setLocation(500, 100);
		frame.setVisible(true);
	}
	
}
