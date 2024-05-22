package lecture12_generics;

public class Pair<T> {

	T x;
	T y;

	public Pair(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	void swap() {
		T temp = x;
		x = y;
		y = temp;
	}
	
	T getX(){
		return x;
	}
	
//	int sum() { //not possible to use +
//		return x+y;
//	}

	public static void main(String[] args) {
		Pair<String> p = new Pair<String>("Hei", "INF101");
		String x1 = p.getX();
		
		Pair<Integer> p2 = new Pair<Integer>(7,23);
		int x2 = p2.getX();
		
		Pair<Object> p3 = new Pair<>(new String("Hei"),101);
		p3.swap();
		Object x3 = p3.getX();
		//String text3 = (String)x3; avoid this
		
		System.out.println(x3);
	}
}
