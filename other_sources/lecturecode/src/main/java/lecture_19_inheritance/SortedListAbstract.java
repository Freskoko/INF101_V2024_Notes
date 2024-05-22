package lecture_19_inheritance;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;

public class SortedListAbstract extends AbstractList<Integer>{

	ArrayList<Integer> list;
	
	public SortedListAbstract() {
		list = new ArrayList<>();
	}
	
	@Override
	public Integer get(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public Integer set(int index, Integer element) {
		int num = list.set(index, element);
		Collections.sort(list);
		return num;
	}

	@Override
	public void add(int index, Integer element) {
		list.add(index, element);
		Collections.sort(list);
	}
	
	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}
}
