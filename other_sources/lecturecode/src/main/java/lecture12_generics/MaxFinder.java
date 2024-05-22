package lecture12_generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxFinder {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<100; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		
		int max = getMax(list);
		System.out.println(max);

	}

	private static <T extends Comparable<? super T>> T getMax(List<T> list) {
		T max = list.get(0);
		for(T i : list) {
			if(max.compareTo(i)<0) {
				max = i;
			}
		}
		
		return max;
	}

}
