package lecture_18_repetition;

public class Door {

	private boolean isOpen;
	private boolean isLocked;

	public void open() {
		if(!isLocked)
			isOpen = true;	
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void close() {
		isOpen=false;		
	}

	public void lock() {
		if(!isOpen)
			isLocked=true;
	}

	public void unLock() {
		isLocked = false;
	}
	
}
