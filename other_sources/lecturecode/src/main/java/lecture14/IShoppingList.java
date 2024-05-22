package lecture14;

import java.time.LocalDate;
import java.util.List;

public interface IShoppingList extends Iterable<IShoppingItem>{

	/**
	 * Returns a list of all items you need to buy.
	 * The order of items on this list is irrelevant.
	 * Multiple items to shop for should occur multiple times on the list.
	 * 
	 * E.g. If you have 3 apples on your shopping list 
	 * the list this method returns should contain 3 apples.
	 * 
	 * @return a list of all items to buy
	 */
	public List<IShoppingItem> getAllItems();
	
	/**
	 * Adds an item to this list.
	 * If item is not a valid ShoppingItem this method should throw an Exception.
	 * 
	 * @param item - the item to add
	 * @throws IllegalArgumentException if item is null or item.name is blank
	 */
	public void add(IShoppingItem item);
	
	
	/**
	 * Returns the number of items on this shopping list.
	 * 
	 * @return the number of items on the shopping list
	 */
	public int numItems();
	
	/**
	 * This gives the date this ShoppingList was created
	 * @return a date
	 */
	public LocalDate getDate();
	
	/**
	 * This gives the total sum of the price for all items in the list
	 * @return
	 */
	public double totalPrice();
	
	/**
	 * This methods checks how many items on the list has a given name.
	 * 
	 * If you have "Apple" 3 times on your list you should return 3
	 * 
	 * @param itemName - the name of the item to count
	 * @return the number of items with the given name
	 */
	public int getItemCount(String itemName);
	
	
	/**
	 * This method removes all items that cost more than a certain limit.
	 * 
	 * @param priceLimit items above this limit gets removed 
	 * @return the number of items removed
	 */
	public int removeExpensiveItems(double priceLimit);
}
