package com.warouss.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseXMLUsingSax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ParseXMLUsingSax().execute();
	}
	
	private void execute() {
		try{
			// création d'une fabrique de parseurs SAX
			SAXParserFactory fabrique = SAXParserFactory.newInstance();

			// création d'un parseur SAX
			SAXParser parseur = fabrique.newSAXParser();

			// lecture d'un fichier XML avec un DefaultHandler
			File fichier = new File("src/xml/Customer.xml");
			CustomerHandler gestionnaire = new CustomerHandler();
			parseur.parse(fichier, gestionnaire);

		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur");
			System.out.println("Lors de l'appel à newSAXParser()");
		}catch(SAXException se){
			System.out.println("Erreur de parsing");
			System.out.println("Lors de l'appel à parse()");
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("Lors de l'appel à parse()");
		}
	}
	
	
	class CustomerHandler extends DefaultHandler {
		private int index=0;
		
		public CustomerHandler() {
			super();
		}
		
		//détection d'ouverture de balise
		public void startElement(String uri, String localName,
				String qName, Attributes attributes) throws SAXException {
			
			System.out.print(getPrefix(true)+"startElement "+uri+" : "+localName+" : "+qName);
			if (attributes!=null && attributes.getLength()>0) {
				for (int i=0; i<attributes.getLength(); i++) {
					System.out.print("\t"+attributes.getQName(i)+"="+attributes.getValue(i));
				}
			}
			System.out.println("");
			
		}
		
		//détection fin de balise
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			
			System.out.println(getPrefix(false)+"endElement "+uri+" : "+localName+" : "+qName);
			
		}
		
		//détection de caractères
		public void characters(char[] ch,int start, int length)
				throws SAXException{
			String lecture = new String(ch,start,length);
			if (lecture != null && !lecture.trim().isEmpty())
				System.out.println("lecture : "+lecture.trim());
		}
		
		//début du parsing
		public void startDocument() throws SAXException {
			System.out.println("Début du parsing");
		}
		
		//fin du parsing
		public void endDocument() throws SAXException {
			System.out.println("Fin du parsing");
		}
		
		private String getPrefix(boolean isStart) {
			String prefix="";
			index=isStart?index+1:index-1;
			for (int i=0;i<index;i++) {
				prefix=prefix+"   ";
			}
			return prefix;
		}
	}

}
