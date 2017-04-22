package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPException;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarFormaOrganizacao extends RequestSIGTAP {

    static String url = "";

    protected RequestListarFormaOrganizacao() throws Exception {
        super(url);
    }

    protected void addNameSpace() throws SOAPException {

    }

    @Override
    protected void addContent() throws SOAPException {

    }
}
