package com.warouss.java;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;


public class FormatTest {

	public static void main(String[] args) {
		
//		new FormatTest().stringToDate();
		
//		new FormatTest().dateToString();

		new FormatTest().numberFormat();
		
//		new FormatTest().decimalFormat();
	}
	
	private void stringToDate() {
		print("Date", "String");
		String PATTERN = "yyyyMMddHHmmss";
		String s_date = "22991231235959";
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
		try {
			Date date = dateFormat.parse(s_date);
			print(s_date, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void dateToString() {
		print("Date", "String");
		String PATTERN = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN);
		Date date = new Date();
		String s_date = dateFormat.format(date);
		print(date, s_date);
	}
	
	private void numberFormat() {
		print("Double", "NumberFormat");
		Double price = new Double(9876543.2199);
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
	    String s_price = currencyFormatter.format(price);
	    print(price, s_price);
	    currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	    s_price = currencyFormatter.format(price);
	    print(price, s_price);
	}
	
	private void decimalFormat() {
		String PATTERN = "#.##";
		print("Double", "String");
		Double price = Double.valueOf(9876543.2199);
		DecimalFormat decimalFormat = new DecimalFormat(PATTERN);
//		decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.FRANCE));
//		decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);
//		decimalFormat.setCurrency(Currency.getInstance(Locale.FRANCE));
		String s_price = decimalFormat.format(price);
		print(price, s_price);
	}
	
	private void print(Object o1, Object o2) {
		System.out.println(o1+" --> "+o2);
	}

}
