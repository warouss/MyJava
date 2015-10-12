package com.warouss.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://www.person.com/schema")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonService {
	
	@WebMethod
	public boolean addPerson(Person p);
	
	@WebMethod
	public boolean deletePerson(Person p);
	
	@WebMethod
	public Person getPerson(Long id);
	
	@WebMethod
	public Person[] getAllPersons();
	
	

}
