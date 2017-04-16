package br.ufg.inf.soap.message;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestPesquisarCompatibilidades extends RequestSIGTAP {

    static String url = "http://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1?";

    protected RequestPesquisarCompatibilidades() throws Exception {
        super(url);
        addContent();
    }

    @Override
    public SOAPMessage getSOAPMessage() throws Exception {
        return null;
    }

    @Override
    protected void addContent() throws SOAPException {

    }
}
