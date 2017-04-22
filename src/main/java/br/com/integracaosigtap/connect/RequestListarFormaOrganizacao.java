package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarFormaOrganizacao extends RequestSIGTAP {

    static String url = "";

    protected RequestListarFormaOrganizacao() throws Exception {
        super(url);
    }

    public SOAPMessage getSOAPMessage() throws Exception {
        return null;
    }

    protected void addNameSpace() throws SOAPException {

    }

    @Override
    protected void addContent() throws SOAPException {

    }
}
