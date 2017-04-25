package br.com.integracaosigtap.connect;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;

/**
 * Created by astr1x on 15/04/17.
 */

public class ConnectionSUS implements SoapMessenger, Connection{

    public static String URL_PROCEDIMENTOS = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";

    public static String URL_COMPATIBILIDADE_POSSIVEIS = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";

    public static String URL_COMPATIBILIDADE = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadeService/v1";


    protected MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
    protected SOAPMessage soapMessage = messageFactory.createMessage();
    protected SOAPPart soapPart = soapMessage.getSOAPPart();
    protected SOAPEnvelope envelope=  soapPart.getEnvelope();

    public ConnectionSUS() throws Exception {
        addNameSpace();
        addContent();
        addHeader();
    }

    public String getPesquisarProcedimentos(String url) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = null;

        try{
            ConnectionSUS pesquisarProcedimentos = new RequestPesquisarProcedimentos();
            soapResponse = soapConnection.call(pesquisarProcedimentos.getSOAPMessage(), url);
        }catch (Exception e){
            System.err.println("Erro ao receber resposta do servidor ");
            e.printStackTrace();
        }finally {
            soapConnection.close();
        }

        return printSoapMessage(soapResponse);
    }

    public String getDetalharProcedimentos(String url) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = null;

        try{
            ConnectionSUS pesquisarProcedimentos = new RequestDetalharProcedimentos();
            soapResponse = soapConnection.call(pesquisarProcedimentos.getSOAPMessage(), url);
        }catch (Exception e){
            System.err.println("Erro ao receber resposta do servidor ");
            e.printStackTrace();
        }finally {
            soapConnection.close();
        }

        return printSoapMessage(soapResponse);
    }

    public String getListarCompatibilidadesPossiveis(String url) throws Exception{
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = null;

        try{
            ConnectionSUS pesquisarProcedimentos = new RequestListarCompatibilidadesPossiveis();
            soapResponse = soapConnection.call(pesquisarProcedimentos.getSOAPMessage(), url);
        }catch (Exception e){
            System.err.println("Erro ao receber resposta do servidor ");
            e.printStackTrace();
        }finally {
            soapConnection.close();
        }

        return printSoapMessage(soapResponse);
    }

    public String getPesquisarCompatibilidades(String url) throws Exception{
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = null;

        try{
            ConnectionSUS pesquisarProcedimentos = new RequestPesquisarCompatibilidades();
            soapResponse = soapConnection.call(pesquisarProcedimentos.getSOAPMessage(), url);
        }catch (Exception e){
            System.err.println("Erro ao receber resposta do servidor ");
            e.printStackTrace();
        }finally {
            soapConnection.close();
        }

        return printSoapMessage(soapResponse);
    }

    public String getListarFormaOrganizacao(String url){
        return null;
    }

    public String getListarGrupos(String url){

        return null;
    }

    public String getListarSubGrupos(String url){

        return null;
    }

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

    public SOAPMessage getSOAPMessage() throws Exception {
        soapMessage.saveChanges();

        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    public void addNameSpace() throws SOAPException {}

    public void addContent() throws SOAPException {}

    public static String printSoapMessage(final SOAPMessage soapMessage) throws Exception{
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
}
