package lecture_9_mutability_iterate;

public class RectangleImmutable implements Rectangle{

	private int height;
	private int width;
	
	public RectangleImmutable(int height,int width) {
		if(height<=0 || width<=0)
			throw new IllegalArgumentException("Height and width must be positive");
		this.height = height;
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

}
