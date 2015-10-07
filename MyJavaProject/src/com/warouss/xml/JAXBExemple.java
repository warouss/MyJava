package com.warouss.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBExemple {

	public static void main(String[] args) {
		
		new JAXBExemple().executeEcriture();
		
		new JAXBExemple().executeLecture();
		

	}
	
	private void executeEcriture() {
		System.out.println("Debut executeEcriture -----------------------------");
		Customer customer = new Customer();
		customer.setId(4);
		customer.setName("Brad Host");
		customer.setComplain("New Customer From JAXB");
		
		try {
			File file = new File("src/xml/CustomerJAXB.xml");
			
			JAXBContext cxt = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = cxt.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println("Fin executeEcriture -----------------------------");
	}
	
	private void executeLecture() {
		System.out.println("Debut executeLecture -----------------------------");
		try {
			 
			File file = new File("src/xml/CustomerJAXB.xml");
			JAXBContext cxt = JAXBContext.newInstance(Customer.class);
	 
			Unmarshaller jaxbUnmarshaller = cxt.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			System.out.println(customer);
	 
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		System.out.println("Fin executeLecture -----------------------------");
	}

}
