package lecture_9_mutability_iterate;

import java.util.Iterator;

public class RangeIterator implements Iterator<Integer> {

	private Range range;
	private int current;
	
	public RangeIterator(Range range) {
		this.range = range;
		current = range.getStart();
	}

	@Override
	public boolean hasNext() {
		if(range.getStep()>=0) {
			return current<=range.getSlutt();
		}
		else {
			return current>=range.getSlutt();
		}
	}

	@Override
	public Integer next() {
		int value = current;
		current = current+range.getStep();
		return value;
	}

}
