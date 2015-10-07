package com.warouss.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().execute();
	}
	
	private void execute() {
		//Test createFixedFileSize
//		createFixedFileSize("src/com/warouss/io/1Gbp.tmp", 1000*1000*1000/8);
		
		String fileName = "src/com/warouss/io/TestWriteRead.txt";
		//Test Write Read
//		writerFile(fileName);
//		readFile(fileName);
//		scanFile(fileName);
//		deleteFile(fileName);
		
		testSpace();
	}
	
	/**
	 * Créer un ficher avec une taille donnée en bit
	 * @param fileName
	 * @param size
	 */
	private void createFixedFileSize(String fileName, long size) {
		String methodName = "createFixedFileSize";
		LOG.info("ENTRY "+methodName+" params : "+fileName+", "+size );
		try {
            RandomAccessFile f = new RandomAccessFile(fileName, "rw");
            f.setLength(size);
       } catch (Exception e) {
            System.err.println(e);
       }
		LOG.info("RETURN "+methodName);
	}
	
	private void writerFile(String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
			System.out.println("Debut Ecriture du fichier "+fileName+" --------------------------------------");
			writer.write("Hello World !!!");
			writer.newLine();
			writer.write("Line (2) -------------");
			writer.newLine();
			writer.write("Fin Fichier :(");
			System.out.println("Fin Ecriture du fichier "+fileName+" --------------------------------------");
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					writer = null;
				}
			}
		}
	}
	
	private void readFile(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			System.out.println("Debut Lecture du fichier "+fileName+" --------------------------------------");
			while (reader.ready()) {
				System.out.println(reader.readLine());
			}
			System.out.println("Fin Lecture du fichier "+fileName+" --------------------------------------");
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					reader = null;
				}
			}
		}
	}
	
	private void scanFile(String fileName) {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
			System.out.println("Debut Scan fichier "+fileName+" --------------------------------------");
			int lineNum = 1;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				System.out.println((lineNum++)+" : "+line);
			}
			System.out.println("Fin Scan fichier "+fileName+" --------------------------------------");
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} finally {
			if (s!=null)
				s.close();
		}
	}
	
	private void deleteFile(String fileName) {
		File file = new File(fileName);
		System.out.println("Delete SUCCESS="+file.delete());
	}
	
	private void testSpace() {
		File file = new File("C:");
		//capacité de la partition
		long totalSpace = file.getTotalSpace();
		//espace libre
		long freeSpace = file.getFreeSpace();
		
		System.out.println("-------- Partition C: ---------");
        long Go = totalSpace / (1024 * 1024 * 1024);
        System.out.println("Capacite : " + totalSpace + " octets soit " + Go + " Go");
        Go = freeSpace / (1024 * 1024 * 1024);
        System.out.println("Espace libre : " + freeSpace + " octets soit " + Go + " Go");
	}

}
