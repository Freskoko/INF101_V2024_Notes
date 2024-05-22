package lecture14;

public class ShoppingItem implements IShoppingItem {

	String name;
	double price;
	
	public ShoppingItem(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPrice() {
		return price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ShoppingItem) {
			ShoppingItem item = (ShoppingItem) obj;
			return this.getName().equals(item.getName());
		}
		return false;
	};

}
