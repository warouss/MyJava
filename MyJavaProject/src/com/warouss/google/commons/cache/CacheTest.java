package com.warouss.google.commons.cache;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CacheTest extends CacheLoader<Long, Product> implements RemovalListener<Long, Product> {

	public static void main(String[] args) {
		new CacheTest().execute();

	}
	
	private void execute() {
		LoadingCache<Long, Product> cache = (LoadingCache<Long, Product>) CacheBuilder.newBuilder()
				.maximumSize(10)
				.refreshAfterWrite(3, TimeUnit.SECONDS)
				.removalListener(this)
				.build(this);
		
		for (long i=0; i<15; i++) {
			cache.put(i, new Product(i, "PRD_"+i, 10.5));
		}
		
		try {
			System.out.println(cache.get(Long.valueOf(6)));
			System.out.println(cache.get(Long.valueOf(2)));
			System.out.println("start sleep 1");
			Thread.sleep(2000);
			cache.put(15l, new Product(15l, "PRD_"+15, 10.5));
			System.out.println("end sleep 2");
			Thread.sleep(2000);
			System.out.println(cache.get(Long.valueOf(10)));//est rafraichi car t > 3 sec de la création
			System.out.println(cache.get(Long.valueOf(15)));//n'est pas rafraichi cat t<3 sec de la création
			System.out.println("invalidateAll()");
			cache.invalidateAll();
			System.out.println(cache.get(Long.valueOf(8)));
			
		} catch (ExecutionException e) {
			System.err.println("ExecutionException "+e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("InterruptedException "+e.getMessage());
		}
		
		CacheStats stats = cache.stats();
		System.out.println("Stats : hits="+stats.hitCount()+", loads="+stats.loadCount()+", loadSuccess="+stats.loadSuccessCount()+", miss="+stats.missCount());
	}

	@Override
	public void onRemoval(RemovalNotification<Long, Product> r) {
		System.err.println("<<Remove "+r.getValue());
		
	}

	@Override
	public Product load(Long key) throws Exception {
		Product p = new Product(key, "PRD_NEW_"+key, 11.99);
		System.err.println(">>Load "+p);
		return p;
	}
	
	
	

}
