package com.warouss.apache.commons.io;

import java.io.File;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.io.monitor.FileEntry;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

public final class FileMonitorExemple {
	private static final String CLASS_NAME = FileMonitorExemple.class.getName();
	
	private static final String PARENT_DIR ="C:\\Users\\warouss.NETIK\\Programmes\\DEV\\workspace\\ApacheCommonsProject\\ressources\\io";
	private static final String EXAMPLE_PATH = PARENT_DIR+"\\file.txt";
	private static final String NEW_DIR =PARENT_DIR+"\\newDir";
	private static final String NEW_FILE =PARENT_DIR+"\\newFile.txt";
	
	private static StopWatch chrono;
	
	public static void execute() {
		System.out.println("DEBUT "+CLASS_NAME);
		
		// FileEntry
		System.out.println("## FileEntry ==============================================");
		
		FileEntry entry = new FileEntry(FileUtils.getFile(EXAMPLE_PATH));
		
		System.out.println("File monitored: " + entry.getFile());
		System.out.println("File name: " + entry.getName());
		System.out.println("Is the file a directory?: " + entry.isDirectory());
		
		// File Monitoring
		System.out.println("## FileAlterationObserver ==============================================");
		File parentDir = FileUtils.getFile(PARENT_DIR);
		
		FileAlterationObserver observer = new FileAlterationObserver(parentDir);
		observer.addListener(new FileAlterationListenerAdaptor() {
			@Override
			public void onFileCreate(File file) {
				chrono.split();
				System.out.println("File created: " + file.getName() + " : " + DurationFormatUtils.formatDurationHMS(chrono.getSplitTime()));
			}
			
			@Override
			public void onFileDelete(File file) {
				chrono.split();
				System.out.println("File deleted: " + file.getName() + " : " + DurationFormatUtils.formatDurationHMS(chrono.getSplitTime()));
			}
			
			@Override
			public void onDirectoryCreate(File dir) {
				chrono.split();
				System.out.println("Directory created: " + dir.getName() + " : " + DurationFormatUtils.formatDurationHMS(chrono.getSplitTime()));
			}
			
			@Override
			public void onDirectoryDelete(File dir) {
				chrono.split();
				System.out.println("Directory deleted: " + dir.getName() + " : " + DurationFormatUtils.formatDurationHMS(chrono.getSplitTime()));
			}
		});
		
		
		FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
		try {
			chrono = new StopWatch();
			chrono.start();
			monitor.start();
			
			File newDir = new File(NEW_DIR);
			File newFile = new File(NEW_FILE);
			
			newDir.mkdirs();
			newFile.createNewFile();
			
			Thread.sleep(5000);

			FileDeleteStrategy.NORMAL.delete(newDir);
			FileDeleteStrategy.NORMAL.delete(newFile);

			Thread.sleep(1000);

			monitor.stop();
			chrono.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}


		

		
		System.out.println("FIN "+CLASS_NAME);
	}



}
