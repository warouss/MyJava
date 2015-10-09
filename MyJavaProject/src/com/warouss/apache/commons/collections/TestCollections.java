package com.warouss.apache.commons.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.ClosureUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

public class TestCollections {

	public static void main(String[] args) {
//		new Test().testForAllDo();
//		
//		new Test().testCollect();
		
//		new TestCollections().testFilter();
		
		new TestCollections().testPerformances();
	}

	private void testForAllDo() {
		final String methodName = "testForAllDo";
		System.out.println("Début "+methodName);
		List<String> list = Arrays.asList("aaa zzz", "bbb yyy", "ccc mmm",
				"ddd fff", "eee kkk", "fff xyz");

		CollectionUtils.forAllDo(list, new Closure<String>() {
			@Override
			public void execute(String arg0) {
				arg0 = WordUtils.capitalize(arg0);
				System.out.println("\targ0.capitalized="+arg0);
			}
		});
		
		System.out.println("Fin "+methodName);
	}
	
	private void testCollect() {
		final String methodName = "testCollect";
		System.out.println("Début "+methodName);
		List<String> list1 = Arrays.asList("item1", "item120", "item50", "item0", "item7");
		System.out.println("\tList1=" + list1);
		
		Collection<Integer> list2 = CollectionUtils.collect(list1, new Transformer<String, Integer>() {
			@Override
			public Integer transform(String arg0) {
				return Integer.parseInt(arg0.substring("item".length()));
			}
		});
		System.out.println("\tList2=" + list2);
		
		System.out.println("Fin "+methodName);
	}
	
	private void testFilter() {
		final String methodName = "testFilter";
		System.out.println("Début "+methodName);
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i=0; i<10; i++) {
			Double nbr = Math.random()*100;
			list1.add(nbr.intValue());
		}
		
		System.out.println("\tStart List1=" + list1);
		CollectionUtils.filter(list1, new Predicate<Integer>() {
			@Override
			public boolean evaluate(Integer i) {
				return i%2==0;
			}
		});
		System.out.println("\tFiltered List1=" + list1);
		System.out.println("Fin "+methodName);
	}
	
	private void testPerformances() {
		final String methodName = "testPerformances";
		System.out.println("Début "+methodName);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10000000; i++) {
			list.add(i);
		}
		
		StopWatch chrono = new StopWatch();
		chrono.start();
		for (Integer i : list) {i.equals(-1);}
		chrono.stop();
		System.out.println(DurationFormatUtils.formatDurationHMS(chrono.getTime())+" : For each loop");
		chrono.reset();
		
		chrono.start();
		int size1 = list.size();
		for (int i=0; i<size1; i++) {list.get(i).equals(-1);}
		chrono.stop();
		System.out.println(DurationFormatUtils.formatDurationHMS(chrono.getTime())+" : for i++");
		chrono.reset();
		
		chrono.start();
		CollectionUtils.forAllDo(list, new Closure<Integer>() {
			@Override
			public void execute(Integer i) {
				i.equals(-1);
			}
		});
		chrono.stop();
		System.out.println(DurationFormatUtils.formatDurationHMS(chrono.getTime())+" : CollectionUtils.forAllDo()");
		
		System.out.println("Fin "+methodName);
	}

}
