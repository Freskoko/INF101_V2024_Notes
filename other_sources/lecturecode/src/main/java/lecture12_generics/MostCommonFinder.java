package lecture12_generics;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class looks through an array to find 
 * the most common element
 * 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 *
 */

public class MostCommonFinder {

	public static void main(String[] args) {
		String[] votes = new String[] {"yes","no","no","yes","no","yes","no"};
		String majority = findMajorityGeneric(votes);
		System.out.println("Voting gave: "+majority);

		Integer[] guess = new Integer[]{7,9,3,5,7,8,5,5};
		int bestGuess = findMajorityGeneric(guess);
		System.out.println("Best guess is: "+bestGuess);
	}


	private static int findMajority(Integer[] array) {
		Integer bestAnswer = null;
		int numOccurences = 0;
		for(Integer num : array) {
			List<Integer> list = Arrays.asList(array);
			int count = Collections.frequency(list,num);
			if(count>numOccurences) {
				bestAnswer = num;
				numOccurences = count;
			}
		}
		return bestAnswer;	
	}

	private static String findMajority(String[] array) {
		String bestAnswer = null;
		int numOccurences = 0;
		for(String element : array) {
			int count = frequency(element,array);
			if(count>numOccurences) {
				bestAnswer = element;
				numOccurences=count;
			}
				
		}
		return bestAnswer;	
	}


	private static int frequency(String element, String[] array) {
		int count=0;
		for(String s : array) {
			if(s.equals(element)) {
				count++;
			}
		}
		return count;
	}
	
	static <T> T findMajorityGeneric(T[] array) {
		T bestAnswer = null;
		int numOccurences = 0;
		for(T num : array) {
			List<T> list = Arrays.asList(array);
			int count = Collections.frequency(list,num);
			if(count>numOccurences) {
				bestAnswer = num;
				numOccurences = count;
			}
		}
		return bestAnswer;	
	}
	
	//not a good idea
	private static Object findMajority(Object[] array) {
		return null;
		
	}
	
}
