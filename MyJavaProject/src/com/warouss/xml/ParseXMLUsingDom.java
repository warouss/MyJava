package com.warouss.xml;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParseXMLUsingDom {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	    InputSource inputStream = new InputSource();
	  
	    //For parsing XML document using FileReader
	    inputStream.setCharacterStream(new FileReader("src/xml/Customer.xml"));
	    
	    //If you have an xml string then you can pass the string into StringReader
	    //inputStream.setCharacterStream(new StringReader(""));
	    
	    Document document = documentBuilder.parse(inputStream);
	    NodeList studentNodeList = document.getElementsByTagName("customer");
	    for (int index = 0; index < studentNodeList.getLength(); index++) {
	      Node node = studentNodeList.item(index);
	      if (node.getNodeType() == Node.ELEMENT_NODE) {
	        Element element = (Element) node;
	        NodeList nameNodeList = element.getElementsByTagName("name");
	        for (int eIndex = 0; eIndex < nameNodeList.getLength(); eIndex++) {
	          if (nameNodeList.item(eIndex).getNodeType() == Node.ELEMENT_NODE) {
	            Element nameElement = (Element) nameNodeList.item(eIndex);
	            System.out.println("Name = "+ nameElement.getFirstChild().getNodeValue().trim());
	          }
	        }
	        NodeList complainNodeList = element.getElementsByTagName("complain");
	        for (int eIndex = 0; eIndex < complainNodeList.getLength(); eIndex++) {
	          if (complainNodeList.item(eIndex).getNodeType() == Node.ELEMENT_NODE) {
	            Element complainElement = (Element) complainNodeList.item(eIndex);
	            System.out.println("Complain = "+ complainElement.getFirstChild().getNodeValue().trim());
	          }
	        }
	      }
	    }
	}
}
