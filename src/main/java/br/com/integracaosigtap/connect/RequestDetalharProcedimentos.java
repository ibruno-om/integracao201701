package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestDetalharProcedimentos  extends ConnectionSUS {


    public RequestDetalharProcedimentos() throws Exception {
    }

    public void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("proc", "http://servicos.saude.gov.br/sigtap/v1/procedimentoservice");
        envelope.addNamespaceDeclaration("proc1", "http://servicos.saude.gov.br/schema/sigtap/procedimento/v1/procedimento");
        envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/schema/corporativo/v1/competencia");
        envelope.addNamespaceDeclaration("det", "http://servicos.saude.gov.br/wsdl/mensageria/sigtap/v1/detalheadicional");
        envelope.addNamespaceDeclaration("pag", "http://servicos.saude.gov.br/wsdl/mensageria/v1/paginacao");
    }

    @Override
    public void addContent() throws SOAPException {
        SOAPBody soapBody = envelope.getBody();

        SOAPElement requestDetalharProcedimentos = soapBody.addChildElement("requestDetalharProcedimento", "proc");

        SOAPElement codigoProcedimento = requestDetalharProcedimentos.addChildElement("codigoProcedimento", "proc1");
        codigoProcedimento.addTextNode("0203010027");

        SOAPElement detalhesAdicionais = requestDetalharProcedimentos.addChildElement("DetalhesAdicionais", "proc");

        SOAPElement detalheAdicional = detalhesAdicionais.addChildElement("DetalheAdicional", "det");

        SOAPElement categoriaDetalheAdicional = detalheAdicional.addChildElement("categoriaDetalheAdicional","det");
        categoriaDetalheAdicional.addTextNode("DESCRICAO");

        //Paginacao
        SOAPElement paginacao = detalheAdicional.addChildElement("Paginacao", "det");
        SOAPElement registroInicial = paginacao.addChildElement("registroInicial", "pag");
        registroInicial.addTextNode("01");
        SOAPElement quantidadeRegistros = paginacao.addChildElement("quantidadeRegistros", "pag");
        quantidadeRegistros.addTextNode("20");
        SOAPElement totalRegistros = paginacao.addChildElement("totalRegistros", "pag");
        totalRegistros.addTextNode("100");
    }
}
