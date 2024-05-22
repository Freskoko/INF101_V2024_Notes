package lecture5.objects;

import java.util.ArrayList;

public class Library {
	
	ArrayList<Book> books;

	public Library() {
		books = new ArrayList<Book>();
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
	}
	
	
}
