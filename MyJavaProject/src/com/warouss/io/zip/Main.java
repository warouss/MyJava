package com.warouss.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

	public static void main(String[] args) {
		new Main().execute();

	}
	
	private void execute() {
		String zipName = "src/com/warouss/io/zip/ZipWriter.zip";
		
		writeZip(zipName);
		readZip(zipName);
		
//		deleteZip(zipName);
		
	}
	
	private void writeZip(String zipName) {
		System.out.println(String.format("Debut writeZip(%s) ============================================", zipName));
		FileOutputStream zipFile = null;
		try {
			zipFile = new FileOutputStream(zipName);
			ZipOutputStream zip = new ZipOutputStream(zipFile);
			System.out.println("Ajout 1er fichier ");
			//Ajout 1er fichier dans le zip
			ZipEntry e1 = new ZipEntry("file1.txt");
			zip.putNextEntry(e1);
			zip.write("Hello zip (1) !!!!!!!!!!!!!!!".getBytes());
			zip.closeEntry();
			System.out.println("Ajout 2ème fichier ");
			//Ajout 2ème fichier
			ZipEntry e2 = new ZipEntry("file2.txt");
			zip.putNextEntry(e2);
			zip.write("Hello zip (2) !!!!!!!!!!!!!!!".getBytes());
			zip.closeEntry();
			System.out.println("Ajout repertoire ");
			//Ajout repertoire
			ZipEntry e3 = new ZipEntry("rep1/file1.txt");
			zip.putNextEntry(e3);
			zip.write("Hello rep zip (1) !!!!!!!!!!!!!!!".getBytes());
			zip.closeEntry();
			zip.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (zipFile!=null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					zipFile = null;
				}
			}
			
			System.out.println(String.format("Fin writeZip(%s) ============================================", zipName));
		}
	}
	
	private void readZip(String zipName) {
		System.out.println(String.format("Debut readZip(%s) ============================================", zipName));
		
		byte[] buffer = new byte[1024];
		
		FileInputStream zipFile = null;
		try {
			zipFile = new FileInputStream(zipName);
			ZipInputStream zip = new ZipInputStream(zipFile);
			ZipEntry e = zip.getNextEntry();
			while (e!=null) {
				System.out.println(String.format("Read File %s, isDirectory=%s, size=%s, compressedSize=%s", e.getName(), e.isDirectory(), e.getSize(), e.getCompressedSize()));
				if (!e.isDirectory()) {
					StringBuffer sb = new StringBuffer();
					while (zip.read(buffer)>0) {
						sb.append(new String(buffer, Charset.defaultCharset()));
					}
					System.out.println("\t"+sb);
				} 
				zip.closeEntry();
				e = zip.getNextEntry();
			}
			zip.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (zipFile!=null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					zipFile = null;
				}
			}
			System.out.println(String.format("Fin readZip(%s) ============================================", zipName));
		}
	}
	
	private void deleteZip(String zipName) {
		System.out.println(String.format("Debut deleteZip(%s) ============================================", zipName));
		File file = new File(zipName);
		System.out.println("Delete SUCCESS="+file.delete());
		System.out.println(String.format("Fin deleteZip(%s) ============================================", zipName));
	}
	
}
