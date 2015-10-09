package com.warouss.apache.commons.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;

public final class UtilityExample {
	private static final String CLASS_NAME = UtilityExample.class.getName();
	
	public static void main(String[] args) {
		try {
			UtilityExample.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String DIR_PARENT = "C:\\Users\\warouss.NETIK\\Programmes\\DEV\\workspace\\ApacheCommonsProject\\ressources";
	private static final String DIR_IO = DIR_PARENT+"\\io";
	private static final String DIR_IO_COPY = DIR_PARENT+"\\ioCopy";
	private static final String FILE_TXT_NAME = "file.txt";
	private static final String FILE_STRING_NAME = "string.txt";
	private static final String FILE_COPY_NAME = "copy.txt";
	private static final String EXEMPLE_TXT_PATH = DIR_IO+"\\"+FILE_TXT_NAME;
	private static final String EXEMPLE_TXT_PATH_STRING = DIR_IO+"\\"+FILE_STRING_NAME;
	private static final String EXEMPLE_TXT_PATH_COPY = DIR_IO+"\\"+FILE_COPY_NAME;
	
	
	
	public static void execute() throws IOException {
		System.out.println("DEBUT "+CLASS_NAME);
		
		//**** FilenameUtils
		System.out.println("## FilenameUtils ==============================================");
		System.out.println("file Path : "+FilenameUtils.getFullPath(EXEMPLE_TXT_PATH));
		System.out.println("file name : "+FilenameUtils.getName(EXEMPLE_TXT_PATH));
		System.out.println("file extension : "+FilenameUtils.getExtension(EXEMPLE_TXT_PATH));
		System.out.println("file base name : "+FilenameUtils.getBaseName(EXEMPLE_TXT_PATH));
		
		//**** FileUtils 
		System.out.println("## FileUtils ==============================================");
		System.out.println("1) writeStringToFile & readFileToString ----");
		//stringToFile
		FileUtils.writeStringToFile(FileUtils.getFile(EXEMPLE_TXT_PATH_STRING), "Ceci est un test de la fonction FileUtils.writeStringToFile(File f, String s) "
				+ "\nde l'API Apache Commons IO");
		//fileToString
		String st = FileUtils.readFileToString(FileUtils.getFile(EXEMPLE_TXT_PATH_STRING));
		System.out.println("Contenu de "+FILE_STRING_NAME+" : ");
		System.out.println(st);
		//copyFile
		FileUtils.copyFile(FileUtils.getFile(EXEMPLE_TXT_PATH_STRING), FileUtils.getFile(EXEMPLE_TXT_PATH_COPY));
		String stCopy = FileUtils.readFileToString(FileUtils.getFile(EXEMPLE_TXT_PATH_COPY));
		System.out.println("Contenu de "+FILE_COPY_NAME+" : ");
		System.out.println(stCopy);
		//-checksum
		long checkSum = FileUtils.checksumCRC32(FileUtils.getFile(EXEMPLE_TXT_PATH_STRING));
		long checkSum_copy = FileUtils.checksumCRC32(FileUtils.getFile(EXEMPLE_TXT_PATH_COPY));
		System.out.println("test Checksum : "+checkSum+"?="+checkSum_copy+" : "+(checkSum==checkSum_copy));
		//copyDir
		FileUtils.copyDirectory(FileUtils.getFile(DIR_IO), FileUtils.getFile(DIR_IO_COPY));
		//-------------------------------------------------------------------------------------------
		System.out.println("\n\n");
		System.out.println("2) lineIterator ----");
		File exempleFile = FileUtils.getFile(EXEMPLE_TXT_PATH);
		LineIterator iter = FileUtils.lineIterator(exempleFile);
		System.out.println("Contenu de "+FilenameUtils.getName(EXEMPLE_TXT_PATH));
		while (iter.hasNext()) {
			System.out.println("\t"+iter.next());
		}
		iter.close();
		
		File directory = FileUtils.getFile(DIR_PARENT);
		System.out.println(DIR_PARENT+" contains "+FilenameUtils.getName(EXEMPLE_TXT_PATH)+" ? "+FileUtils.directoryContains(directory, exempleFile));
		
		//**** FileSystemUtils
		System.out.println("## FileSystemUtils ==============================================");
		System.out.println("Free disk space (in KB): " + FileSystemUtils.freeSpaceKb("C:"));
		System.out.println("Free disk space (in MB): " + FileSystemUtils.freeSpaceKb("C:") / 1024);
		
		
		System.out.println("FIN "+CLASS_NAME);
	}

}
