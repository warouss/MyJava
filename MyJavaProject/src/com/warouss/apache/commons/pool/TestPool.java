package com.warouss.apache.commons.pool;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class TestPool {
	public static void main(String[] args) {
//		new TestPool().executeSimple();
		
		new TestPool().executeThread();
	}
	
	private void executeSimple() {
		try {
			System.out.println("Appel 1 de ChronoFactory ");
			Thread.sleep(2000);
			Chrono chrono = ChronoFactory.getInstance().getChrono();
			chrono.start();
			Thread.sleep(1000);
			chrono.stop();
			System.out.println("Time1_"+chrono.getId()+"="+chrono.getDuration());
			ChronoFactory.getInstance().returnChrono(chrono);
			Thread.sleep(1000);
			//---------------------------
			System.out.println("Appel 2 de ChronoFactory ");
			Chrono chrono2 = ChronoFactory.getInstance().getChrono();
			chrono2.start();
			Thread.sleep(1000);
			chrono2.stop();
			System.out.println("Time2_"+chrono2.getId()+"="+chrono2.getDuration());
			ChronoFactory.getInstance().returnChrono(chrono2);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void executeThread() {
		ExecutorService executor = Executors.newFixedThreadPool(20);
		for (int i = 0; i<20; i++) {
			executor.execute(new Task("Task_"+i));
		}
		try {
			Thread.sleep(2000);
			Chrono chrono = ChronoFactory.getInstance().getChrono();
			chrono.start();
			Thread.sleep(1000);
			chrono.stop();
			System.out.println("Time1_"+chrono.getId()+"="+chrono.getDuration());
			ChronoFactory.getInstance().returnChrono(chrono);
			Thread.sleep(2000);
			//---------------------------
			Chrono chrono2 = ChronoFactory.getInstance().getChrono();
			chrono2.start();
			Thread.sleep(1000);
			chrono2.stop();
			System.out.println("Time2_"+chrono2.getId()+"="+chrono2.getDuration());
			ChronoFactory.getInstance().returnChrono(chrono2);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private class Task implements Runnable {
		private String name = null;
		
		public Task(String name) {
			this.name=name;
		}
		

		@Override
		public void run() {
//			ObjectPool<Chrono> pool =  new GenericObjectPool<Chrono>(new ChronoFactory());
			try {
				Chrono chrono = ChronoFactory.getInstance().getChrono();
				name=name+"_"+chrono.getId();
				long startTime = Double.valueOf(Math.random()*1000).longValue();
				System.out.println(name+" : startTime="+startTime);
				Thread.sleep(startTime);
				chrono.start();
				Thread.sleep(500);
				chrono.stop();
				System.out.println(name+" : Duration="+chrono.getDuration());
				ChronoFactory.getInstance().returnChrono(chrono);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
