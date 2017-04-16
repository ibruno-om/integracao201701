package br.ufg.inf.integracao.util;

import java.io.IOException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class Printer {
	
	public static void printSOAPRequest(SOAPMessage soapMessage) throws SOAPException, IOException{
        System.out.println("== Request SOAP Message ==");
        soapMessage.writeTo(System.out);
        System.out.println();
	}
	
	public static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.println("== Response SOAP Message ==");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
		System.out.println();
	}

}
