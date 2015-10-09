package com.warouss.apache.commons.io;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public final class FiltersExample {
	private static final String CLASS_NAME = FiltersExample.class.getName();
	
	private static final String PARENT_DIR ="C:\\Users\\warouss.NETIK\\Programmes\\DEV\\workspace\\ApacheCommonsProject\\ressources\\io";
	
	public static void execute() {
		System.out.println("DEBUT "+CLASS_NAME);
		
		File dir = FileUtils.getFile(PARENT_DIR);
		String[] acceptedNames = {"exemple", "exempleTxt.txt"};
		
		System.out.println("File found, named: ");
		for (String file: dir.list(new NameFileFilter(acceptedNames, IOCase.INSENSITIVE))) {
			System.out.println("\t-"+file);
		}
		
		System.out.println("Wildcard file found, named: ");
		for (String file: dir.list(new WildcardFileFilter("*emple*"))) {
			System.out.println("\t-"+file);
		}
		
		System.out.println("Prefix file found, named: ");
		for (String file: dir.list(new PrefixFileFilter("exemple"))) {
			System.out.println("\t-"+file);
		}
		
		System.out.println("Suffix file found, named: ");
		for (String file: dir.list(new SuffixFileFilter(".txt"))) {
			System.out.println("\t-"+file);
		}
		
		System.out.println("And/Not file found, named: ");
		for (String file : dir.list(new AndFileFilter(
				new WildcardFileFilter("*emple*"), 
				new NotFileFilter(new SuffixFileFilter(".txt"))))) {
			System.out.println("\t-"+file);
		}

		
		System.out.println("FIN "+CLASS_NAME);
	}

}
