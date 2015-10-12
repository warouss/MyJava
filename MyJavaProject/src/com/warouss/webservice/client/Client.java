package com.warouss.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.warouss.webservice.server.Person;
import com.warouss.webservice.server.PersonService;

public class Client {
	private String url = null;
	private PersonService personService;
	
	public Client(String p_url) {
		url = p_url;
	}
	
	public void start() {
		try {
			QName qname = new QName("http://www.person.com/", "PersonService");
			
			//Cr�ation d'une fabrique pour le WS
			Service service = Service.create(new URL(url+"?wsdl"), qname);
			System.out.println("Client : creation service "+service.getServiceName());
			
			//R�cup�ration Proxy pour acc�der aux m�thodes
			personService = service.getPort(PersonService.class);
			System.out.println("client : r�cup�ration du proxy : "+personService);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void test() {
		System.out.println("Test create()");
		
		for (int i=0; i<5; i++) {
			Person p = new Person();
			p.setId(Long.valueOf(i));
			p.setName("Person_"+i);
			p.setAge(30+i);
			p.setMale(i%2==0);
			personService.addPerson(p);
		}
		
		System.out.println("Test getPerson()");
		Person p = personService.getPerson(3L);
		System.out.println(p);
		System.out.println("Test deletePerson()");
		personService.deletePerson(p);
		System.out.println("Test getAllPersons()");
		Person[] all = personService.getAllPersons();
		System.out.println("AllPersons = "+all.length+" : "+Arrays.toString(all));
		
	}
	
	public static void main(String[] args) {
		Client client = new Client("http://localhost:8888/person");
		
		client.start();
		
		client.test();
		
		
	}


}
