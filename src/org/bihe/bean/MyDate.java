package org.bihe.bean;

import java.time.LocalDate;
import java.util.*;

import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.MyDateDaoImpl;

public class MyDate implements Comparable<MyDate>, Cloneable {
	private int id;
	private int day;
	private int month;
	private int year;

	/*
	 * This constructor accepts a string, in the format “DDMM/YYYY”, that
	 * initializes the day, month, and year instance variables.
	 */
	public MyDate(String Date) {
		StringTokenizer myTokens = new StringTokenizer(Date, "/");
		this.day = Integer.parseInt(myTokens.nextToken());
		this.month = Integer.parseInt(myTokens.nextToken());
		this.year = Integer.parseInt(myTokens.nextToken());
		this.id = generateId();
	}

	/*
	 * This copy constructor accepts another MyDate object as its argument,
	 * performing a deep copy of it.
	 */
	public MyDate(int month, int day, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.id = generateId();
	}

	/*
	 * This is a default (no-argument) constructor that initializes the date to
	 * 00/00/00
	 */
	
	public MyDate(LocalDate ld) {
		this.day = ld.getDayOfMonth();
		this.month  =ld.getMonthValue();
		this.year = ld.getYear();
		this.id = generateId();
	}
	
	public MyDate() {

	}

	public boolean lessThan(LocalDate ld) {
		if (year < ld.getYear() || (year == ld.getYear() && month < ld.getMonthValue())
				|| (year == ld.getYear() && month == ld.getMonthValue() && day < ld.getDayOfMonth()))
			return true;
		else
			return false;
	}

	public boolean moreThan(LocalDate ld) {
		if (year > ld.getYear() || (year == ld.getYear() && month > ld.getMonthValue())
				|| (year == ld.getYear() && month == ld.getMonthValue() && day > ld.getDayOfMonth()))
			return true;
		else
			return false;
	}

	public static LocalDate convertMydatToLocalDate(MyDate myDate) {
		return LocalDate.of(myDate.year, myDate.month, myDate.day);
	}
	/*
	 * toString method of MyDate returns a string giving the month name, followed by
	 * the day, followed by a comma, followed by the last two digits of the year
	 */
	public String toString() {
		String monthName = "";
		int lastDigits = year % 100;
		if (month == 1) {
			monthName = "January";
		}
		if (month == 2) {
			monthName = "February";
		}
		if (month == 3) {
			monthName = "March";
		}
		if (month == 4) {
			monthName = "April";
		}
		if (month == 5) {
			monthName = "May";
		}
		if (month == 6) {
			monthName = "June";
		}
		if (month == 7) {
			monthName = "July";
		}
		if (month == 8) {
			monthName = "August";
		}
		if (month == 9) {
			monthName = "September";
		}
		if (month == 10) {
			monthName = "October";
		}
		if (month == 11) {
			monthName = "November";
		}
		if (month == 12) {
			monthName = "December";
		}

		if (lastDigits < 10) {
			return day + " " + monthName + ",0" + lastDigits;
		}
		return day + " " + monthName + "," + lastDigits;
	}

	/*
	 * This method called lessThan, which accepts another MyDate object, and
	 * compares the argument to the current object. If the current date is less than
	 * the given date, it returns true. Else, it returns false.
	 */
	public boolean lessThan(MyDate d) {
		if (year < d.year || (year == d.year && month < d.month) || (year == d.year && month == d.month && day < d.day))
			return true;
		else
			return false;
	}

	/*
	 * This method called equals method which accepts another MyDate object, and
	 * compares the argument to the current object to see if all of their values are
	 * the same.
	 */
	public boolean equals(MyDate d) {
		if (year == d.year && month == d.month && day == d.day)
			return true;
		else
			return false;
	}

	// -------------------------------Mutator-----------------------------
	public void setId(int id) {
		this.id = id;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// --------------------------------Accessor--------------------------------

	public int getId() {
		return id;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	// ----------------------------Generating Id for Date------------------
	private static int generateId() {
		if (MyDateDaoImpl.getInstance().getAllElements().isEmpty()) {
			return 1;
		} else
			return getHighestId() + 1;
	}

	private static int getHighestId() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (MyDate hm : MyDateDaoImpl.getInstance().getAllElements()) {
			ids.add(hm.getId());
		}
		Collections.sort(ids);
		return ids.get(ids.size() - 1);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyDate other = (MyDate) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(MyDate o) {
		if (this.year != o.year) {
			return Integer.compare(this.year, o.year);
		} else if (this.month != o.year) {
			return Integer.compare(this.month, o.month);
		} else {
			return Integer.compare(this.day, o.day);
		}
	}

	@Override
	protected MyDate clone() throws CloneNotSupportedException {
		MyDate cloned = new MyDate();
		cloned.id = this.id;
		cloned.day = this.day;
		cloned.month = this.month;
		cloned.year = this.year;
		return cloned;
	}
}
