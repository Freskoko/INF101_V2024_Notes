package lecture11_testing;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDay {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		System.out.println(today);
		
		DayOfWeek weekDay = dayOfWeek(today);
	}

	public static DayOfWeek dayOfWeek(LocalDate today) {
		if(today==null)
			throw new IllegalArgumentException("input can not be null");
		
		return today.getDayOfWeek();
	}
}
