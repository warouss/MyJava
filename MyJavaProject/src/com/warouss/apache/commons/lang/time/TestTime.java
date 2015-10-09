package com.warouss.apache.commons.lang.time;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

public class TestTime {

	public static void main(String[] args) {
		new TestTime().testStopWatch();
		
//		new Test().testFormat();
		
//		new Test().testBuildDate();
	}
	
	private void testStopWatch() {
		final String methodName = "testStopWatch";
		System.out.println("Début "+methodName + " : "+new Date());
		StopWatch chrono = new StopWatch();
		chrono.start();
		try {
			Thread.sleep(1000);
			chrono.split();
			System.out.println("\tDurée (1)    : "+DurationFormatUtils.formatDurationHMS(chrono.getSplitTime()));
			
			Thread.sleep(201);
			chrono.split();
			System.out.println("\tDurée (2)    : "+DurationFormatUtils.formatDurationHMS(chrono.getSplitTime()));
			
			Thread.sleep(10);
		} catch (InterruptedException e) {e.printStackTrace();}
		chrono.stop();
		System.out.println("\tDurée Totale HMS : "+DurationFormatUtils.formatDurationHMS(chrono.getTime()));
		System.out.println("\tDurée Totale ISO : "+DurationFormatUtils.formatDurationISO(chrono.getTime()));
		System.out.println("\tDurée Totale Formatted : "+DurationFormatUtils.formatDuration(chrono.getTime(), "HH:mm:ss.mmm"));
		System.out.println("Fin "+methodName + " : "+new Date());
	}
	
	private void testFormat() {
		final String methodName = "testFormat";
		System.out.println("Début "+methodName);
		System.out.println("\tDate-> String : ");
		Date dt1 = new Date();
		String s_dt1 = DateFormatUtils.format(dt1, "yyyy/MM/dd-HH:MM:ss");
		System.out.println("\t"+dt1+" -> "+s_dt1);
		
		System.out.println("\tString -> Date");
		String s_dt2 = "20140605";
		Date dt2 = null;
		try {
			dt2 = DateUtils.parseDate(s_dt2, "yyyyMMdd");
			System.out.println("\t"+s_dt2+" -> "+dt2);
		} catch (ParseException e) {
			System.err.println("ParseException : "+e.getMessage());
		}
		
		//comparaison sameDay
		boolean sameDay = DateUtils.isSameDay(dt1, dt2);
		System.out.println("\tSameDay = "+sameDay);
		
		System.out.println("Fin "+methodName);
	}
	
	private void testBuildDate() {
		final String methodName = "testBuildDate";
		System.out.println("Début "+methodName);
		
		Date dt = new Date();
		System.out.println("\tDate 1 : "+dt);
		dt = DateUtils.addDays(dt, -10);
		System.out.println("\tDate 2 : "+dt);
		dt = DateUtils.addHours(dt, 13);
		System.out.println("\tDate 2 : "+dt);
		
		System.out.println("Fin "+methodName);
	}

}
