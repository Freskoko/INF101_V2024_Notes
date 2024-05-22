package lecture_9_mutability_iterate;

public class UseRectangle {

	public static void main(String[] args) {
		RectangleMutable rect = new RectangleMutable(3,5);
		System.out.println(rect.getHeight());
		//rect.height = -7; not allowed for private variable
		//rect.setHeight(-7);//throws Exception
		System.out.println(rect.getHeight());

	}

}
