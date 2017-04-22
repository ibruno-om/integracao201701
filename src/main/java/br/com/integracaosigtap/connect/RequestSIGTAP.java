package br.com.integracaosigtap.connect;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

/**
 * Created by astr1x on 15/04/17.
 */

public abstract class RequestSIGTAP implements SoapMessenger {

    /**
     *  Adicionar URL especifica de cada requisicao
     */
    public String url;

    MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
    SOAPMessage soapMessage = messageFactory.createMessage();
    SOAPPart soapPart = soapMessage.getSOAPPart();
    SOAPEnvelope envelope=  soapPart.getEnvelope();

    protected RequestSIGTAP(String url) throws Exception {
        this.url = url;
        addNameSpace();
        addContent();
        addHeader();
    }

    /** Header generico para toda requisicao como autenticacao, se necessitar colocar alguma informacao
     *  extra ou que seja diferente para algumas requisicoes, poremos colocar parametros nesse metodo
     *  como por exemplo addHeader(String nomeUserNameToken, String password) e fazer que as subclasses
     *  que forem instanciar passem como argumento.
     *
     * @throws SOAPException
     */

    private void addHeader() throws SOAPException {
        SOAPHeader header = soapMessage.getSOAPHeader();
        SOAPHeaderElement security = header.addHeaderElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse"));

        SOAPElement userNameToken = security.addChildElement("UsernameToken", "wsse");
        userNameToken.setAttribute("wsu:Id", "Id-0001334008436683-000000002c4a1908-1");
        userNameToken.addNamespaceDeclaration("wsu","http://docs.oasisopen.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

        SOAPElement userName = userNameToken.addChildElement("Username", "wsse");
        userName.setTextContent("SIGTAP.PUBLICO");

        SOAPElement password = userNameToken.addChildElement("Password", "wsse");
        password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
        password.addTextNode("sigtap#2015public");
    }

    /** Colocar aqui namespaces genericos para toda requisicao. Porem se nao tiver um padrao consistente
     *  transformar esse metodo em abstrato e forcar que cada subclasse implemente, colocando seus
     *  namespaces especificos.
     *
     * @throws SOAPException
     */
    protected abstract void addNameSpace() throws SOAPException;

    /**
     *  Adicionar informacoes adicionais para a requisicao, como body, parametros etc.
     */
    protected abstract void addContent() throws SOAPException;
}
