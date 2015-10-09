package com.warouss.apache.commons.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;

public final class OutputExemple {
	private static final String CLASS_NAME = OutputExemple.class.getName();
	
	private static final String INPUT = "This should go to the output.";

	
	public static void execute() {
		System.out.println("DEBUT "+CLASS_NAME);
		TeeInputStream teeIn = null;
		TeeOutputStream teeOut = null;
		
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(INPUT.getBytes("US-ASCII"));
			ByteArrayOutputStream out1 = new ByteArrayOutputStream();
			ByteArrayOutputStream out2 = new ByteArrayOutputStream();
			
			teeOut = new TeeOutputStream(out1, out2);
			teeIn = new TeeInputStream(in, teeOut, true);
			teeIn.read(new byte[INPUT.length()]);
			
			System.out.println("Output stream 1: " + out1.toString());
			System.out.println("Output stream 2: " + out2.toString());


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { teeIn.close(); }
			catch (IOException e) { e.printStackTrace(); }
		}
		


		
		
		System.out.println("FIN "+CLASS_NAME);
	}

}
