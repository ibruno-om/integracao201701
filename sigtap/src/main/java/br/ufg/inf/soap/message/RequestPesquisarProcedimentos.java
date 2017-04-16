package br.ufg.inf.soap.message;

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
        addContent();
    }

    @Override
    protected void addContent() throws SOAPException {
        envelope.addNamespaceDeclaration("grup", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/grupo");
        envelope.addNamespaceDeclaration("sub", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/subgrupo");

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

    @Override
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
