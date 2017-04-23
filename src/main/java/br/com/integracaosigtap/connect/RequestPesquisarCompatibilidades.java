package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestPesquisarCompatibilidades extends RequestSIGTAP {

    static String url = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadeService/v1";

    protected RequestPesquisarCompatibilidades() throws Exception {
        super(url);
    }

    protected void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/sigtap/v1/compatibilidadeservice");
        envelope.addNamespaceDeclaration("com1", "http://servicos.saude.gov.br/schema/sigtap/compatibilidade/v1r1/compatibilidadepossivel");
        envelope.addNamespaceDeclaration("com2", "http://servicos.saude.gov.br/schema/corporativo/v1/competencia");
        envelope.addNamespaceDeclaration("grup", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/grupo");
        envelope.addNamespaceDeclaration("sub", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/subgrupo");
        envelope.addNamespaceDeclaration("proc", "http://servicos.saude.gov.br/schema/sigtap/procedimento/v1/procedimento");
        envelope.addNamespaceDeclaration("pag", "http://servicos.saude.gov.br/wsdl/mensageria/v1/paginacao");

    }

    @Override
    protected void addContent() throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestPesquisarCompatibilidades = soapBody.addChildElement("requestPesquisarCompatibilidades", "com");

        SOAPElement codigoCompatibilidadePossivel = requestPesquisarCompatibilidades.addChildElement("codigoCompatibilidadePossivel", "com1");
        codigoCompatibilidadePossivel.addTextNode("01");

        SOAPElement competencia = requestPesquisarCompatibilidades.addChildElement("competencia", "com2");
        competencia.addTextNode("200801");

        SOAPElement codigoGrupo = requestPesquisarCompatibilidades.addChildElement("codigoGrupo", "grup");
        codigoGrupo.addTextNode("05");

        SOAPElement codigoSubgrupo = requestPesquisarCompatibilidades.addChildElement("codigoSubgrupo", "sub");
        codigoSubgrupo.addTextNode("05");

        //Paginacao
        SOAPElement paginacao = requestPesquisarCompatibilidades.addChildElement("Paginacao", "pag");
        SOAPElement registroInicial = paginacao.addChildElement("registroInicial", "pag");
        registroInicial.addTextNode("01");
        SOAPElement quantidadeRegistros = paginacao.addChildElement("quantidadeRegistros", "pag");
        quantidadeRegistros.addTextNode("20");
    }
}
