package puzzles;

import java.util.Arrays;

public class Friday {
		
	 enum Day {
	 	SUNDAY,
	 	MONDAY, 
	 	TUESDAY, 
	 	WEDNESDAY,
	 	THURSDAY,
	 	FRIDAY,
	 	SATURDAY;
	 }

	 enum Month {
	 	JANUARY {
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
	 	},
	 	FEBRUARY {
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			if (!isLeapYear){
	 				return getDayFromFirstDay(firstDay, 28);
 				} else {
 					return getDayFromFirstDay(firstDay, 29);
 				}
	 		}
	 	},
	 	MARCH {
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
	 	},
	 	APRIL{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 30);
	 		}
	 	},
	 	MAY{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
 		},
	 	JUNE{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 30);
	 		}
	 	},
	 	JULY{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
	 	},
	 	AUGUST{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
 		},
	 	SEPTEMBER{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 30);
	 		}
 		},
	 	OCTOBER{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
 		},
	 	NOVEMBER{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 30);
	 		}
 		},
	 	DECEMBER{
	 		@Override
	 		Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear){
	 			return getDayFromFirstDay(firstDay, 31);
	 		}
 		};

	 	private static Day getDayOnDate(Day firstDay, int date){
	 		int firstDayIndex = firstDay.ordinal();
	 		int dayIndex = (firstDayIndex + date -1 )% 7;
	 		return Day.values()[dayIndex];
	 	}

	 	abstract Day getStartingDayOfNextMonth(Day firstDay, boolean isLeapYear);

	 	static Day getDayFromFirstDay(Day firstDay, int gap){ 		
	 		int firstDayIndex = firstDay.ordinal();
	 		int firstOfNextMonthIndex = (firstDayIndex + gap) % 7;
	 		return Day.values()[firstOfNextMonthIndex];
	 	}
	 		
	 }



	

	 private static void findDaysOnGiveDateGivenStartingDayOfYear(Day firstDay,  int date, boolean isLeapYear, int[] daysDistributionOnThirteenth){

	 	Day currentFirstDay = firstDay;

	 	for (Month m: Month.values()){
	 		Day day = m.getDayOnDate(currentFirstDay, date);
	 		daysDistributionOnThirteenth[day.ordinal()]++;
	 		currentFirstDay = m.getStartingDayOfNextMonth(currentFirstDay, isLeapYear);
	 	}


	 }


	 private static Day getStartingDayOfNextYear( Day firstDay, boolean isLeapYear){

	 	Day currentFirstDay = firstDay;

	 	for (Month m: Month.values()){
	 		currentFirstDay = m.getStartingDayOfNextMonth(currentFirstDay, isLeapYear);
	 	};

	 	if (!isLeapYear){
	 		Day expected = Month.getDayFromFirstDay(firstDay, 365);
	 		if (currentFirstDay != expected) throw new AssertionError("currentFirstDay:" + currentFirstDay + "  should be: " + expected);
		} else {
			Day expected = Month.getDayFromFirstDay(firstDay, 366);
	 		if(currentFirstDay != expected) throw new AssertionError("currentFirstDay:" + currentFirstDay + " should be: " + expected);
		}
	 	return currentFirstDay;
	 }

	 public static boolean isLeapYear(int year){
	 	boolean isDivisibleByFour = (year % 4 == 0);
	 	boolean isDivisibleByTwentyFive = (year % 25 == 0);
	 	boolean isDivisibleBySixteen = (year % 16 == 0);
	 	//System.out.println(String.valueOf(isDivisibleByFour) + String.valueOf( isDivisibleByTwentyFive) + String.valueOf(isDivisibleBySixteen));
	 	if (!isDivisibleByFour){
	 		return false;
	 	}

	 	
	 	if (!isDivisibleByTwentyFive){
	 		// not divisible by 100 but divisible by 4
	 		return true;
	 	} 
	 	
	 	//divisble by 100
	 	
	 	return isDivisibleBySixteen;
	 		
	 	
	 }



	 private static int[] findDaysOnGiveDatethGivenStartingAndEndYear(Day firstDay, int date, int yearOne, int yearEnd){

	 	int[] daysDistributionOnThirteenth = new int[Day.values().length];

	 	Day currentFirstDay = firstDay;

	 	for (int currentYear = yearOne; currentYear <= yearEnd; currentYear++){

	 		if (isLeapYear(currentYear)){
	 			findDaysOnGiveDateGivenStartingDayOfYear(currentFirstDay, date,true, daysDistributionOnThirteenth);
	 			currentFirstDay = getStartingDayOfNextYear(currentFirstDay, true );
	 		} else {
	 			findDaysOnGiveDateGivenStartingDayOfYear(currentFirstDay, date, false, daysDistributionOnThirteenth);
	 			currentFirstDay = getStartingDayOfNextYear(currentFirstDay, false);
	 		}
	 	}

	 	return daysDistributionOnThirteenth;

	 }

	 public static void printDaysFrequency(Day firstDay, int date, int yearOne,  int yearEnd){
	 	int[] days = findDaysOnGiveDatethGivenStartingAndEndYear(firstDay, date, yearOne, yearEnd);
	 	for (Day d: Day.values()){
	 		System.out.println(d.toString() + ": " + days[d.ordinal()] );
	 	}
	 }



	 public static void main(String[] args){

	 	printDaysFrequency(Day.MONDAY, 25, 1973,  2372);

	 }





	
	

}