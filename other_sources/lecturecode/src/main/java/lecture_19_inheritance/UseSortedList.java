package lecture_19_inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UseSortedList {

	public static void main(String[] args) {
		//List<Integer> numbers = new ArrayList<Integer>();
		//List<Integer> numbers = new SortedListInheritance();
		SortedListComposition numbers = new SortedListComposition();
		//List<Integer> numbers = new SortedListAbstract();
		Random rand = new Random();
		for(int i=0; i<10; i++) {
			numbers.add(rand.nextInt(100));
			System.out.println(numbers);
		}
	}
}
