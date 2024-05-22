package lecture5.objects;
import java.util.ArrayList;
import java.util.Collections;



/**
 * This program keeps track of a list of persons names and ages.
 * 
 * @author Martin Vatshelle
 */
public class Persons {

	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> ages = new ArrayList<Integer>(); 
		
		fill(names, ages);
		printAll(names, ages);
		removeLast(names, ages);
		System.out.println();
		printAll(names, ages);
		Collections.sort(names);
		Collections.sort(ages);
		System.out.println();
		printAll(names, ages);
		
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
	private static void removeLast(ArrayList<String> names, ArrayList<Integer> ages) {
		ages.remove(ages.size()-1);
		names.remove(names.size()-1);
	}

	/**
	 * Fills two lists with the same number of elements.
	 * names.get(i) and ages.get(i) are the name and age of person i
	 *  
	 * @param names The List of names to be filled
	 * @param ages The List of ages to be filled
	 */
	public static void fill(ArrayList<String> names, ArrayList<Integer> ages) {
		names.add("Anna");
		ages.add(12);

		names.add("Per");
		ages.add(3);

		names.add("Hans");
		ages.add(7);

		names.add("Lise");
		ages.add(9);
	}
	
	/**
	 * Prints out the names and ages of a list of Persons
	 * 
	 * @param names
	 * @param ages
	 */
	public static void printAll(ArrayList<String> names, ArrayList<Integer> ages) {
		for(int i=0; i<names.size(); i++) {
			System.out.println(names.get(i)+" is "+ ages.get(i)+" years.");
		}
	}
}









