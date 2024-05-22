package lecture_19_inheritance;

import java.util.ArrayList;
import java.util.List;

public class Wardrobe {

	private List<IStorage> storage;
	
	public Wardrobe() {
		storage = new ArrayList<IStorage>();
	}
	
	/**
	 * Installs a new shelf in this Wardrobe
	 * @param width how wide the shelf is
	 * @param depth how deep the shelf is
	 * @param height how high the shelf is
	 */
	public void BuildShelf(int width, int depth, int height) {
		storage.add(new Shelf(width,depth,height));
	}

	/**
	 * Installs a new drawer in this Wardrobe
	 * @param width how wide the drawer is
	 * @param depth how deep the drawer is
	 * @param height how high the drawer is
	 */	
	public void BuildDrawer() {
		storage.add(new Drawer(60,60,15));
	}

	/**
	 * Adds an item of clothing to this wardrobe
	 * @param item the clothing to add 
	 * @return true if there was a space for this item and this item was successfully added, false otherwise
	 */
	public boolean put(Clothing item){
		for(IStorage place : storage) {
			if(place.add(item)) {
				return true;
			}
		}
		return  false;
	}
	
	/**
	 * Takes out an item from the wardrobe
	 * @param item the item wanted
	 * @return true if item was found and removed, false otherwise
	 */
	public boolean remove(Clothing item) { //changed name to remove the name get was not very clear
		for(IStorage place : storage) {
			if(place.remove(item)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * A method to build an sample Wardrobe (a bit modest to be a dream wardrobe...)
	 * A method similar to this one should probably be made such that the user can give user input
	 * @return A Wardrobe  ready to store Clothing objects
	 */
	public static Wardrobe buildDreamWardrobe() {
		Wardrobe myWardrobe = new Wardrobe();
		for(int i=1;i<=10;i++) {
			myWardrobe.BuildShelf(60,60,30);
		}
		return myWardrobe;
	}
	
	/**
	 * Just a test that this works, should probably make JUnit tests instead of main method
	 * @param args
	 */
	public static void main(String[] args) {
		Wardrobe myWardrobe = Wardrobe.buildDreamWardrobe();
		System.out.println(myWardrobe.storage.size());
	}
}
