package lecture14;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ShoppingListTest {

	private LocalDate date;
	private IShoppingList list;
	private ShoppingItem milk;
	private ShoppingItem bread;

	@BeforeEach
	void setup() {
		date = LocalDate.now();
		list = new ShoppingList(date);
		milk = new ShoppingItem("Milk",12);
		bread = new ShoppingItem("Bread", 10.2);
	}
	
	@Test
	void testCanConstruct() {
		LocalDate foundDate = list.getDate();
		assertEquals(date, foundDate);
	}
	
	@Test
	void testCanAdd() {
		List<IShoppingItem> items =  list.getAllItems();
		assertTrue(items.isEmpty());
		list.add(milk);
		items =  list.getAllItems();
		assertTrue(items.contains(milk));
	}
	
	@Test 
	void testGetTotalPrice() {
		assertEquals(0, list.totalPrice());
		list.add(milk);
		assertEquals(milk.getPrice(), list.totalPrice());
		list.add(milk);
		assertEquals(milk.getPrice()*2, list.totalPrice());		
	}
	
	@Test
	void testShoppingItem() {
		assertEquals(10.2, bread.getPrice());
		String name = bread.getName();
		assertEquals("Bread", name);
	}

	@Test
	void testGetItemCount() {
		assertEquals(0, list.getItemCount("Milk"));
		list.add(milk);
		assertEquals(1, list.getItemCount("Milk"));
		list.add(milk);
		assertEquals(2, list.getItemCount("Milk"));
		list.add(bread);
		assertEquals(2, list.getItemCount("Milk"));
	}
	
	@Test
	void addInvalidShoppingItem() {
		ShoppingItem blank = new ShoppingItem("", 10);
		assertThrows(IllegalArgumentException.class, () -> list.add(blank));
	}
	
	@Test
	void removeExpensiveItems() {
		fillRandom();
	}

	private void fillRandom() {
		String[] names = {"Milk","TV","Bread","Banana","Shoes","Jacket"};
		Random random = new Random();
		for(int i=0; i<100; i++) {
			String name = names[random.nextInt(names.length)];
			ShoppingItem item = new ShoppingItem(name, random.nextDouble(1000));
			list.add(item);
		}
		int sizeBefore = list.numItems();
		double limit = 500.0;
		int removed = list.removeExpensiveItems(limit);
		for(IShoppingItem item : list) {
			assertTrue(item.getPrice()<limit,"Price was supposed to be less than "+limit+" but was: "+item.getPrice());
		}
		assertEquals(sizeBefore-list.numItems(), removed,"The number of removed items does not match the change of size in the list");
	}
}
