package lecture14;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShoppingList implements IShoppingList {

	private ArrayList<IShoppingItem> shoppingList;
	private LocalDate createdDate;
	
	//Constructor
	
	public ShoppingList(LocalDate date) {
		shoppingList = new ArrayList<IShoppingItem>();
		createdDate = date;
	}

	//Methods
	
	@Override
	public Iterator<IShoppingItem> iterator() {
		return shoppingList.iterator();
	}

	@Override
	public List<IShoppingItem> getAllItems() {
		return Collections.unmodifiableList(shoppingList);
	}

	@Override
	public void add(IShoppingItem item) {
		if(item==null || item.getName().isBlank())
			throw new IllegalArgumentException("Invalid item");
		shoppingList.add(item);
	}

	@Override
	public int numItems() {
		return shoppingList.size();
	}

	@Override
	public LocalDate getDate() {
		return createdDate;
	}

	@Override
	public double totalPrice() {
		double total = 0.0;
		for(IShoppingItem item : shoppingList) {
			total += item.getPrice();
		}
		return total;
	}

	@Override
	public int getItemCount(String itemName) {
		int count = 0;
		for(IShoppingItem item : shoppingList) {
			if(item.getName().equals(itemName)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int removeExpensiveItems(double priceLimit) {
		int count =0;
		int index =0;
		while(index<shoppingList.size()) {
			IShoppingItem item = shoppingList.get(index);
			if(item.getPrice()>= priceLimit) {
				shoppingList.remove(index);
				count++;
			}
			else {
				index++;
			}
		}
		return count;
	}
}
