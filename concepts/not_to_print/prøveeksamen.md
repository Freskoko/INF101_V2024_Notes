```java
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Fridge implements IFridge {
    
    private ArrayList<FridgeItem> itemsList = new ArrayList<>();
    private int fridgeCapacity = 0;
    
    public Fridge (int fridgeCapacity){
        this.fridgeCapacity = fridgeCapacity;
    }
    
    @Override	
    public int nItemsInFridge() {
        return this.itemsList.size();
    }

    @Override
	public int totalSize() {
	    return fridgeCapacity;
	}

	@Override
	public boolean placeIn(FridgeItem item) {
	    
	    if (this.nItemsInFridge() >= this.fridgeCapacity){
	        return false;
	    }
	    return this.itemsList.add(item);
	}

	@Override
	public void takeOut(FridgeItem item) {
	    
	    FridgeItem toTakeOut = null;
	    
	    for (FridgeItem fridgeItem: this.itemsList) {
	        if (fridgeItem.equals(item)) {
                toTakeOut = fridgeItem;
	        }
	    }
	    
	    if (toTakeOut == null){
	        throw new NoSuchElementException("Cannot remove " + item + " from fridge, its not in the fridge!");
	    }
	    this.itemsList.remove(toTakeOut);
	}

	@Override
	public void emptyFridge() {
	    this.itemsList = new ArrayList<FridgeItem>();
	}

	@Override
	public List<FridgeItem> removeExpiredFood() {
	    
	    ArrayList<FridgeItem> toRemove = new ArrayList<>();
	    
	    for (FridgeItem fridgeItem: this.itemsList){
	        if (fridgeItem.hasExpired()) {
	            toRemove.add(fridgeItem);
	        }
	    }
	    
	    for (FridgeItem fridgeItem: toRemove){
	        this.takeOut(fridgeItem);
	    }
	    
	    // eller:
	    // this.itemsList.removeAll(toRemove);

	    return toRemove;
    }
}
```