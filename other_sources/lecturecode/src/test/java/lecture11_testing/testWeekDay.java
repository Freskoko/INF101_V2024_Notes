package lecture11_testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Executable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

class testWeekDay {
	
	@Test
	void testMonday() {
		LocalDate day = LocalDate.parse("2024-02-26");
		DayOfWeek output = WeekDay.dayOfWeek(day);
		assertEquals(DayOfWeek.MONDAY, output);
	}

	@Test
	void testTuesday() {
		LocalDate day = LocalDate.parse("2024-02-27");
		DayOfWeek output = WeekDay.dayOfWeek(day);
		assertEquals(DayOfWeek.TUESDAY, output);
	}
	
	@Test
	void testNull() {
		assertThrows(NullPointerException.class, () -> WeekDay.dayOfWeek(null));
		
		assertTrue(5==5, "Math does not know 5=5");
	}

}
