package no.uib.inf101.fridge;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Fridge implements IFridge {
    
    //TODO: Implementer instansvariabler og konstruktør
    
    @Override	
    public int nItemsInFridge() {
        return 0;
    }

    @Override
	public int totalSize() {
	    return 0;
	}

	@Override
	public boolean placeIn(FridgeItem item) {
	    return false;
	}

	@Override
	public void takeOut(FridgeItem item) {
	    
	}

	@Override
	public void emptyFridge() {
	    
	}

	@Override
	public List<FridgeItem> removeExpiredFood() {
	    return null;
	}
}