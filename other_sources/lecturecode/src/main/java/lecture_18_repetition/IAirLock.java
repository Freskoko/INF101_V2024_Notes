package lecture_18_repetition;

/**
 * An AirLock is a set of two doors where only one door be open at the time.
 * We call the two doors inner and outer door.
 * 
 * In addition each door has a lock, a locked door can not be opened before it is unlocked.
 * 
 * @author Martin Vatshelle
 */
public interface IAirLock {

	/** Opens the inner door if it is unlocked and it is allowed to open. */
	public void openInner();
	
	/** Opens the outer door if it is unlocked and it is allowed to open. */
	public void openOuter();
	
	/** Closes inner door if not already closed. */
	public void closeInner();
	
	/** Closes outer door if not already closed */
	public void closeOuter();
	
	/** @return true if innerDoor is open, false otherwise */
	public boolean innerIsOpen();
	
	/** @return true if outerDoor is open, false otherwise */
	public boolean outerIsOpen();

	/** locks the inner door if it is closed */
	public void lockInner();
	
	/** unlocks the inner door if it is locked */
	public void unLockInner();
	
	/** locks the outer door if it is closed */
	public void lockOuter();
	
	/** unlocks the outer door if it is locked */
	public void unLockOuter();
}


