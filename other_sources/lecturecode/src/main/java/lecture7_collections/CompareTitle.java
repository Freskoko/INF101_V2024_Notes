package lecture7_collections;

import java.util.Comparator;

/**
 * This Comparator compares the title of books
 * Null elements are always after Book Objects
 * 
 * @author Martin Vatshelle
 *
 */
public class CompareTitle implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {
		if(o1==null && o2==null) {
			return 0;
		}
		if(o1==null) {
			return 1;
		}
		if(o2==null) {
			return -1;
		}
		return o1.title.compareTo(o2.title);
	}

}
