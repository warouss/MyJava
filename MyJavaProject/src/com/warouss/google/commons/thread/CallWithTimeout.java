package com.warouss.google.commons.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.SimpleTimeLimiter;

public class CallWithTimeout {

	public static void main(String[] args) {
		new CallWithTimeout().testTimeout();

	}
	
	long startTime  = 0L;
	private void testTimeout() {
		final String methodName="testTimeout";
		System.out.println("START "+methodName+" ==========================================================");
		
		Long TIMEOUT = 2L;
		
		System.out.println("Timeout value = "+TIMEOUT+" s");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		startTime = System.currentTimeMillis();
		System.out.println("debut Test ");
		System.out.println("---------------------------------------------");
		String retour = null;
		try {
			retour = new SimpleTimeLimiter(executor).callWithTimeout(new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println("\tSTART call() - time="+(System.currentTimeMillis()-startTime)+" ms");
					String retour = null;
					for (int i=0; i<10; i++) {
						Thread.sleep(500);
						retour = "retour_"+i;
						System.out.println("\t\t "+i+" - time="+(System.currentTimeMillis()-startTime)+" ms");
					}
					System.out.println("\tEND call() - time="+(System.currentTimeMillis()-startTime)+" ms");
					return retour;
				}
				
			}, TIMEOUT, TimeUnit.SECONDS, true);
		} catch (Exception e) {
			System.err.println("\tException - time="+(System.currentTimeMillis()-startTime)+" ms : "+e.getMessage());
		} finally {
			executor.shutdown();
			System.out.println("\tINTERRUPTED call()");
		}
		System.out.println("---------------------------------------------");
		System.out.println("fin Test - time="+(System.currentTimeMillis()-startTime)+" ms");
		System.out.println("retour = "+retour);
		
		System.out.println("END "+methodName+" ==========================================================");
	}

}
