package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarFormaOrganizacao extends ConnectionSUS {

    protected RequestListarFormaOrganizacao() throws Exception {
    }

    public void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("niv", "http://servicos.saude.gov.br/sigtap/v1/nivelagregacaoservice");
        envelope.addNamespaceDeclaration("sub", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/subgrupo");
    }

    @Override
    public void addContent() throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestListarFormaOrganizacao = soapBody.addChildElement("requestListarFormaOrganizacao", "niv");

        SOAPElement codigoGrupo = requestListarFormaOrganizacao.addChildElement("codigoSubgrupo", "sub");
        codigoGrupo.addTextNode("01");
    }
}
