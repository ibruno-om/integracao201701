package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestPesquisarCompatibilidades extends RequestSIGTAP {

    static String url = "http://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1?";

    protected RequestPesquisarCompatibilidades() throws Exception {
        super(url);
    }

    protected void addNameSpace() throws SOAPException {

    }

    @Override
    protected void addContent() throws SOAPException {

    }
}
