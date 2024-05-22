package lecture7_collections;

import java.util.Comparator;

/**
 * This comparator compares the field author in the Book class
 * This Comparator does not handle null objects.
 * 
 * @author Martin Vatshelle
 *
 */
public class CompareAuthor implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		return o1.author.compareTo(o2.author);
	}

}
