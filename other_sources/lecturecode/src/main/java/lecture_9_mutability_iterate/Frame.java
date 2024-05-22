package lecture_9_mutability_iterate;

/**
 * This class stores the dimensions of a picture frame
 * 
 * @author Martin Vatshelle
 *
 */
public class Frame {

	private Rectangle inner;
	private Rectangle outer;
	
	public Frame(Rectangle inner, Rectangle outer) {
		if(inner.getHeight()>outer.getHeight())
			throw new IllegalArgumentException("Inner frame can not be higher than outer frame.");
		if(inner.getWidth()>outer.getWidth())
			throw new IllegalArgumentException("Inner frame can not be wider than outer frame.");
		this.inner = inner;
		this.outer = outer;
	}
	
	Rectangle getInner() {
		return inner;
	}
	
	Rectangle getOuter() {
		return outer;
	}
}
