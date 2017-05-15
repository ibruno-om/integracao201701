package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarCompatibilidadesPossiveis extends ConnectionSUS {

    private String url = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";

    public RequestListarCompatibilidadesPossiveis() throws Exception {
    }

    public void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/sigtap/v1/compatibilidadepossivelservice");
    }

    @Override
    public void addContent() throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestPesquisarProcedimentos = soapBody.addChildElement("requestListarCompatibilidadesPossiveis", "com");
    }
}
