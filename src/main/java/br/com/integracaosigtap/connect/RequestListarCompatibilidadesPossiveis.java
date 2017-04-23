package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarCompatibilidadesPossiveis extends RequestSIGTAP {

    static String url = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";

    protected RequestListarCompatibilidadesPossiveis() throws Exception {
        super(url);
    }

    protected void addNameSpace() throws SOAPException {
        envelope.addNamespaceDeclaration("com", "http://servicos.saude.gov.br/sigtap/v1/compatibilidadepossivelservice");
    }

    @Override
    protected void addContent() throws SOAPException {
        SOAPBody soapBody = envelope.getBody();
        SOAPElement requestPesquisarProcedimentos = soapBody.addChildElement("requestListarCompatibilidadesPossiveis", "com");
    }
}
