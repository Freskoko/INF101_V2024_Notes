package lecture_19_inheritance;

/**
 * IStorage space represents a place to put various clothing items.
 * Shelf, Drawer and Bar was discussed in lecture.
 * LaundryBin, PileOnFloor and Hook could be other classes to be added later
 * @author mva021
 */
public interface IStorage {

	/**
	 * Gets the height of the storage space, can be used to check if an item fits inside.
	 * @return
	 */
	public int getHeight();

	/**
	 * Gets the width of the storage space, can be used to check if an item fits inside.
	 * @return
	 */
	public int getWidth();

	/**
	 * Gets the depth of the storage space, can be used to check if an item fits inside.
	 * @return
	 */
	public int getDepth();	
	
	/**
	 * Places an Clothing object in this storage space
	 * @param clothing - The item to place in the Wardrobe
	 * @return true if the clothing can be added, false otherwise
	 */
	public boolean add(Clothing clothing);
	
	/**
	 * Checks if a clothing fulfills the requirements for being placed.
	 * Dirty clothing should not end up in the shelves, but may end up in a LaundryBin
	 * Socks should not end up in shelves (unless they are in a Box which is placed in a Shelf)
	 * @param item an Clothing item
	 * @return true if the clothing can be added, false otherwise
	 */
	public boolean canAdd(Clothing item) ;
	
	/**
	 * Tries to remove an item of clothing from the Wardrobe.
	 *  
	 * @param item The clothing item to remove
	 * @return true if the item was found and removed, false otherwise.
	 */
	public boolean remove(Clothing item) ;
}
