package com.warouss.apache.commons.pool;

import java.util.NoSuchElementException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class ChronoFactory {
	
	private static ChronoFactory INSTANCE = new ChronoFactory();
	
	public static ChronoFactory getInstance() {
		return INSTANCE;
	}
	
	private ObjectPool<Chrono> pool = null;
	
	private ChronoFactory() {
		pool = new GenericObjectPool<Chrono>(new BasePooledChronoFactory());
	}
	
	public Chrono getChrono() throws NoSuchElementException, IllegalStateException, Exception {
		return pool.borrowObject();
	}
	
	public void returnChrono(Chrono chrono) throws Exception {
		pool.returnObject(chrono);
	}
	
	
	/**
	 *
	 */
	private class BasePooledChronoFactory extends BasePooledObjectFactory<Chrono> {

		@Override
		public Chrono create() throws Exception {
			return new Chrono();
		}

		@Override
		public PooledObject<Chrono> wrap(Chrono arg0) {
			return new DefaultPooledObject<Chrono>(arg0);
		}

	}
	
	

}
