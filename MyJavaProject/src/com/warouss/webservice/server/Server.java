package com.warouss.webservice.server;

import javax.xml.ws.Endpoint;

public class Server {
	private String url = null;
	private Endpoint endpoint;
	
	public Server(String p_url) {
		url = p_url;
	}
	
	public void start() {
		//Rendre disponible l'instance
		endpoint = Endpoint.publish(url, new PersonServiceImpl());
		
		//Tester la disponibilité du endpoint
		boolean status = endpoint.isPublished();
		System.out.println("Server : publication du webservice");
		
		System.out.println("Server : webservice disponible ? "+status);
	}
	
	public void stop() {
		endpoint.stop();
		System.out.println("Server : arrêt du webservice");
		
		boolean status = endpoint.isPublished();
		System.out.println("Server : webservice disponible ? "+status);
	}
	
	public static void main(String[] args) {
		new Server("http://localhost:8888/person").start();
	}

}
