package lecture14;

public interface IShoppingItem {

	/**
	 * Each ShoppingItem must have a unique name
	 * 
	 * @return the name of this ShoppingItem
	 */
	public String getName();
	
	/**
	 * Returns the price of this ShoppingItem
	 * @return
	 */
	public double getPrice();
}
