package br.com.integracaosigtap.util;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SOAPMessageWriter {

	private SOAPMessage soapMessage;

	public void printSOAPRequest() throws SOAPException, IOException{
        System.out.println("== Request SOAP Message ==");
		soapMessage.writeTo(System.out);
        System.out.println();
	}
	
	public String getSOAPResponse() throws Exception {
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		final Transformer transformer = transformerFactory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		final Source soapContent = soapMessage.getSOAPPart().getContent();

		final ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
		final StreamResult result = new StreamResult(streamOut);
		transformer.transform(soapContent, result);

		return streamOut.toString();
	}

	public void setSoapMessage(SOAPMessage soapMessage) {
		this.soapMessage = soapMessage;
	}
}
