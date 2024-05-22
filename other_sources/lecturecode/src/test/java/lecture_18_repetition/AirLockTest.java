package lecture_18_repetition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AirLockTest {

	IAirLock doors;
	
	@BeforeEach
	void setup() {
		doors = new AirLock();
	}
	
	@Test
	void testDoorsStartsAsClosed() {
		assertFalse(doors.innerIsOpen(),"Doors should start as closed.");
		assertFalse(doors.outerIsOpen(),"Doors should start as closed.");
	}
	
	@Test
	void testDoorsCanInitiallyBeOpened() {
		doors.openInner();
		assertTrue(doors.innerIsOpen());

		doors = new AirLock();
		doors.openOuter();
		assertTrue(doors.outerIsOpen());
	}

	@Test
	void testDoorsCanBeOpenedAndClosed() {
		doors.openInner();
		assertTrue(doors.innerIsOpen());
		doors.closeInner();
		assertFalse(doors.innerIsOpen());

		doors.openOuter();
		assertTrue(doors.outerIsOpen());
		doors.closeOuter();
		assertFalse(doors.outerIsOpen());
	}

	@Test
	void testDoorsCannotBothBeOpen() {
		doors.openInner();
		assertTrue(doors.innerIsOpen());
		doors.openOuter();
		assertFalse(doors.outerIsOpen());

		doors = new AirLock();
		doors.openOuter();
		assertTrue(doors.outerIsOpen());
		doors.openInner();
		assertFalse(doors.innerIsOpen());

	}
	
	@Test
	void testLookedDoorsCanNotOpen() {
		doors.lockInner();
		doors.openInner();
		assertFalse(doors.innerIsOpen());

		doors.lockOuter();
		doors.openOuter();
		assertFalse(doors.outerIsOpen());
		
		doors.unLockInner();
		doors.openInner();
		assertTrue(doors.innerIsOpen());

	}

	@Test
	void testUnLock() {
		doors.lockInner();
		doors.lockOuter();
		doors.openInner();
		assertFalse(doors.innerIsOpen());
		doors.openOuter();
		assertFalse(doors.outerIsOpen());

		doors.unLockInner();
		doors.openInner();
		assertTrue(doors.innerIsOpen());
		doors.closeInner();
		doors.openOuter();
		assertFalse(doors.outerIsOpen());
		doors.lockInner();
		
		doors.unLockOuter();
		doors.openOuter();
		assertTrue(doors.outerIsOpen());
		doors.closeOuter();
		doors.openInner();
		assertFalse(doors.innerIsOpen());
	}

	@Test
	void testCanNotLockOpenDors() {
		doors.openInner();
		doors.lockInner();
		assertTrue(doors.innerIsOpen());
		doors.closeInner();
		assertFalse(doors.innerIsOpen());
		doors.openInner();
		assertTrue(doors.innerIsOpen());
	}

	
}
