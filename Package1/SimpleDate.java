import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

/**********************************************************************
 * The following class converts integers or a string to a date with a 
 * spelled out month. The class can increase or decrease a date, check
 * for leap years and save or load dates. The minimum year is 1753.
 * 
 * @author Paul Hood
 * @version 9/18/2013
 *********************************************************************/

public class SimpleDate {
	
	/** represents the day for a SimpleDate */
	private int day;
	
	/** represents the month for a SimpleDate */
	private int month;
	
	/** represents the year for a SimpleDate */
	private int year;
	
	/** static variable counts the total number of SimpleDates */
	private static int totalDates = 0;
	
	/** static variable represents the minimum date allowed */
	public static final int minYear = 1753;
	
	/** static string array responsible for storing months */
	public static final String[] months = { "January", "February", 
		"March", "April", "May", "June", "July", "August",
		"September", "October", "November", "December" };
	
	/** static string responsible for storing days in month */
	public static final int[] totalMonthDays = { 31, 28, 31, 30, 31,
		30, 31, 31, 30, 31, 30, 31 };
	
	/******************************************************************
	 * This constructor takes no parameters and sets day, month and 
	 * year to 0. totalDates is increased by 1.
	 ******************************************************************/
	public SimpleDate() {
		day = 0;
		month = 0;
		year = 0;
		totalDates++;
	}
	
	/******************************************************************
	 * Constructor that sets the month, day and year to the parameters,
	 * it also checks for illegal arguments.
	 * @param month represents month for SimpleDate
	 * @param day represents day for SimpleDate
	 * @param year represents year for SimpleDate
	 *****************************************************************/
	public SimpleDate(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
		
		// check for errors
		if (!(checkError(month, day, year))) {
			throw new IllegalArgumentException("Not a valid entry");
		}	
		totalDates++;
	}
	
	/******************************************************************
	 * Constructor accepts another SimpleDate as a parameter, it also 
	 * checks for input errors and increases the number of totalDates.
	 * @param other SimpleDate that is set to new SimpleDate 
	 *****************************************************************/
	public SimpleDate (SimpleDate other) {
		day = other.day;
		month = other.month;
		year = other.year;
		
		//check for errors
		if (!(checkError(month, day, year))) {
			throw new IllegalArgumentException("Not a valid entry");
		}
		totalDates++;
	}
	
	/******************************************************************
	 * A constructor that accepts a string as a parameter with the
	 * following format: �9/21/2013� where 21 indicates day, 9
	 * indicates month,  and 2013 indicates year. You can assume the
	 * input has no errors  (i.e., a valid set of numbers) contained
	 * within and the year is a 4 digit number .
	 * @param startDate String in the form month, day , year
	 *****************************************************************/
	public SimpleDate(String startDate) {
		
		// split String into array by /
		String[] values = startDate.split("/");
		month = Integer.parseInt(values[0]);
		day = Integer.parseInt(values[1]);
		year = Integer.parseInt(values[2]);
		
		// check for errors
		if (!(checkError(month, day, year))) {
			throw new IllegalArgumentException("Not a valid entry");
		}	
		totalDates++;
	}
	
	/******************************************************************
	 * This is a getter method for the month property
	 * @return Current number value of month
	 *****************************************************************/
	public int getMonth() {
		return month;
	}
	
	/******************************************************************
	 * This is a setter method for the month property
	 * @param number value of month
	 *****************************************************************/
	public void setMonth(int month) {
		
		// check for errors
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Not a valid entry");
		}
		this.month = month;
	}
	
	
	/******************************************************************
	 * This is a getter method for the day property
	 * @return current number value of day
	 *****************************************************************/
	public int getDay() {
		return day;
	}
	
	/******************************************************************
	 * This is a setter method for the day property. This method has no
	 * validation properties because it can be set even if month is set
	 * to 0.
	 * @param number value of day
	 *****************************************************************/
	public void setDay(int day) {
		
		// check for numbers between 0 and 31. Cannot check for an
		// error with a month because month can be set to 0
		if (day < 1 || day > 31) {
			throw new IllegalArgumentException("Not a valid entry.");
		}
		this.day = day;
	}
	
	/******************************************************************
	 * This is a getter method for the year property
	 * @return Current number value of year
	 *****************************************************************/
	public int getYear() {
		return year;
	}

	/******************************************************************
	 * This is a setter method for the year property
	 * @param number value of year
	 *****************************************************************/
	public void setYear(int year) {
		
		// check for errors
		if (year < minYear) {
			throw new IllegalArgumentException("Not a valid entry");
		}
		this.year = year;
	}
	
	/******************************************************************
	 * This methodd resets the private static int variable totalDates 
	 * to 0. Mainly for testing purposes.
	 *****************************************************************/
	public static void reset() {
		totalDates = 0;
	}

	/******************************************************************
	 * Boolean method checks to see if "this" object is equal with the 
	 * input. Returns true if they are equivalent and false is not.
	 * @param other Object casted to SimpleDate
	 * @return false is not equivalent, true if equal
	 *****************************************************************/
	public boolean equals(Object other) {
		
		// cast other object to SimpleDate
		SimpleDate checkDate = (SimpleDate) other; 
		if (this.day == checkDate.day && this.month == checkDate.month
				&& this.year == checkDate.year) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/******************************************************************
	 * This method uses the public boolean equals method to check if
	 * two SimpleDates are equal
	 * @param s1 first SimpleDate object
	 * @param s2 second SimpleDate object
	 * @return true if equal, false if not equal
	 *****************************************************************/
	public static boolean equals(SimpleDate s1, SimpleDate s2) {
		if (s1.equals(s2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/******************************************************************
	 * A method that checks to see if an object is greater than, less,
	 * or equal to another SimpleDate object.
	 * @param other SimpleDate object to compare "this" to
	 * @return 1 if "this" is greater, -1 if less, 0 if equal
	 *****************************************************************/
	public int compareTo(SimpleDate other) {
		if (this.year > other.year) {
			return 1;
		}
		else if (this.year < other.year) {
			return -1;
		}
		else if (this.month > other.month) {
			return 1;
		}
		else if (this.month < other.month) {
			return -1;
		}
		else if (this.day > other.day) {
			return 1;
		}
		else if (this.day < other.day) {
			return -1;
		}
		
		// SimpleDates are equal
		else {
			return 0;
		}
	}
	
	/******************************************************************
	 * A method that checks if "this" year is a leap year.
	 * @return true if leap year, false is no leap year
	 *****************************************************************/
	public boolean isLeapYear() {
		
		// not leap year if not divisible by 4
		if (this.year % 4 != 0) {
			return false;
		}
		
		// divisible by 4 and not 100
		else if (this.year % 100 != 0) {
			return true;
		}
		
		// divisble by 100 and 400
		else if (this.year % 100 == 0 && this.year % 400 == 0) {
			return true;
		}
		
		// no leap year
		else {
			return false;
		}
	}
	
	/******************************************************************
	 * A method that checks if a integer is leap year
	 * @param year Integer that is checked for leap year
	 * @return true if leap year, false if not leap year
	 *****************************************************************/
	public static boolean isLeapYear(int year) {
		
		// same logic as equals() method is used
		if (year % 4 != 0) {
			return false;
		}
		else if (year % 100 != 0) {
			return true;
		}
		else if (year % 100 == 0 && year % 400 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/******************************************************************
	 * A method the returns the number of days at the beginning of the
	 * "this" year.
	 * @param dayCount keeps track of total days from beginning of year
	 * @return total days since beginning of year
	 *****************************************************************/
	public int ordinalDate() {
	int dayCount = 0;
	
	// count up the total days in months leading up to current month
	for (int i = 0; i < this.month - 1; i++) {
		dayCount += totalMonthDays[i];
	}
	
	// add an extra day if leap year
	if (isLeapYear()) {
		dayCount++;
	}
	
	// return total days plus current days in month
	return dayCount + this.day;
	}
	
	/******************************************************************
	 * A method that increments the "this" SimpleDate by 1 day
	 *****************************************************************/
	public void increment() {
		
		// checks leap year to avoid array out of bounds exception
		if (isLeapYear() && this.month == 2) {
			
			// leap year and february
			if (this.day < totalMonthDays[1] + 1) {
				this.day++;
			}
			
			// leap year, february and end of month
			else if (this.day == totalMonthDays[1] + 1) {
				this.day = 1;
				this.month++;
			}
		}
		
		// if not at end of month increase 1 day
		else if (this.day < totalMonthDays[this.month - 1]) {
			this.day++;
		}
		
		// if at end of month other than december
		else if (this.day == totalMonthDays[this.month - 1] &&
				this.month != 12) {
			this.day = 1;
			this.month++;
		}
		
		// at end of month and december, increase day, month and year
		else {
			this.day = 1;
			this.month = 1;
			this.year++;
		}
	}
	
	/******************************************************************
	 * A method that decrements the "this" SimpleDate by 1 day
	 *****************************************************************/
	public void decrement() {
		
		// if beginning of january month
		if (this.day == 1 && this.month == 1) {
			this.month = 12;
			this.day = totalMonthDays[this.month - 1];
			this.year--;
		}
		
		// if beginning of month
		else if (this.day == 1) {
			this.month--;
			day = totalMonthDays[this.month - 1];
			
			// if leap year increase day to 29
			if (this.month == 2 && isLeapYear(this.year)) {
				day++;
			}
		}
		else {
			this.day--;
		}
	}
	
	/******************************************************************
	 * Method that returns a string that represents a SimpleDate with
	 * the following format:  "Day Month Year".  
	 * @param display String display of output
	 * @return Return string in the format "Day Month Year"
	 *****************************************************************/
	public String toString() {
		if (this.month == 0) {
			return "0 0 0";
		}
		String display = this.day + " " + months[this.month - 1] + " " 
				+ this.year;
		
		// if day is less than 10 insert 0 in front of day number
		if (this.day < 10) {
			display = "0" + display;
		}
		return display;
	}
	
	/******************************************************************
	 * A method that returns the total number of SimpleDate objects
	 * created
	 * @return total number of SimpleDate objects
	 *****************************************************************/
	public static int getNumberOfSimpleDates() {
		return totalDates;
	}
	
	/******************************************************************
	 * This method writes "this" to a desired filename inside of the
	 * project folder.
	 * @param fileName name of the file created in project folder
	 *****************************************************************/
	public void save(String fileName) {
		PrintWriter out = null;
		try {
			
			// create new file
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName)));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// write out "this" SimpleDate
		out.println(this);
		
		// close PrintWriter
		out.close(); 

	}
	
	/******************************************************************
	 * This method loads the current file. Its currently set to print
	 * out the info in the file, but it could be used to create
	 * another simpledate object. It throws an error if it cannot find
	 * the file or if something goes wrong.
	 * @param fileName name of the file saved in the project folder
	 *****************************************************************/
	public void load(String fileName) {	
		try {
			Scanner fileReader = new Scanner(new File(fileName));
			
			// sets "this" to loaded simpledate
			this.day = Integer.parseInt(fileReader.next());
			String loadMonth = fileReader.next();
			
			// loops that assigns proper month value from month array
			for (int i = 0; i < months.length; i++) {
				if (loadMonth.equals(months[i])) {
					this.month = i + 1;
				}
			}
			
			// sets final value to year
			this.year = Integer.parseInt(fileReader.next());
		}

		// could not find file
		catch (FileNotFoundException error) {
			System.out.println("File not found ");
		}
			// problem reading the file
		catch (IOException error){
			System.out.println("Oops!  Something went wrong.");
			}

	}
	
	/******************************************************************
	 * This method returns a new SimpleDate object based on the number
	 * of days in the parameter. It can be greater or less than the
	 * current date. "Now" is used as the current day, month and year.
	 * @param n number of days to be increased or decreased by
	 * @return new SimpleDate based on number of days
	 * @throws IllegalArgumentException if errors occurs
	 *****************************************************************/
	public SimpleDate daysFromNow(int n) throws IllegalArgumentException {
		
		// use calender to get current month, day and year
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
	
		// cal.get(cal.MONTH) returns current month - 1
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		// create new SimpleDate based on actual date
		SimpleDate now = new SimpleDate(month, day, year);
		
		// this while loop increases or decreases the date until the 
		// total number of days hits 0
		while (n != 0) {
			
			// increase date
			if (n > 0) {
				now.increment();
				n--;
			}
			
			// decrease date
			else {
				now.decrement();
				n++;
			}
			
			// if n runs past the year 1753
			if (!(checkError(now.month, now.day, now.year))) {
				throw new IllegalArgumentException("Cannot decrement"
						+ " past " + minYear);
			}
		}	
		return now;
	}

	/******************************************************************
	 * This method returns the total number of days that have elapsed
	 * from "this" to another SimpleDate. The returned integer can be 
	 * positive or negative.
	 * @param other Other SimpleDate to find days between them
	 * @return Number of days between "this" and other SimpleDate
	 *****************************************************************/
	public int daysSince(SimpleDate other) {
		
		// used these integers to save the other values current dates.
		// I did this because I used the decrement() or increment()
		// method to find the number of total days. Creating a new 
		// SimpleDate would have costs totalSimpleDates() to be false.
		int otherDay = other.day;
		int otherMonth = other.month;
		int otherYear = other.year;
		
		// total day counter
		int daysSince = 0;
		
		// stops when the two SimpleDates are equal
		while(compareTo(other) != 0) {
			if(compareTo(other) == 1) {
				other.increment();
				daysSince--;
			}
			else if (compareTo(other) == -1) {
				other.decrement();
				daysSince++;
			}
		}
		
		// reset other to its original date
		other.day = otherDay;
		other.month = otherMonth;
		other.year = otherYear;
		return daysSince;
	}
	
	/******************************************************************
	 * This method checks for errors. It it used the the constructors.
	 * @param month Integer used to check for valid month
	 * @param day Integer used to check for valid day
	 * @param year Integer used to check for valid year
	 * @return true if valid input, false if invalid
	 *****************************************************************/
	public boolean checkError(int month, int day, int year) {
		
		// first check for valid month. I did this to prevent a 
		// ArrayOutOfBoundsException from being throw.
		if (month > 12 || month < 1) {
			return false;
		}
		int daysInMonth = totalMonthDays[month - 1];
		if (isLeapYear(year) && month == 2) {
			daysInMonth++;
		}
		
		// if day less than 1 or greater than days in month, check for
		// invalid month and invalid year
		if(day < 1 || day > daysInMonth || month > 12 || month < 1 ||
				year < minYear) {
			return false;
		}
		return true;
	}
}
