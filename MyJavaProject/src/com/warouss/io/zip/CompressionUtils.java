package com.warouss.io.zip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressionUtils {
	private static final Logger LOG = Logger.getLogger(CompressionUtils.class
			.getName());

	/**
	 * @param args
	 * @throws IOException 
	 * @throws DataFormatException 
	 */
	public static void main(String[] args) throws IOException, DataFormatException {
		StringBuffer sb = new StringBuffer("String;");
		for (int i=0; i<10000; i++) {
			sb.append("String").append(i).append(";");
		}
		String s = sb.toString();
		byte[] bt1 = compress(s.toString().getBytes());
		String s1 = new String(bt1);
		LOG.info("sb ?= bt1 "+s.equals(s1));
		byte[] bt2 = decompress(bt1);
		String s2 = new String(bt2);
		LOG.info("sb ?= bt1 "+s.equals(s2));

	}

	

	public static byte[] compress(byte[] data) throws IOException {

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);
		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer); // returns the generated
													// code... index
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		LOG.info("Original: " + data.length / 1024 + " Kb");
		LOG.info("Compressed: " + output.length / 1024 + " Kb");
		return output;
	}

	public static byte[] decompress(byte[] data) throws IOException,
			DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		LOG.info("Original: " + data.length / 1024 + " Kb");
		LOG.info("Compressed: " + output.length / 1024 + " Kb");
		return output;
	}

}
