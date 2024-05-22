package lecture5.objects;
import java.util.ArrayList;
import java.util.Collections;



/**
 * This program keeps track of a list of persons names and ages.
 * 
 * @author Martin Vatshelle
 */
public class PersonsObjects {

	public static void main(String[] args) {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		fill(persons);
		printAll(persons);
		removeLast(persons);
		System.out.println();
		printAll(persons);
		
	}

	/**
	 * Persons are stored in 2 lists, 
	 * index i in names gives the name of person i and 
	 * index i in ages gives the age of person i
	 * 
	 * This method removes the last person from the list
	 * 
	 * @param names a list of names
	 * @param ages a list of ages
	 */
	private static void removeLast(ArrayList<Person> persons) {
		persons.remove(persons.size()-1);
	}

	/**
	 * Fills two lists with the same number of elements.
	 * names.get(i) and ages.get(i) are the name and age of person i
	 *  
	 * @param names The List of names to be filled
	 * @param ages The List of ages to be filled
	 */
	public static void fill(ArrayList<Person> persons) {

		persons.add(new Person("Anna",12));
		persons.add(new Person("Per",3));
		persons.add(new Person("Hans",7));
		persons.add(new Person("Lise",9));
	}
	
	/**
	 * Prints out the names and ages of a list of Persons
	 * 
	 * @param names
	 * @param ages
	 */
	public static void printAll(ArrayList<Person> persons) {
		for(int i=0; i<persons.size(); i++) {
			Person p = persons.get(i);
			//System.out.println(p.name+" is "+ p.age+" years.");
			System.out.println(p);
		}
	}
}









