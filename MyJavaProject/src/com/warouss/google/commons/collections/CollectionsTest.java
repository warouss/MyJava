package com.warouss.google.commons.collections;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

public class CollectionsTest {

	public static void main(String[] args) {
//		new CollectionsTest().testList();
		
//		new CollectionsTest().testMap();
		
//		new CollectionsTest().testCollections2();
		
		new CollectionsTest().testIterables();
		
//		new CollectionsTest().testFirstMatch();
		
//		new CollectionsTest().testComparisonChain();

	}
	
	private void testList() {
		System.out.println("Start testList()");
		List<Item> list1 = getRandomItems(10);
		List<Item> list2 = Lists.newArrayList(
				new Item(10, "Item 10"), 
				new Item(11, "Item 11"), 
				new Item(12, "Item 12"), 
				new Item(13, "Item 13"), 
				new Item(14, "Item 14"));
		
		Iterable it = Iterables.concat(list1, list2);
		print("it = "+it);
		
		print("first="+Iterables.getFirst(it, null));
		print("last="+Iterables.getLast(it));
		print("frequency="+Iterables.frequency(it, new Item(12, "Item 12")));

		System.out.println("End testList()");
	}
	
	
	
	private void testMap() {
		System.out.println("Test ImmutableMap =========================");
		Map<Integer, Item> map = ImmutableMap.<Integer, Item>builder()
			.put(1, new Item(1, "ITEM_1"))
			.put(2, new Item(2, "ITEM_2"))
			.put(3, new Item(3, "ITEM_3"))
	        .put(4, new Item(4, "ITEM_4"))
	        .build();
		
		try {
			map.put(5, new Item(5, "ITEM_5"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		print(map);
		
		System.out.println("\nTest BiMap  =========================");
		BiMap<String, Integer> words = HashBiMap.create();
	    words.put("First", 1);
	    words.put("Second", 2);
	    words.put("Third", 3);
	    
	    print("inverse 2 -> "+words.inverse().get(2));
		
		System.out.println("\nTest Multimap =========================");
		Multimap<String, String> multimap = ArrayListMultimap.<String, String>create();
		multimap.put("fruit", "apple");
		multimap.put("fruit", "banana");
		multimap.put("pet", "cat");
		multimap.put("pet", "dog");
		System.out.println(multimap.get("fruit"));
		
		System.out.println("\nTest Table =========================");
		Table<String,String,Integer> table = HashBasedTable.create();
		table.put("London", "Paris", 340);
		table.put("New York", "Los Angeles", 3940);
		table.put("London", "New York", 5576);
	    
	    print("Distance="+table.get("London", "Paris"));
	    print("table"+table.rowKeySet());
	    Table<String,String,Integer> transposed = Tables.transpose(table);
	    print("transposed"+transposed.rowKeySet());
	    
	    System.out.println("\nTest Multimap (grouping list) =========================");
	    List<String> names = Lists.newArrayList("John", "Adam", "Tom");
	    Multimap<Integer, String> groups = Multimaps.index(names, new Function<String,Integer>(){
			@Override
			public Integer apply(String input) {
				return input.length();
			}
	    });
	    print("groups="+groups);
	    
	    System.out.println("\nTest ClassToInstanceMap =========================");
	    ClassToInstanceMap<Number> number = MutableClassToInstanceMap.<Number>create();
	    number.put(Integer.class, 1);
	    number.put(Double.class, 1.01);
	    print("IntegerNumber = "+number.get(Integer.class));
	    print("DoubleNumber = "+number.get(Double.class));
		
	}
	
	private void testCollections2() {
		System.out.println("Start testCollections2()");
		Collection<Item> list = getRandomItems(10);
		Collection<Item> filtered = Collections2.filter(list, new Predicate<Item>() {
			@Override
			public boolean apply(Item i) {
				//System.out.println("\tapply("+i+")");
				Range r = Range.<Double>closed(0.0, 10.0);
				boolean bool = r.contains(i.getPrice());
				print(i+" ? price in "+r+" = "+bool);
				return bool;
			}
		});
		
		print("filter result ="+filtered);
		
		Collection<String> prices = Collections2.transform(filtered, new Function<Item, String>() {
			@Override
			public String apply(Item i) {
				NumberFormat f = NumberFormat.getCurrencyInstance();
				String price = f.format(i.getPrice());
				return price;
			}
		}); 
		
		print("transform result="+prices);
		
		System.out.println("Fin testCollections2()");
	}
	
	private void testIterables() {
		System.out.println("Start testIterables()");
		Collection<Item> list = getRandomItems(5);
		
		boolean any = Iterables.any(list, new Predicate<Item>() {
			@Override
			public boolean apply(Item i) {
				return Range.<Double>closed(9.0, 11.0).contains(i.getPrice());
			}
		});
		print("any result = "+any);
		
		Iterables.removeIf(list, new Predicate<Item>() {
			@Override
			public boolean apply(Item i) {
				return Range.<Double>lessThan(5.0).contains(i.getPrice());
			}
		});
		print("removeIf result ="+list);
		
		Iterable<Item> filtered = Iterables.filter(list, new Predicate<Item>() {
			@Override
			public boolean apply(Item i) {
				return Range.<Double>closed(9.0, 11.0).contains(i.getPrice());
			}
		});
		print("filter result ="+filtered);
		
		Iterable<String> prices = Iterables.transform(filtered, new Function<Item, String>() {
			@Override
			public String apply(Item i) {
				return NumberFormat.getCurrencyInstance().format(i.getPrice());
			}
		});
		
		print("transform result="+prices);
		
		
		System.out.println("Fin testIterables()");
	}
	
	private void testFirstMatch() {
		System.out.println("Start testFirstMatch()");
		Collection<Item> items = getRandomItems(10);
		
		System.out.println("\titems="+items);
		
		Optional<Item> result = FluentIterable.<Item>from(items).firstMatch(new Predicate<Item>() {
			@Override
			public boolean apply(Item i) {
				return Range.<Double>closed(9.0, 11.0).contains(i.getPrice());
			}
		});
		
		System.out.println("Optional : isPresent="+result.isPresent()+", orNull="+result.orNull());
		if(result.isPresent()) {
			System.out.println("item found = "+result.get());
		}
		
		
		System.out.println("End testFirstMatch()");
	}
	
	private void testComparisonChain() {
		System.out.println("Start testComparisonChain()");
		Comparator<Item> comparator = new Comparator<Item>() {
			@Override
			public int compare(Item i1, Item i2) {
				return ComparisonChain.start()
						.compare(i1.getPrice().intValue(), i2.getPrice().intValue())
						.compare(i1.getId(), i2.getId())
						.result();
			}
		};
		
		List<Item> list = getRandomItems(20);
		Collections.sort(list, comparator);
		print("int(price) : id : name : price");
		Iterables.all(list, new Predicate<Item>() {
			@Override
			public boolean apply(Item i) {
				print(i.getPrice().intValue()+" : "+i.getId()+" : "+i.getName()+" : "+NumberFormat.getCurrencyInstance().format(i.getPrice()));
				return true;
			}
		});
		System.out.println("End testComparisonChain()");
	}
	
	
	private List<Item> getRandomItems(int nbr) {
		List<Item> list = Lists.newArrayList();
		for (int i=0; i<nbr; i++) {
			int id = Double.valueOf(20*Math.random()).intValue();
			Double price = 20*Math.random();
			list.add(new Item(id, "Item "+id, price));
		}
		return list;
	}
	
	
	
	private class Item {
		private Integer id;
		private String name;
		private Double price;
		public Item(Integer id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public Item(Integer id, String name, Double price) {
			this.id = id;
			this.name = name;
			this.price = price;
		}

		public Integer getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		@Override
		public int hashCode() {
			return Objects.hashCode(id, name, price);
		}
		@Override
		public boolean equals(Object obj) {
			return this.hashCode() == obj.hashCode();
		}
		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.add("id", id)
					.add("name", name)
					.add("price", price)
					.omitNullValues()
					.toString();
		}
	}
	
	private void printt(Object o) {
		print("\t"+o);
	}
	private void print(Object o) {
		System.out.println("\t"+o);
	}

}
