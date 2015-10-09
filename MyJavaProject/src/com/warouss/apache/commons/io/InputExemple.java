package com.warouss.apache.commons.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.input.XmlStreamReader;

public final class InputExemple {
	private static final String CLASS_NAME = InputExemple.class.getName();
	
	private static final String PARENT_DIR = "C:\\Users\\warouss.NETIK\\Programmes\\DEV\\workspace\\ApacheCommonsProject\\ressources\\io";
	private static final String XML_PATH = PARENT_DIR+"\\in.xml";
	
	private static final String INPUT = "This should go to the output.";


	
	public static void execute() {
		System.out.println("DEBUT "+CLASS_NAME);
		
		File xml = FileUtils.getFile(XML_PATH);
		
		try (XmlStreamReader xmlReader = new XmlStreamReader(xml)) {
			System.out.println("XML encoding: " + xmlReader.getEncoding());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TeeInputStream tee = null;
		try {
			
			ByteArrayInputStream in = new ByteArrayInputStream(INPUT.getBytes("US-ASCII"));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			tee = new TeeInputStream(in, out, true);
			tee.read(new byte[INPUT.length()]);

			System.out.println("Output stream: " + out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { tee.close(); }
			catch (IOException e) { e.printStackTrace(); }
		}

		
		
		System.out.println("FIN "+CLASS_NAME);
	}

}
