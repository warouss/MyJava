package com.warouss.io.serialization;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Data implements Serializable {
	private static final long serialVersionUID = -2075686592457342738L;

	private String data;

	public Data(String d) {
		this.data = d;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	 * replacing serialized object to DataProxy object
	 */
	private Object writeReplace() {
		return new DataProxy(this);
	}

	private void readObject(ObjectInputStream ois)
			throws InvalidObjectException {
		throw new InvalidObjectException("Proxy is not used, something fishy");
	}

	@Override
	public String toString() {
		return "Data{data=" + data + "}";
	}

	private static class DataProxy implements Serializable {
		private static final long serialVersionUID = 2248007868797690046L;

		private String dataProxy;
		private static final String PREFIX = "ABC";
		private static final String SUFFIX = "DEFG";

		public DataProxy(Data d) {
			// obscuring data for security
			this.dataProxy = PREFIX + d.data + SUFFIX;
		}

		private Object readResolve() throws InvalidObjectException {
			if (dataProxy.startsWith(PREFIX) && dataProxy.endsWith(SUFFIX)) {
				return new Data(dataProxy.substring(3, dataProxy.length() - 4));
			} else
				throw new InvalidObjectException("data corrupted");
		}
	}

}
