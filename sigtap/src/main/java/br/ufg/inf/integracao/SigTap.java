package br.ufg.inf.integracao;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

@Deprecated
public class SigTap {

	public static void main(String[] args) throws Exception {
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String url = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

        // Process the SOAP Response
//        printSOAPResponse(soapResponse);

        soapConnection.close();

	}
	
	 private static SOAPMessage createSOAPRequest() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/schema/corporativo/v1/competencia");
	        envelope.addNamespaceDeclaration("grup", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/grupo");
	        envelope.addNamespaceDeclaration("pag", "http://servicos.saude.gov.br/wsdl/mensageria/v1/paginacao");
	        envelope.addNamespaceDeclaration("proc", "http://servicos.saude.gov.br/sigtap/v1/procedimentoservice");
	        envelope.addNamespaceDeclaration("sub", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/subgrupo");
	        
	        //SOAP HEADER
	        SOAPHeader header = soapMessage.getSOAPHeader();
	        SOAPHeaderElement  security = header.addHeaderElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse"));
	        
	        SOAPElement userNameToken = security.addChildElement("UsernameToken", "wsse");
	        userNameToken.setAttribute("wsu:Id", "Id-0001334008436683-000000002c4a1908-1");
	        userNameToken.addNamespaceDeclaration("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

	        SOAPElement userName = userNameToken.addChildElement("Username", "wsse");
	        userName.setTextContent("SIGTAP.PUBLICO");
	        
	        SOAPElement password = userNameToken.addChildElement("Password", "wsse");
	        password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
	        password.addTextNode("sigtap#2015public");
	        
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement requestPesquisarProcedimentos = soapBody.addChildElement("requestPesquisarProcedimentos", "proc");
	        
	        SOAPElement codigoGrupo = requestPesquisarProcedimentos.addChildElement("codigoGrupo", "grup");
	        codigoGrupo.addTextNode("05");
	        
	        SOAPElement paginacao = requestPesquisarProcedimentos.addChildElement("Paginacao", "pag");
	        SOAPElement registroInicial = paginacao.addChildElement("registroInicial", "pag");
	        registroInicial.addTextNode("01");
	        SOAPElement quantidadeRegistros = paginacao.addChildElement("quantidadeRegistros", "pag");
	        quantidadeRegistros.addTextNode("20");
	        

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message = ");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }
	 
	  /**
	     * Method used to print the SOAP Response
	     */
	    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        Source sourceContent = soapResponse.getSOAPPart().getContent();
	        System.out.print("\nResponse SOAP Message =  ");
	        StreamResult result = new StreamResult(System.out);
	        transformer.transform(sourceContent, result);
	    }
}
