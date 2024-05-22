package lecture_9_mutability_iterate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameVisualizer extends JPanel{

	private static final long serialVersionUID = 1L;

	Frame pictureFrame;
	
	public FrameVisualizer(Frame frame) {
		this.pictureFrame = frame;
		view();
	}

	public static void show(Frame frame) {
		FrameVisualizer fv = new FrameVisualizer(frame);
		fv.view(frame);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		drawRectangle(g2, pictureFrame.getOuter(),Color.BLACK);
		drawRectangle(g2, pictureFrame.getInner(),Color.WHITE);
	}

	void drawRectangle(Graphics g, Rectangle rectangle,Color color){
		int x = (getWidth()-rectangle.getWidth())/2;
		int y = (getHeight()-rectangle.getHeight())/2;

		g.setColor(color);
		g.fillRoundRect(x, y, rectangle.getWidth(), rectangle.getHeight(), 10, 10);
	}

	
	/** Run the terminal GUI in a window frame. 
	 * @throws InterruptedException */
	public void view() {
		JFrame frame = new JFrame("Frame");
		int width = Math.max(200, this.pictureFrame.getOuter().getWidth()+20);
		int height = Math.max(600, this.pictureFrame.getOuter().getHeight()+20);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void view(Frame frame) {
		this.pictureFrame = frame;
		repaint();
	}
	
}
