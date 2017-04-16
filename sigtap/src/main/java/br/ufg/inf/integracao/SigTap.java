package br.ufg.inf.integracao;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import br.ufg.inf.integracao.util.Printer;

public class SigTap {
	private SOAPConnectionFactory soapConnectionFactory;
	private SOAPConnection soapConnection;
	private SOAPMessage soapMessage;
	
	public SigTap(){}

	public void getListaProcedimentos() throws Exception{
		setConnection();
		setHeader();

		SOAPBody soapBody = this.soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement requestPesquisarProcedimentos = soapBody.addChildElement("requestPesquisarProcedimentos", "proc");

		SOAPElement codigoGrupo = requestPesquisarProcedimentos.addChildElement("codigoGrupo", "grup");
		codigoGrupo.addTextNode("05");

		SOAPElement pag = requestPesquisarProcedimentos.addChildElement("Paginacao", "pag");
		SOAPElement registroInicial = pag.addChildElement("registroInicial", "pag");
		registroInicial.addTextNode("01");
		SOAPElement quantidadeRegistros = pag.addChildElement("quantidadeRegistros", "pag");
		quantidadeRegistros.addTextNode("20");

		this.soapMessage.saveChanges();
		
		execute();
	}
	
	private void setConnection() throws UnsupportedOperationException, SOAPException{
		this.soapConnectionFactory = SOAPConnectionFactory.newInstance();
		this.soapConnection = soapConnectionFactory.createConnection();
		this.soapMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
		
		SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();
		
		envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/schema/corporativo/v1/competencia");
		envelope.addNamespaceDeclaration("grup", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/grupo");
		envelope.addNamespaceDeclaration("pag", "http://servicos.saude.gov.br/wsdl/mensageria/v1/paginacao");
		envelope.addNamespaceDeclaration("proc", "http://servicos.saude.gov.br/sigtap/v1/procedimentoservice");
		envelope.addNamespaceDeclaration("sub","http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/subgrupo");
	};
	
	private void setHeader() throws SOAPException{
		SOAPHeader header = this.soapMessage.getSOAPHeader();
		SOAPHeaderElement security = header.addHeaderElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security", "wsse"));

		SOAPElement userNameToken = security.addChildElement("UsernameToken", "wsse");
		userNameToken.setAttribute("wsu:Id", "Id-0001334008436683-000000002c4a1908-1");
		userNameToken.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

		SOAPElement userName = userNameToken.addChildElement("Username", "wsse");
		userName.setTextContent("SIGTAP.PUBLICO");

		SOAPElement password = userNameToken.addChildElement("Password", "wsse");
		password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		password.addTextNode("sigtap#2015public");
	}
	
	private void execute() throws Exception{
		Printer.printSOAPRequest(this.soapMessage);
		SOAPMessage soapResponse = this.soapConnection.call(this.soapMessage, "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1");
		Printer.printSOAPResponse(soapResponse);

		this.soapConnection.close();
	};

}
