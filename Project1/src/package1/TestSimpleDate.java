package package1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
/**********************************************************************
 * This class tests the SimpleDate class. 
 * @author Paul Hood
 * @version 9/18/2013
 *
 *********************************************************************/
public class TestSimpleDate {
	
	/******************************************************************
	 * This method tests the default constructor
	 *****************************************************************/
	@Test
	public void testDefaultConstructor() {
		SimpleDate s1 = new SimpleDate();
		assertEquals(0, s1.getDay());
		assertEquals(0, s1.getMonth());
		assertEquals(0, s1.getYear());
	}
	
	/******************************************************************
	 * This method tests the constructor with integer parameters
	 *****************************************************************/
	@Test
	public void testIntConstructor() {
		SimpleDate s1 = new SimpleDate(11, 2, 1961);
		assertEquals(2, s1.getDay());
		assertEquals(11, s1.getMonth());
		assertEquals(1961, s1.getYear());
	}
	
	/******************************************************************
	 * This method tests the constructor a simpledate parameter
	 *****************************************************************/
	@Test
	public void testSimpleDateConstructor() {
		SimpleDate s1 = new SimpleDate("3/19/1834");
		SimpleDate s2 = new SimpleDate(s1);
		assertEquals(19, s1.getDay());
		assertEquals(3, s1.getMonth());
		assertEquals(1834, s1.getYear());
	}
	
	/******************************************************************
	 * This method tests the constructor a string parameter
	 *****************************************************************/
	@Test
	public void testStringConstructor() {
		SimpleDate s1 = new SimpleDate("6/23/1979");
		assertEquals(23, s1.getDay());
		assertEquals(6, s1.getMonth());
		assertEquals(1979, s1.getYear());
	}
	
	/******************************************************************
	 * This method assumes an IllegalArgumentException will be thrown 
	 * for a bad constructor.
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testFailConstructor1() {
		SimpleDate s1 = new SimpleDate("9-10-2013");
	}
	
	/******************************************************************
	 * This method assumes an IllegalArgumentException will be thrown 
	 * for a bad constructor.
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testFailConstructor2() {
		SimpleDate s1 = new SimpleDate("01 January 2013");
	}
	
	/******************************************************************
	 * This method assumes an IllegalArgumentException will be thrown
	 * for a bad constructor.
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testFailConstructor3() {
		SimpleDate s1 = new SimpleDate(13, 1, 1788);
	}
	
	/******************************************************************
	 * This method assumes an IllegalArgumentException will be throw 
	 * for a bad constructor.
	 ******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testFailConstructor4() {
		SimpleDate s1 = new SimpleDate("12/12/1752");
	}
	
	/******************************************************************
	 * This method expects IllegalArgumentException for setting the
	 * year incorrectly
	 *******************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testSetError() {
		SimpleDate s1 = new SimpleDate();
		s1.setDay(14);
		s1.setMonth(10);
		s1.setYear(1752);
	}
	
	/******************************************************************
	 * This method tests for proper month values.
	 *****************************************************************/
	@Test
	public void testMonth() {
		int month1 = new SimpleDate(12, 1, 1753).getMonth();
		int month2 = new SimpleDate("12/1/1753").getMonth();
		int month3 = new SimpleDate(6, 17, 1990).getMonth();
		int month4 = new SimpleDate(4, 19, 1875).getMonth();
		int month5 = new SimpleDate().getMonth();
		int month6 = new SimpleDate("2/29/2012").getMonth();
		
		assertEquals(12, month1);
		assertEquals(12, month2);
		assertEquals(6, month3);
		assertEquals(4, month4);
		assertEquals(0, month5);
		assertEquals(2, month6);
	}
	
	/******************************************************************
	 * This method expects setDay errors. The error will be if its less
	 * than 0 or greater than 31 because the month can be set to 0.
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testDayError() {
		SimpleDate s = new SimpleDate();
		s.setDay(0);
	}
	
	/******************************************************************
	 * This method expects setMonth errors
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testSetMonth() {
		SimpleDate s1 = new SimpleDate();
		s1.setMonth(25);
	}
	
	/******************************************************************
	 * This method tests for proper day values.
	 *****************************************************************/
	@Test
	public void testDay() {
		int day1 = new SimpleDate().getDay();
		int day2 = new SimpleDate(4, 25, 1788).getDay();
		int day3 = new SimpleDate("09/29/1900").getDay();
		int day4 = new SimpleDate("07/28/1988").getDay();
		int day5 = new SimpleDate(8, 14, 1800).getDay();
		int day6 = new SimpleDate(12,12,2012).getDay();
		
		assertEquals(0, day1);
		assertEquals(25, day2);
		assertEquals(29, day3);
		assertEquals(28, day4);
		assertEquals(14, day5);
		assertEquals(12, day6);
	}
	
	/******************************************************************
	 * This method tests for proper year values.
	 *****************************************************************/
	@Test
	public void testYear() {
		int year1 = new SimpleDate(12, 1, 1788).getYear();
		int year2 = new SimpleDate("12/1/1753").getYear();
		int year3 = new SimpleDate(6, 17, 1998).getYear();
		int year4 = new SimpleDate(4, 19, 2015).getYear();
		int year5 = new SimpleDate().getYear();
		int year6 = new SimpleDate("2/14/2033").getYear();
		
		assertEquals(1788, year1);
		assertEquals(1753, year2);
		assertEquals(1998, year3);
		assertEquals(2015, year4);
		assertEquals(0, year5);
		assertEquals(2033, year6);
	}
	
	/******************************************************************
	 * This method checks the isLeapYear() method for valid leap years.
	 *****************************************************************/
	@Test
	public void testTrueLeapYear() {
		SimpleDate d1 = new SimpleDate(1, 1, 1804);
    	SimpleDate d2 = new SimpleDate(2, 29, 2104);
    	SimpleDate d3 = new SimpleDate(3, 15, 1904);
    	SimpleDate d4 = new SimpleDate(12, 15, 1960);
    	SimpleDate d5 = new SimpleDate(2, 11, 1940);
    	SimpleDate d6 = new SimpleDate(10, 6, 1844);
    	SimpleDate d7 = new SimpleDate(11, 26, 1872);
    	SimpleDate d8 = new SimpleDate(11, 27, 1972);

    	assertTrue(d1.isLeapYear());
    	assertTrue(d2.isLeapYear());
    	assertTrue(d3.isLeapYear());
    	assertTrue(d4.isLeapYear());
    	assertTrue(d5.isLeapYear());
    	assertTrue(d6.isLeapYear());
    	assertTrue(d7.isLeapYear());
    	assertTrue(d8.isLeapYear());
	}
	
	/******************************************************************
	 * This method checks the static isLeapYear(int year) method for
	 *  invalid leap years.
	 *****************************************************************/
	@Test
	public void testStaticLeapYear() {
		// true
		assertTrue(SimpleDate.isLeapYear(1996));
		assertTrue(SimpleDate.isLeapYear(1992));
		assertTrue(SimpleDate.isLeapYear(1984));
		assertTrue(SimpleDate.isLeapYear(1964));
		assertTrue(SimpleDate.isLeapYear(1916));
		assertTrue(SimpleDate.isLeapYear(1912));
		
		// false
		assertFalse(SimpleDate.isLeapYear(2001));
		assertFalse(SimpleDate.isLeapYear(1785));
		assertFalse(SimpleDate.isLeapYear(1777));
		assertFalse(SimpleDate.isLeapYear(1834));
		assertFalse(SimpleDate.isLeapYear(2013));
	}
	
	/******************************************************************
	 * Method uses arrays and a loop to check invalid leapYears. It
	 * tests the isLeapYear method for falses leap years.
	 *****************************************************************/
	@Test 
	public void testFalseLeapYear() {
		
		// string of dates 
		String[] dates = { "1/1/1805", "1/30/1900", "10/26/2302",
				"2/6/2013" , "3/14/1999", "11/11/1821", "8/4/1935",
				"4/25/2001"};
		
		for(String date : dates) {
			SimpleDate notLeap = new SimpleDate(date);
			assertFalse(notLeap.isLeapYear());
		}
	}
	
	/******************************************************************
	 * This method tests the boolean equals() method. It tests for true
	 * and false returns.
	 *****************************************************************/
	@Test 
	public void testEquals() {
		SimpleDate d1 = new SimpleDate(1, 1, 2013);
		SimpleDate d2 = new SimpleDate("1/1/2013");
		SimpleDate d3 = new SimpleDate(d2);
		SimpleDate d4 = new SimpleDate(d3);
		SimpleDate d5 = new SimpleDate("6/25/1900");
		SimpleDate d6 = new SimpleDate(4, 25, 1990);
		SimpleDate d7 = new SimpleDate("5/5/1990");
		SimpleDate d8 = new SimpleDate(2, 6, 1999);
		
		// test return true
		assertTrue(d1.equals(d2));
		assertTrue(d1.equals(d3));
		assertTrue(d2.equals(d3));
		assertTrue(d2.equals(d1));
		assertTrue(d1.equals(d4));
		
		// test return false
		assertFalse(d1.equals(d8));
		assertFalse(d4.equals(d5));
		assertFalse(d5.equals(d6));
		assertFalse(d7.equals(d3));
		assertFalse(d8.equals(d4));
	}
	
	/******************************************************************
	 * This method checks the static boolean equals method with two 
	 * SimpleDate parameters. It checks for true and false equal Dates.
	 *****************************************************************/
	@Test 
	public void testBoolEquals() {
		SimpleDate d1 = new SimpleDate(1, 1, 2013);
		SimpleDate d2 = new SimpleDate("1/1/2013");
		SimpleDate d3 = new SimpleDate(d2);
		SimpleDate d4 = new SimpleDate(d3);
		SimpleDate d5 = new SimpleDate("6/25/1900");
		SimpleDate d6 = new SimpleDate(4, 25, 1990);
		SimpleDate d7 = new SimpleDate("5/5/1990");
		SimpleDate d8 = new SimpleDate(2, 6, 1999);
		
		// test return true 
		assertTrue(SimpleDate.equals(d1, d2));
		assertTrue(SimpleDate.equals(d2, d3));
		assertTrue(SimpleDate.equals(d3, d4));
		assertTrue(SimpleDate.equals(d4, d1));
		assertTrue(SimpleDate.equals(d3, d1));
		
		// test return false
		assertFalse(SimpleDate.equals(d1, d5));
		assertFalse(SimpleDate.equals(d5, d6));
		assertFalse(SimpleDate.equals(d6, d8));
		assertFalse(SimpleDate.equals(d4, d5));
		assertFalse(SimpleDate.equals(d7, d3));
		assertFalse(SimpleDate.equals(d2, d7));
	}
	
	/******************************************************************
	 * This method tests the compareTo() method. It tests values for 
	 * more than, less than, and equal to returns.
	 *****************************************************************/
	@Test
	public void testCompareTo() {
		SimpleDate d1 = new SimpleDate(4, 24, 1900);
		SimpleDate d2 = new SimpleDate(d1);
		SimpleDate d3 = new SimpleDate(6, 22, 1975);
		SimpleDate d4 = new SimpleDate(2, 29, 2012);
		SimpleDate d5 = new SimpleDate("6/23/1975");
		SimpleDate d6 = new SimpleDate(2, 14, 1772);
		SimpleDate d7 = new SimpleDate("7/11/2011");
		SimpleDate d8 = new SimpleDate(d7);
		SimpleDate d9 = new SimpleDate("2/29/2012");
		
		// test return 1
		assertTrue(d1.compareTo(d6) == 1);
		assertTrue(d4.compareTo(d3) == 1);
		assertTrue(d5.compareTo(d3) == 1);
		
		// test return -1
		assertTrue(d7.compareTo(d4) == -1);
		assertTrue(d3.compareTo(d5) == -1);
		assertTrue(d6.compareTo(d1) == -1);
		
		// test return 0
		assertTrue(d2.compareTo(d1) == 0);
		assertTrue(d8.compareTo(d7) == 0);
		assertTrue(d4.compareTo(d9) == 0);
	}
	
	/******************************************************************
	 * This method checks for valid returns from the ordinalDate()
	 * method.
	 *****************************************************************/
	@Test
	public void testOrdinal() {
		SimpleDate d1 = new SimpleDate(2, 10, 2013);
		SimpleDate d2 = new SimpleDate("4/25/1990");
		SimpleDate d3 = new SimpleDate(6, 22, 1975);
		SimpleDate d4 = new SimpleDate(2, 28, 2012);
		SimpleDate d5 = new SimpleDate("6/23/1975");
		
		assertEquals(41, d1.ordinalDate());
		assertEquals(115, d2.ordinalDate());
		assertEquals(173, d3.ordinalDate());
		assertEquals(60, d4.ordinalDate());
		assertEquals(174, d5.ordinalDate());
	}
	
	/******************************************************************
	 * Tests for valid increments from the increment() method. 
	 *****************************************************************/
	@Test
	public void testIncrement() {
		SimpleDate d1 = new SimpleDate(4, 24, 1900);
		SimpleDate d2 = new SimpleDate(4, 25, 1900);
		SimpleDate d3 = new SimpleDate(12, 31, 1899);
		SimpleDate d4 = new SimpleDate(1, 1, 1900);
		SimpleDate d5 = new SimpleDate("1/2/1990");
		SimpleDate d6 = new SimpleDate(1, 3, 1990);
		SimpleDate d7 = new SimpleDate("2/15/1772");
		SimpleDate d8 = new SimpleDate(2, 16, 1772);
		SimpleDate d9 = new SimpleDate(2, 28, 2012);
		SimpleDate d10 = new SimpleDate(2, 29, 2012);
		
		// increment days, should match the following day
		d1.increment();
		d3.increment();
		d5.increment();
		d7.increment();
		d9.increment();
		
		assertEquals(d2, d1);
		assertEquals(d4, d3);
		assertEquals(d6, d5);
		assertEquals(d8, d7);
		assertEquals(d10, d9);
		
	}
	
	/******************************************************************
	 * This method uses a java class to test the increment method
	 *****************************************************************/
	@Test
	public void testIncrement2() {
		SimpleDate s1 = new SimpleDate(8, 12, 2006);
		SimpleDate validDate = new SimpleDate();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(s1.getYear(), s1.getMonth() - 1, s1.getDay());
		cal.add(Calendar.DAY_OF_MONTH, 343);
		for (int i = 0; i < 343; i++) {
			s1.increment();
		}
		validDate.setDay(cal.get(Calendar.DAY_OF_MONTH));
		validDate.setMonth(cal.get(Calendar.MONTH) + 1);
		validDate.setYear(cal.get(Calendar.YEAR));
		assertTrue(s1.equals(validDate));
	}
	
	/******************************************************************
	 * Tests for valid decrement methods.
	 *****************************************************************/
	@Test
	public void testDecrement() {
		SimpleDate d1 = new SimpleDate(4, 24, 1900);
		SimpleDate d2 = new SimpleDate(4, 25, 1900);
		SimpleDate d3 = new SimpleDate(12, 31, 1899);
		SimpleDate d4 = new SimpleDate(1, 1, 1900);
		SimpleDate d5 = new SimpleDate("1/2/1990");
		SimpleDate d6 = new SimpleDate(1, 3, 1990);
		SimpleDate d7 = new SimpleDate("2/15/1772");
		SimpleDate d8 = new SimpleDate(2, 16, 1772);
		SimpleDate d9 = new SimpleDate(2, 28, 2012);
		SimpleDate d10 = new SimpleDate(2, 29, 2012);
		
		// decrement date, should match the date before
		d2.decrement();
		d4.decrement();
		d6.decrement();
		d8.decrement();
		d10.decrement();
		
		assertEquals(d1, d2);
		assertEquals(d3, d4);
		assertEquals(d5, d6);
		assertEquals(d7, d8);
		assertEquals(d9, d10);
	}
	
	/******************************************************************
	 * This method uses a java class to test the decrement method
	 *****************************************************************/
	@Test
	public void testDecrement2() {
		SimpleDate s1 = new SimpleDate(8, 12, 2006);
		SimpleDate validDate = new SimpleDate();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(s1.getYear(), s1.getMonth() - 1, s1.getDay());
		cal.add(Calendar.DAY_OF_MONTH, -2000);
		for (int i = 0; i > -2000; i--) {
			s1.decrement();
		}
		validDate.setDay(cal.get(Calendar.DAY_OF_MONTH));
		validDate.setMonth(cal.get(Calendar.MONTH) + 1);
		validDate.setYear(cal.get(Calendar.YEAR));
		assertTrue(s1.equals(validDate));
	}
	
	/******************************************************************
	 * This method checks for valid toString() methods from the
	 * toString() method.
	 *****************************************************************/
	@Test
	public void testToString() {
		SimpleDate d1 = new SimpleDate(4, 19, 1901);
		SimpleDate d2 = new SimpleDate(1, 25, 1966);
		SimpleDate d3 = new SimpleDate(6, 4, 1888);
		SimpleDate d4 = new SimpleDate(3, 4, 1944);
		SimpleDate d5 = new SimpleDate("2/26/1987");
		SimpleDate d6 = new SimpleDate(5, 19, 1778);
		SimpleDate d7 = new SimpleDate("2/3/1897");
		SimpleDate d8 = new SimpleDate(2, 8, 1772);
		
		assertEquals("19 April 1901", d1.toString());
		assertEquals("25 January 1966", d2.toString());
		assertEquals("04 June 1888", d3.toString());
		assertEquals("04 March 1944", d4.toString());
		assertEquals("26 February 1987", d5.toString());
		assertEquals("19 May 1778", d6.toString());
		assertEquals("03 February 1897", d7.toString());
		assertEquals("08 February 1772", d8.toString());
	}
	
	/******************************************************************
	 * Method tests for the proper number of SimpleDates created
	 *****************************************************************/
	@Test
	public void testNumberSimpleDates() {
		SimpleDate.reset();
		SimpleDate d1 = new SimpleDate(4, 19, 1901);
		SimpleDate d2 = new SimpleDate(1, 25, 1966);
		
		// check for proper number of SimpleDates
		assertEquals(2, SimpleDate.getNumberOfSimpleDates());
		
		SimpleDate d3 = new SimpleDate(6, 4, 1888);
		
		// check for proper number of SimpleDates
		assertEquals(3, SimpleDate.getNumberOfSimpleDates());
		
		SimpleDate d4 = new SimpleDate(3, 4, 1944);
		SimpleDate d5 = new SimpleDate("2/26/1987");
		SimpleDate d6 = new SimpleDate(5, 19, 1778);
		
		// check for proper number of SimpleDates
		assertEquals(6, SimpleDate.getNumberOfSimpleDates());
		
		SimpleDate d7 = new SimpleDate("2/3/1897");
		SimpleDate d8 = new SimpleDate(2, 8, 1772);
		
		// check for proper number of SimpleDates
		assertEquals(8, SimpleDate.getNumberOfSimpleDates());
	}
	
	/******************************************************************
	 * This method tests the save Method and the load method by saving 
	 * a simple date then creating a new simpledate set to the loaded 
	 * values. It then checks to see if the two dates match.
	 *****************************************************************/
	@Test
	public void testSaveAndLoad1() {
		SimpleDate d1 = new SimpleDate(1, 1, 2013);
		d1.save("d1");
		SimpleDate d2 = new SimpleDate();
		
		// the load method sets "this" to files SimpleDate
		d2.load("d1");
		assertTrue(d1.equals(d2));
	}
	
	/******************************************************************
	 * This method also tests the save and load methods
	 *****************************************************************/
	@Test
	public void testSaveAndLoad2() {
		SimpleDate s1 = new SimpleDate("7/9/2013");
		s1.save("s1");
		SimpleDate s2 = new SimpleDate();
		s2.load("s1");
		assertTrue(SimpleDate.equals(s1, s2));
	}
	
	/******************************************************************
	 * This method checks for a load failure with a file not found
	 *****************************************************************/
	@Test
	public void testSaveAndLoad3() {
		SimpleDate date = new SimpleDate(1, 15, 2000);
		date.save("test.txt");
		SimpleDate newDate = new SimpleDate();
		date.load("test");
		assertFalse(date.equals(newDate));
	}

	/******************************************************************
	 * This method creates a exact time and uses a java class to add 
	 * days to that time. It then creates another SimpleDate and runs
	 * the days from now method to see if they are equal.
	 *****************************************************************/
	@Test
	public void testDaysFromNow1() {
		
		// create new date with the current date month time
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		// add or subtract days
		cal.add(Calendar.DAY_OF_MONTH, -8350);
		SimpleDate validDate = new SimpleDate();
		
		// set New SimpleDate with valid value
		validDate.setDay(cal.get(Calendar.DAY_OF_MONTH));
		validDate.setMonth(cal.get(Calendar.MONTH) + 1);
		validDate.setYear(cal.get(Calendar.YEAR));
		
		// create new SimpleDate and run daysFromNow with same n
		SimpleDate s1 = new SimpleDate();
		s1 = s1.daysFromNow(-8350);
		
		// test for equivalency
		assertTrue(s1.equals(validDate));
	}
	
	/******************************************************************
	 * This method creates a exact time and uses a java class to add 
	 * days to that time. It then creates another SimpleDate and runs
	 * the days from now method to see if they are equal.
	 *****************************************************************/
	@Test
	public void testDaysFromNow2() {
		
		// create new date with the current date month time
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		// add or subtract days
		cal.add(Calendar.DAY_OF_MONTH, 500);
		SimpleDate validDate = new SimpleDate();
		
		// set New SimpleDate with valid value
		validDate.setDay(cal.get(Calendar.DAY_OF_MONTH));
		validDate.setMonth(cal.get(Calendar.MONTH) + 1);
		validDate.setYear(cal.get(Calendar.YEAR));
		
		// create new SimpleDate and run daysFromNow with same n
		SimpleDate s1 = new SimpleDate();
		s1 = s1.daysFromNow(500);
		
		// test for equivalency
		assertTrue(s1.equals(validDate));
	}
	
	/******************************************************************
	 * This method creates a exact time and uses a java class to add 
	 * days to that time. It then creates another SimpleDate and runs
	 * the days from now method to see if they are equal.
	 *****************************************************************/
	@Test
	public void testDaysFromNow3() {
		
		// create new date with the current date month time
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		// add or subtract days
		cal.add(Calendar.DAY_OF_MONTH, 53);
		SimpleDate validDate = new SimpleDate();
		
		// set New SimpleDate with valid value
		validDate.setDay(cal.get(Calendar.DAY_OF_MONTH));
		validDate.setMonth(cal.get(Calendar.MONTH) + 1);
		validDate.setYear(cal.get(Calendar.YEAR));
		
		// create new SimpleDate and run daysFromNow with same n
		SimpleDate s1 = new SimpleDate();
		s1 = s1.daysFromNow(53);
		assertTrue(s1.equals(validDate));
	}
	
	/******************************************************************
	 * This method tests for proper integer return of the daysSince
	 * method. 
	 *****************************************************************/
	@Test
	public void testDaysSince() {
		SimpleDate d1 = new SimpleDate(4, 19, 1901);
		SimpleDate d2 = new SimpleDate(1, 25, 1900);
		SimpleDate d3 = new SimpleDate(6, 4, 1888);
		SimpleDate d4 = new SimpleDate(3, 4, 1888);
		SimpleDate d5 = new SimpleDate("2/26/1987");
		
		assertEquals(-449, d1.daysSince(d2));
		assertEquals(-92, d3.daysSince(d4));
		assertEquals(92, d4.daysSince(d3));
		assertEquals(31808, d2.daysSince(d5));
		assertEquals(-36060, d5.daysSince(d3));
	}
	
	/******************************************************************
	 * This methods for proper return the the checkError method. It
	 * only uses one SimpleDate because it only accepts 3 integer
	 * parameters.
	 *****************************************************************/
	@Test
	public void testCheckError() {
		SimpleDate date = new SimpleDate();
		assertFalse(date.checkError(0, 4, 1990));
		assertFalse(date.checkError(3, 32, 1753));
		assertFalse(date.checkError(4, 2, 1752));
		assertFalse(date.checkError(4, 25, 1700));
		assertFalse(date.checkError(25, 4, 1700));
	}
	
	/******************************************************************
	 * Test for incorrect year.
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
    public void testIncorrectYear() {
        SimpleDate d1 = new SimpleDate("3/1/1700");
    }
	
	/******************************************************************
	 * Test for incorrect month
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testIncorrectMonth() {
		SimpleDate d1 = new SimpleDate("15/1/2300");
   }
	
	/******************************************************************
	 * Test for incorrect day
	 *****************************************************************/
	@Test (expected = IllegalArgumentException.class)
	public void testIncorrectDay() {
		SimpleDate d1 = new SimpleDate("13/32/2000");
  }
}
