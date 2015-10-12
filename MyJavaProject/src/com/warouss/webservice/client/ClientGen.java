package com.warouss.webservice.client;

import java.util.List;

import com.warouss.webservice.client.gen.Person;
import com.warouss.webservice.client.gen.PersonService;
import com.warouss.webservice.client.gen.PersonService_Service;



public class ClientGen {
	private PersonService personService;
	
	public void start() {
		PersonService_Service service = new PersonService_Service();
		personService = service.getPersonServiceImplPort();
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
		List<Person> all = personService.getAllPersons().getItem();
		System.out.println("AllPersons = "+all.size()+" : "+all);
		
	}

	public static void main(String[] args) {
		ClientGen client = new ClientGen();
		
		client.start();
		
		client.test();

	}

}
