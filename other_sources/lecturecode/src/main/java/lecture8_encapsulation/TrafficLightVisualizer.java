package lecture8_encapsulation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrafficLightVisualizer extends JPanel{

	private static final long serialVersionUID = 1L;

	TrafficLight state = new TrafficLight();

	public TrafficLightVisualizer() {
		view();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		drawRed(g);
		drawYellow(g);
		drawGreen(g);

	}

	void drawRed(Graphics g) {
		g.setColor(Color.RED);
		drawCircleByCenter(g, getLightSize()*1/2, state.isRed());
	}

	void drawYellow(Graphics g) {
		g.setColor(Color.YELLOW);
		drawCircleByCenter(g, getLightSize()*3/2, state.isYellow());
	}

	void drawGreen(Graphics g) {
		g.setColor(Color.GREEN);
		drawCircleByCenter(g, getLightSize()*5/2, state.isGreen());
	}

	int getlightRadius() {
		return getLightSize()/2-5;
	}

	int getLightSize() {
		return Math.min(getWidth(),getHeight()/3);
	}

	void drawCircleByCenter(Graphics g, int y,boolean fill){
		int radius = getlightRadius();
		int x = getWidth()/2;
		if(fill) {
			g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		}else {
			g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
		}

	}

	/** Run the terminal GUI in a window frame. 
	 * @throws InterruptedException */
	public void view() {
		JFrame frame = new JFrame("TrafficLight");
		frame.setSize(200,600);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		//frame.pack();
		frame.setVisible(true);
	}
	
	public void view(TrafficLight light) {
		this.state = light;
		repaint();
	}
	
	void run() {
		for(int i=0; i<100; i++) {
			state.next();
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TrafficLightVisualizer light = new TrafficLightVisualizer();
		light.view();
		light.run();
	}
}
