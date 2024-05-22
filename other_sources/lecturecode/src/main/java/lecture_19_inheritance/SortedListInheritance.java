package lecture_19_inheritance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortedListInheritance extends ArrayList<Integer>{

	@Override
	public boolean add(Integer e) {
		super.add(e);
		Collections.sort(this);
		return true;
	}
	
}
