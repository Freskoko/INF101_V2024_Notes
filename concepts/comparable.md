# Comparable

*Sortering av objekter må gjøres manuelt med comparable*

Når vi skal sorterer en list med tall, så kan vi bruke `Collections.sort()`, men hva med Book objekter?

for å bruke `Collections.sort(List<Book>)` så må `Book` implementere `Comparable<Book>`

```java
public class Book implements Comparable<Book>{

    public String title;
	public String author;
    public int yearReleased;

	@Override
	public int compareTo(Book o) {
		return this.title.compareTo(o.title); // blir alphabetisk !
	}
}
```
også sortere en evt liste med books slikt:
`Collections.sort(List<Book>)`

Men! Vi kan også lage mer custom sorting!

feks:

```java
public class CompareAuthor implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		return o1.author.compareTo(o2.author);
	}
}
```
Da kan vi skrive dette:

`Collections.sort(List<Book>, new CompareAuthor());`

Enda mer komplisert:

```java
public class AuthorThenTitleThenYear implements Comparator<Book>{ // implements COMPARATOR

	@Override
	public int compare(Book o1, Book o2) {

		// kunne skrevet: Comparator<Book> authorComp = new CompareAuthor();
		Comparator<Book> authorComp = Comparator.comparing((b)->b.author);
		Comparator<Book> titleComp = Comparator.comparing((b)->b.title);
		Comparator<Book> yearComp = Comparator.comparing((b)->b.yearReleased);
		
		// the method thenComparing combines three Comparators
		// check author, if same then check title, if same check year
		Comparator<Book> compNoNull = authorComp.thenComparing(titleComp).thenComparing(yearComp);

		// the method nullsLast creates a Comparator that handles null (null always last)
		Comparator<Book> comp = nullsLast(compNoNull);
		return comp.compare(o1, o2);
	}	
}
```

Da kan vi sorte slikt:
`Collections.sort(List<Book>, new AuthorThenTitleThenYear());`

## Or, sorting in a bit of an easier fashion using lambda functions

NOTE: We assume `Song implements Comparable<Song>`

```java
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains two different methods for sorting
 * 
 * @author Martin Vatshelle
 */
public class MusicSorter {

	/**
	 * Sorts the list according to the alphabetic order of Song::getArtistName()
	 * @param music - the list of songs to be sorted
	 */
	public static void sortByArtist(List<Song> music) {
		Collections.sort(music, Comparator.comparing(Song::getArtist));
		// OR
		Collections.sort(music, Comparator.comparing((Song s) -> s.getArtist()));
	}
	
	/**
	 * Sorts the list according to the Date of Song::getReleaseDate()
	 * @param music - the list of songs to be sorted
	 */
	public static void sortByReleaseDate(List<Song> music) {
		Collections.sort(music, Comparator.comparing(Song::getReleaseDate));
		// OR
		Collections.sort(music, Comparator.comparing((Song s) -> s.getArtist()));
	}
}
```