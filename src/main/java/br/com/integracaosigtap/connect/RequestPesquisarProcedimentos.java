package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 15/04/17.
 */
public class RequestPesquisarProcedimentos extends RequestSIGTAP{

    static String url = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";

    public RequestPesquisarProcedimentos() throws Exception {
        super(url);
    }

    protected void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/schema/corporativo/v1/competencia");
        envelope.addNamespaceDeclaration("pag", "http://servicos.saude.gov.br/wsdl/mensageria/v1/paginacao");
        envelope.addNamespaceDeclaration("proc", "http://servicos.saude.gov.br/sigtap/v1/procedimentoservice");
        envelope.addNamespaceDeclaration("grup", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/grupo");
        envelope.addNamespaceDeclaration("sub", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/subgrupo");
    }

    @Override
    protected void addContent() throws SOAPException {

        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestPesquisarProcedimentos = soapBody.addChildElement("requestPesquisarProcedimentos", "proc");

        SOAPElement codigoGrupo = requestPesquisarProcedimentos.addChildElement("codigoGrupo", "grup");
        codigoGrupo.addTextNode("05");

        SOAPElement paginacao = requestPesquisarProcedimentos.addChildElement("Paginacao", "pag");
        SOAPElement registroInicial = paginacao.addChildElement("registroInicial", "pag");
        registroInicial.addTextNode("01");
        SOAPElement quantidadeRegistros = paginacao.addChildElement("quantidadeRegistros", "pag");
        quantidadeRegistros.addTextNode("20");
    }

    public SOAPMessage getSOAPMessage() throws Exception {
        soapMessage.saveChanges();

        /**
         *  Debugar o que esta sendo entrega para o WebService, e comparar com o que esta esperado
         *  na especificacao do SIGTAP
         */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
}
