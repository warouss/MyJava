package com.warouss.io.tmp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.IIOException;

public class TemporaryFile {

	public static void main(String[] args) {
		try {
			
			new TemporaryFile().createTempFileOldWay();
			
//			new TemporaryFile().createTempFileWithDirOldWay();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * OldWay : version java < 7
	 * @throws IOException
	 */
	private void createTempFileOldWay() throws IOException {
	    File tempFile = File.createTempFile("tempfile-old", ".tmp");
	    PrintWriter writer = null;
	    try {
	        writer = new PrintWriter(new FileWriter(tempFile));
	        writer.println("Line1");
	        writer.println("Line2");
	    } finally {
	        if (writer != null)
	            writer.close();
	    }
	     
	    System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
	}
	
	private void createTempFileWithDirOldWay() throws IOException {
	    File tempDir = new File(System.getProperty("java.io.tmpdir", null), "tempdir-old");
	    if (!tempDir.exists() && !tempDir.mkdir())
	        throw new IIOException("Failed to create temporary directory " + tempDir);
	     
	    File tempFile = File.createTempFile("tempfile-old", ".tmp", tempDir);
	    PrintWriter writer = null;
	    try {
	        writer = new PrintWriter(new FileWriter(tempFile));
	        writer.println("Line1");
	        writer.println("Line2");
	    } finally {
	        if (writer != null)
	            writer.close();
	    }
	    System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
	}
	
	

}
