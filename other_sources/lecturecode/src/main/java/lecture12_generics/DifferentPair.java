package lecture12_generics;

public class DifferentPair<T,V> {

	T x;
	V y;
	
	public DifferentPair(T x, V y) {
		this.x = x;
		this.y = y;
	}
	
	T getX(){
		return x;
	}
	
	V getY() {
		return y;
	}
	
	public static void main(String[] args) {
		DifferentPair<String,String> p = new DifferentPair<>("Hei", "INF101");
		String x1 = p.getX();
		
		DifferentPair<Integer,Integer> p2 = new DifferentPair<Integer,Integer>(7,23);
		int x2 = p2.getX();
		
		DifferentPair<String, Integer> p3 = new DifferentPair<>(new String("Hei"),101);
		String x3 = p3.getX();
		Integer y3 = p3.getY();
		
		System.out.println(x3);
	}
}
