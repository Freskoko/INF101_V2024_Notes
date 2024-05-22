package lecture7_collections;

import static java.util.Comparator.nullsLast;

import java.util.Comparator;

/**
 * This Comparator Compares author first, and if author is equal title is used to break ties.
 * null elements are always after Book objects
 * 
 * @author Martin Vatshelle
 *
 */
public class AuthorThenTitle implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {

		//Create a Comparator that does not handle null
		//the following two lines are equivalent
		//Comparator<Book> authorComp = new CompareAuthor();
		Comparator<Book> authorComp = Comparator.comparing((b)->b.author);

		//Create a second Comparator for comparing on title
		//the following two lines are equivalent
		//Comparator<Book> titleComp = new CompareTitle();
		Comparator<Book> titleComp = Comparator.comparing((b)->b.title);
		
		//the method thenComparing combines two Comparators
		//if the title is the same, then author is compared instead
		Comparator<Book> compNoNull = authorComp.thenComparing(titleComp);

		//the method nullsLast creates a Comparator that handles null first
		//if either object is null, the null is always last
		//if no objects is null, the compNoNull is used
		Comparator<Book> comp = nullsLast(compNoNull);
		return comp.compare(o1, o2);
	}	
}
