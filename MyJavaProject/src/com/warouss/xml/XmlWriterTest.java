package com.warouss.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlWriterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new XmlWriterTest().execute();
	}
	
	private void execute() {
		XMLStreamWriter writer = createXmlDocument("src/xml/Customer.xml");
		writeToDocument(writer);
	}
	
	private Customer[] createCustomers() {
		Customer[] customers = new Customer[3];
		Customer customer1 = new Customer(1, "John Smith", "Internet Connection problem");
		Customer customer2 = new Customer(2, "Will Foster", "DTH Service problem");
		Customer customer3 = new Customer(3, "Jonty Rhodes", "Set-Top box not working");

		customers[0] = customer1;
		customers[1] = customer2;
		customers[2] = customer3;

		return customers;
	}
	
	private XMLStreamWriter createXmlDocument(String fileInfo) {
		XMLOutputFactory factory = XMLOutputFactory.newFactory();

		FileOutputStream fos;
		XMLStreamWriter writer = null;

		try {
			fos = new FileOutputStream(fileInfo);
			writer = factory.createXMLStreamWriter(fos, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
	private void writeToDocument(XMLStreamWriter writer) {
		try {
			writer.writeStartDocument();
			writer.writeCharacters("\n");
			writer.writeStartElement("customers");
			writer.writeCharacters("\n");
			for (Customer customer : createCustomers()) {
				writer.writeCharacters("\t");
				writer.writeStartElement("customer");
				writer.writeAttribute("id",
						String.valueOf(customer.getCustomerId()));
				writer.writeCharacters("\n\t\t");
				writer.writeStartElement("name");
				writer.writeCharacters(customer.getCustomerName());
				writer.writeEndElement();
				writer.writeCharacters("\n\t\t");
				writer.writeStartElement("complain");
				writer.writeCharacters(customer.getComplain());
				writer.writeEndElement();
				writer.writeCharacters("\n\t");
				writer.writeEndElement();
				writer.writeCharacters("\n");
			}
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	
	class Customer {
		private int customerId;
		private String customerName;
		private String complain;

		public Customer(int customerId, String customerName, String complain) {
			this.customerId = customerId;
			this.customerName = customerName;
			this.complain = complain;
		}
		 
		public int getCustomerId() {return customerId;}
		public void setCustomerId(int customerId) {this.customerId = customerId;}
		 
		public String getCustomerName() {return customerName;}
		public void setCustomerName(String customerName) {this.customerName = customerName;}
		 
		public String getComplain() {return complain;}
		public void setComplain(String complain) {this.complain = complain;}
	}

}
