package com.warouss.apache.commons.io;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;

public final class ComparatorExample {
	private static final String CLASS_NAME = ComparatorExample.class.getName();
	
	private static final String PARENT_DIR = "C:\\Users\\warouss.NETIK\\Programmes\\DEV\\workspace\\ApacheCommonsProject\\ressources\\io";
	
	public static void execute() {
		System.out.println("DEBUT "+CLASS_NAME);
		
		File parentDir = FileUtils.getFile(PARENT_DIR);
		NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
		File[] sortedFiles = comparator.sort(parentDir.listFiles());
		
		System.out.println("Sorted by name files in parent directory: ");
		for (File file: sortedFiles) {
			System.out.println("\t"+ file.getAbsolutePath());
		}
		
		
		SizeFileComparator sizeComparator = new SizeFileComparator(true);
		File[] sizeFiles = sizeComparator.sort(parentDir.listFiles());
		System.out.println("Sorted by size files in parent directory: ");
		for (File file: sizeFiles) {
			System.out.println("\t"+ file.getName() + " with size (kb): " + file.length());
		}
		
		LastModifiedFileComparator lastModified = new LastModifiedFileComparator();
		File[] lastModifiedFiles = lastModified.sort(parentDir.listFiles());
		System.out.println("Sorted by last modified files in parent directory: ");
		for (File file: lastModifiedFiles) {
			Date modified = new Date(file.lastModified());
			System.out.println("\t"+ file.getName() + " last modified on: " + modified);
		}

		
		System.out.println("FIN "+CLASS_NAME);
	}

}
