package lecture_20_exams.library;

import java.util.ArrayList;

public class Library{
	ArrayList<Book> books = new ArrayList<Book>();

	Library(){
		//add books to the library
		books.add(new Book("Effective Java", "Joshua Bloch"));
		books.add(new Book("Clean Code", "Robert Martin"));
		books.add(new Book("Head First Design Patterns", "Elisabeth Robson"));		
	}
	
	/**
	 * Checks if the library has the book and prints a message
	 * @param book
	 */
	public void hasBook(Book book) {
		if(books.contains(book))
			System.out.println("Yes I can read this over summer!");
		else
			System.out.println("Oh no, I will not be an Java expert!");
	}

	public static void main(String[] args) {
		Library UiBLib = new Library();
		Book wanted = new Book("Clean Code", "Robert Martin");
		UiBLib.hasBook(wanted);
	}
}


