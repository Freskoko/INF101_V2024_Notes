package lecture_9_mutability_iterate;

import java.util.Iterator;

public class Range implements Iterable<Integer>{

	private int start;
	private int slutt;
	private int step;
	
	public int getStart() {
		return start;
	}

	public int getSlutt() {
		return slutt;
	}

	public int getStep() {
		return step;
	}

	Range(int start, int slutt, int step){		
		this.start = start;
		this.slutt = slutt;
		this.step = step;
	}

	Range(int start, int slutt){		
		this(start,slutt,1);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator(this);
	}
	
	@Override
	public String toString() {
		return "From: "+start+" to: "+slutt+" with steps of "+step;
	}
	
	
	public static Iterable<Integer> range(int start, int slutt, int step){
		return new Range(start,slutt,step);
	}

	public static Iterable<Integer> range(int start, int slutt){
		return new Range(start,slutt);
	}

}
