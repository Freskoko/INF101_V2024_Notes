package lecture_9_mutability_iterate;

public class UseFrame {

	public static void main(String[] args) {
		RectangleMutable outer = new RectangleMutable(400, 300);
		RectangleMutable inner = new RectangleMutable(340, 240);
		Frame frame = new Frame(inner, outer);
		inner.setHeight(440);
		FrameVisualizer fv = new FrameVisualizer(frame);
	}
}
