package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarSubGrupos extends ConnectionSUS {


    protected RequestListarSubGrupos() throws Exception {
    }

    public void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("niv", "http://servicos.saude.gov.br/sigtap/v1/nivelagregacaoservice");
        envelope.addNamespaceDeclaration("grup", "http://servicos.saude.gov.br/schema/sigtap/procedimento/nivelagregacao/v1/grupo");
    }

    @Override
    public void addContent() throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestListarSubGrupos = soapBody.addChildElement("requestListarSubgrupos", "niv");

    }
}
