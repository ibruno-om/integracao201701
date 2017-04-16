package br.ufg.inf.soap.message;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestDetalharProcedimentos  extends RequestSIGTAP  {

    static String url = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";

    protected RequestDetalharProcedimentos() throws Exception {
        super(url);
        addContent();
    }

    @Override
    protected void addContent() throws SOAPException {
        envelope.addNamespaceDeclaration("proc1", "http://servicos.saude.gov.br/schema/sigtap/procedimento/v1/procedimento");
        envelope.addNamespaceDeclaration("det", "http://servicos.saude.gov.br/wsdl/mensageria/sigtap/v1/detalheadicional");

        SOAPBody soapBody = envelope.getBody();

        SOAPElement requestDetalharProcedimentos = soapBody.addChildElement("requestDetalharProcedimento", "proc");

        SOAPElement codigoProcedimento = requestDetalharProcedimentos.addChildElement("CodigoProcedimento", "proc1");
        codigoProcedimento.addTextNode("0501010017");

        SOAPElement detalhesAdicionais = requestDetalharProcedimentos.addChildElement("DetalhesAdicionais", "proc");

        SOAPElement detalheAdicional = detalhesAdicionais.addChildElement("DetalheAdicional", "det");

        SOAPElement categoriaDetalheAdicional = detalheAdicional.addChildElement("categoriaDetalheAdicional","det");
        categoriaDetalheAdicional.addTextNode("DESCRICAO");

        SOAPElement paginacao = detalheAdicional.addChildElement("Paginacao", "det");
        SOAPElement registroInicial = paginacao.addChildElement("registroInicial", "pag");
        registroInicial.addTextNode("01");
        SOAPElement quantidadeRegistros = paginacao.addChildElement("quantidadeRegistros", "pag");
        quantidadeRegistros.addTextNode("20");
        SOAPElement totalRegistros = paginacao.addChildElement("totalRegistros", "pag");
        totalRegistros.addTextNode("100");
    }

    @Override
    public SOAPMessage getSOAPMessage() throws Exception {
        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");

        soapMessage.writeTo(System.out);

        return soapMessage;
    }
}
