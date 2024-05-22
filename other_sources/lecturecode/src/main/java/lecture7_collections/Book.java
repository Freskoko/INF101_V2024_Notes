package lecture7_collections;

import java.util.Comparator;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;

public class Book implements Comparable<Book>{
	//feltvariabler
	String title;
	String author;
	
	public Book(String title, String author){
		this.title = title;
		this.author = author;
	}
	
	@Override
	public String toString() {
		return title+" by "+author;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof Book) {
			Book toCompare = (Book)obj;
			if(this.author.equals(toCompare.author) && this.title.equals(toCompare.title)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Book o) {
		return this.author.compareTo(o.author);
	}
}




