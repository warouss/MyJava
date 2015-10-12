package com.warouss.webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.warouss.webservice.client.Client;
import com.warouss.webservice.client.ClientGen;
import com.warouss.webservice.server.Server;

public class WSMain {
	private static final String URL_WS = "http://localhost:8888/person";
	

	public static void main(String[] args) {
//		new WSMain().test();
		
//		new WSMain().generateClient();
		
		new WSMain().testGen();
		
	}
	
	private void test() {
		Server server = new Server(URL_WS);
		
		server.start();
		
		Client client = new Client(URL_WS);
		
		client.start();
		
		client.test();
		
		server.stop();
	}
	
	private void testGen() {
		Server server = new Server(URL_WS);
		
		server.start();
		
		ClientGen client = new ClientGen();
		
		client.start();
		
		client.test();
		
		server.stop();
	}
	
	/**
	 * lance la commande wsimport 
	 */
	private void generateClient() {
		Server server = new Server(URL_WS);
		
		server.start();
		
		String javaHome = System.getProperty("java.home");
		String projectDir = System.getProperty("user.dir");
		
		File toDelete = new File(projectDir+"\\src\\com\\warouss\\webservice\\client\\gen");
		System.out.println("toDelete exists = "+toDelete.exists());
		if (toDelete.exists()) {
			for (File f : toDelete.listFiles()) {
				f.delete();
			}
			boolean deleted = toDelete.delete();
			System.out.println("toDelete deleted="+deleted+", exists="+toDelete.exists());
		}
		
		
		String command = "wsimport "+URL_WS+"?wsdl -d "+projectDir+"\\src\\ -p com.warouss.webservice.client.gen -keep";
		System.out.println("command = "+command);
		
		Runtime r = Runtime.getRuntime();
		try {
			File jre = new File(javaHome);
			System.out.println("jre="+jre.getPath());
			File jdk = jre.getParentFile();
			System.out.println("jdk="+jdk.getPath());
			File bin = new File(jdk.getPath()+"\\bin");
			System.out.println("bin="+bin.getPath());
			File wsimport = new File(bin.getPath()+"\\wsimport.exe");
			System.out.println("wsimport exists = "+wsimport.exists());
			
			Process p = r.exec(command, null, bin);
			
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (reader.ready()) {
				System.out.println(reader.readLine());
			}
			
			server.stop();
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
