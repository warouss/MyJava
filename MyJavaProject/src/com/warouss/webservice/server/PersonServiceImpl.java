package com.warouss.webservice.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService(endpointInterface="com.warouss.webservice.server.PersonService", serviceName="PersonService", targetNamespace="http://www.person.com/")

public class PersonServiceImpl implements PersonService {
	private Map<Long, Person> map = new HashMap<Long, Person>();

	@Override
	public boolean addPerson(Person p) {
		boolean bool = false;
		if (!map.containsKey(p.getId())) {
			map.put(p.getId(), p);
			bool = true;
			System.out.println("\tadd "+p);
		}
		return bool;
	}

	@Override
	public boolean deletePerson(Person p) {
		boolean bool = false;
		if (map.containsKey(p.getId())) {
			map.remove(p.getId());
			bool = true;
		}
		
		System.out.println("\tdelete "+p);
		return bool;
	}

	@Override
	public Person getPerson(Long id) {
		Person p = null;
		if (map.containsKey(id))
			p = map.get(id);
		
		System.out.println("\tget "+p);
		return p;
	}

	@Override
	public Person[] getAllPersons() {
		Collection<Person> all = map.values();
		System.out.println("\tall "+all);
		return all.toArray(new Person[all.size()]);
	}

}
