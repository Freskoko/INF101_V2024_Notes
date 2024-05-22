package lecture_9_mutability_iterate;

import static lecture_9_mutability_iterate.Range.range;

public class UseRange {

	public static void main(String[] args) {
		Range range = new Range(1,10,1);
		System.out.println(range);
		
		RangeIterator iter = new RangeIterator(range);
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		for(int i : range) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for(int i : new Range(1,20,2)) {
			System.out.print(i+" ");
		}
		System.out.println();

		for(int i : range(1,20,2)) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for(int i : range(7,12)) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for(int i : range(20,12,-1)) {
			System.out.print(i+" ");
		}

	}
}
