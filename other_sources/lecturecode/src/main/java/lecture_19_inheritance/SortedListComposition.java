package lecture_19_inheritance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SortedListComposition{

	//fields
	ArrayList<Integer> list;

	public SortedListComposition() {
		list = new ArrayList<Integer>();
	}

	public Integer get(int index) {
		return list.get(index);
	}

	public boolean add(Integer e) {
		list.add(e);
		Collections.sort(list);
		return true;
	}


	
	
	
	
	//This code is already done
	
	
	public int size() {
		return list.size();
	}

	
	public boolean isEmpty() {
		return list.isEmpty();
	}

	
	public boolean contains(Object o) {
		return list.contains(o);
	}

	
	public Iterator<Integer> iterator() {
		return list.iterator();
	}

	
	public Object[] toArray() {
		return list.toArray();
	}

	
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	
	public boolean remove(Object o) {
		return list.remove(o);
	}

	
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	
	public boolean addAll(Collection<? extends Integer> c) {
		list.addAll(c);
		Collections.sort(list);
		return true;
	}

	
	public boolean addAll(int index, Collection<? extends Integer> c) {
		list.addAll(index,c);
		Collections.sort(list);
		return true;
	}

	
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	
	public void clear() {
		list.clear();
	}

	
	public Integer set(int index, Integer element) {
		return list.set(index, element);
	}

	
	public void add(int index, Integer element) {
		list.add(index,element);
		Collections.sort(list);
	}

	
	public Integer remove(int index) {
		return list.remove(index);
	}

	
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	
	public ListIterator<Integer> listIterator() {
		return list.listIterator();
	}

	
	public ListIterator<Integer> listIterator(int index) {
		return list.listIterator(index);
	}

	
	public List<Integer> subList(int fromIndex, int toIndex) {
		return subList(fromIndex, toIndex);
	}
	
	
	public String toString() {
		return list.toString();
	}
}
