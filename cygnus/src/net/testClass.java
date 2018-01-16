package net;

import java.text.NumberFormat;
import java.text.ParseException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class testClass {

	public static void main(String[] args) {

		testSubstring();
		
	}
	
	private static void testSubstring() {
		String s = "1.02.01.00.6";
		System.out.println(s.substring(0, s.lastIndexOf(".")));
	}
	
	private static void testSplitString() {
		String s = "8.08.00.00-1000000|8.01.00.00-1000000";
		String[] spl = s.split("\\|");
		System.out.println(spl[0]);
	}
	
	private static void testDateFormatter() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yy");
		DateTime dt = new DateTime();
		System.out.println(formatter.print(dt.getMillis()));
		
		NumberFormat nf = NumberFormat.getInstance();
		String s = "100,500.22";
		try {
			Double d = nf.parse(s).doubleValue();
			
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
