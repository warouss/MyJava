package com.warouss.google.commons.base;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Range;

public class BaseTest {

	public static void main(String[] args) {
		
		new BaseTest().testObjects();
		
//		new BaseTest().testStopWatch();
		
//		new BaseTest().testSplitter_Joiner();

	}
	
	private void testSplitter_Joiner() {
		System.out.println("Start testString()");
		
		String st = " foo, ,bar, quux,";
		Iterable<String> it = Splitter.on(',').trimResults().omitEmptyStrings().split(st);
		print("Test Splitter \""+st+"\"");
		for (String s : it) {
			printt(s);
		}
		String[] tab = new String[] {"Kurt", "Kevin", null, "Chris"};
		print("Test Joiner "+Arrays.toString(tab));
		printt(Joiner.on(",").skipNulls().join(tab));
		
		System.out.println("End testString()");
	}
	
	private void testStopWatch() {
		System.out.println("Start testStopWatch()");
		Stopwatch chrono = Stopwatch.createStarted();
		try {
			Thread.sleep(2956);
		} catch (InterruptedException e) {
			System.err.println("Error : "+e.getMessage());
		}
		
		chrono.stop();
		print("Durée="+chrono.elapsed(TimeUnit.MINUTES)+"m");
		print("Durée="+chrono.elapsed(TimeUnit.SECONDS)+"s");
		print("Durée="+chrono.elapsed(TimeUnit.MILLISECONDS)+"ms");
		print("Durée="+chrono.elapsed(TimeUnit.NANOSECONDS)+"ns");
		
		System.out.println("End testStopWatch()");
	}
	
	private void testObjects() {
		System.out.println("Start testToString()");
		Produit p1 = new Produit(Long.valueOf(1), "prd01", null, 10.5, null);
		Produit p2 = new Produit(Long.valueOf(2), "prd02", "Product 02", null, new Date());
		print("p1="+p1+" : hashCode="+p1.hashCode());
		print("p2="+p2+" : hashCode="+p2.hashCode());
		try {
			print("test Preconditions.checkNotNull() ---");
			Produit p3 = new Produit(Long.valueOf(3), null, "Product 03", 13.99, new Date());
			print("p3="+p3+" : hashCode="+p3.hashCode());
		} catch (Exception e) {
			System.err.println("\t"+e.getMessage());
		}
		
		try {
			print("test Preconditions.checkArgument() ---");
			Produit p4 = new Produit(Long.valueOf(4), "prd04", "Product 04", null, new Date());
			p4.setPrice(-12.55);
			print("p4="+p4+" : hashCode="+p4.hashCode());
		} catch (Exception e) {
			System.err.println("\t"+e.getMessage());
		}
		
		System.out.println("End testToString()");
	}
	
	class Produit {
		private Long id;
		private String sku;
		private String name;
		private Double price;
		private Date startDate;
		public Produit(Long id, String sku, String name, Double price,
				Date startDate) {
			this.id = Preconditions.checkNotNull(id, "id must not be null");
			this.sku = Preconditions.checkNotNull(sku, "sku must not be null");
			this.name = name;
			this.price = price;
			this.startDate = startDate;
		}
		
		public void setPrice(Double price) {
			Preconditions.checkArgument(Range.<Double>greaterThan(0.0).contains(-13.99), "price must be > 0.0");
			this.price = price;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.add("id", id)
					.add("sku", sku)
					.add("name", name)
					.add("price", price)
					.add("startDate", startDate)
					.omitNullValues()
					.toString();
		}
		
		@Override
		public int hashCode() {
			return Objects.hashCode(id, sku, name, price, startDate);
		}
		
	}
	
	
	private void printt(Object o) {
		print("\t"+o);
	}
	private void print(Object o) {
		System.out.println("\t"+o);
	}
	

}
