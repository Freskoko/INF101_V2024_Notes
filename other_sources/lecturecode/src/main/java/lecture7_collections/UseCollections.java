package lecture7_collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class UseCollections {
	
	public static void main(String[] args) {
		Collection<Integer> tall = getCollection(10);
		System.out.println(tall);
		Integer smallest = Collections.min(tall);
		System.out.println(smallest);
		ArrayList<Integer> listOfNumbers = new ArrayList<>(tall);
		Collections.sort(listOfNumbers);
		System.out.println(listOfNumbers);
		int count = Collections.frequency(tall, smallest);
		System.out.println(smallest+" occur "+count+" times.");
	}

	private static Collection<Integer> getCollection(int numElem) {
		ArrayList<Integer> list = new ArrayList<>();
		Random rand = new Random();
		for(int i=0; i<numElem; i++) {
			list.add(rand.nextInt(10));
		}
		return list;
	}
}
