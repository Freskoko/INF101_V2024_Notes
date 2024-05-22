package lecture_9_mutability_iterate;

public class RectangleMutable implements Rectangle{

	private int height;
	private int width;
	
	public RectangleMutable(int height,int width) {
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

	public void setHeight(int height) {
		if(height<=0)
			throw new IllegalArgumentException("Height must be positive");
		this.height = height;		
	}
	
	public void setWidth(int width) {
		if(width<=0)
			throw new IllegalArgumentException("Width must be positive");
		this.width = width;
	}

}
