package lecture7_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Library {
	
	List<Book> books;

	public Library() {
		books = new LinkedList<Book>();
	}
	
	void add(Book book) {
		books.add(book);
	}
	
	boolean hasBook(Book book) {
		return books.contains(book);
	}
	
	public static void main(String[] args) {
		Library myBooks = new Library();
		System.out.println(myBooks.books);
		
		Book myBook = new Book("Objects","Martin");
		
		myBooks.add(myBook);
		System.out.println(myBooks.books);
		
		Book myFavorite = new Book("INF101", "Sondre");
		System.out.println(myBooks.hasBook(myFavorite));
		myBooks.add(myFavorite);
		System.out.println(myBooks.hasBook(myFavorite));
		System.out.println(myBooks.books);
		
		Book wanted = new Book("Objects","Martin");
		System.out.println(myBooks.hasBook(wanted));
		
		myBooks.add(new Book("Dyr", "Tommy"));
		myBooks.add(new Book("Klima", "Susanne"));
		myBooks.add(new Book("Biler", "Ida"));
		myBooks.add(new Book("INF102", "Martin"));
		
		myBooks.books.add(null);
		System.out.println(myBooks.books);

		//sorting using Comparable
		if(!myBooks.books.contains(null)) {
			Collections.sort(myBooks.books); //will not work if the list contains null
		}
		else {
			Collections.sort(myBooks.books,Comparator.nullsLast(Comparator.naturalOrder()));
		}
		System.out.println(myBooks.books);

		//sorting using Comparator
		Collections.sort(myBooks.books, new CompareTitle());
		System.out.println(myBooks.books);

		//sorting using Comparator breaking ties
		Collections.sort(myBooks.books, new AuthorThenTitle());
		System.out.println(myBooks.books);
	}	
}
