package lecture_18_repetition;

public class AirLock implements IAirLock{

	private Door inner;
	private Door outer;
	
	public AirLock() {
		inner = new Door();
		outer = new Door();
	}
	
	Door getInner() {
		return inner;
	}
	
	@Override
	public void openInner() {
		openDoor(inner);
	}

	@Override
	public void openOuter() {
		openDoor(outer);
	}

	private void openDoor(Door door) {
		if(!inner.isOpen() && !outer.isOpen())
			door.open();
		else
			System.out.println("Can not open both at the same time");
	}

	@Override
	public void closeInner() {
		inner.close();
	}

	@Override
	public void closeOuter() {
		outer.close();
	}

	@Override
	public boolean innerIsOpen() {
		return inner.isOpen();
	}

	@Override
	public boolean outerIsOpen() {
		return outer.isOpen();
	}

	@Override
	public void lockInner() {
		inner.lock();
	}

	@Override
	public void unLockInner() {
		inner.unLock();
	}

	@Override
	public void lockOuter() {
		outer.lock();
	}

	@Override
	public void unLockOuter() {
		outer.unLock();		
	}

	public static void main(String[] args) {
		AirLock doors = new AirLock();
		doors.openOuter();
		doors.getInner().open();
	}
}
