package main.java.br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarGrupos extends RequestSIGTAP {

    static String url = "";

    protected RequestListarGrupos() throws Exception {
        super(url);
        addContent();
    }

    public SOAPMessage getSOAPMessage() throws Exception {
        return null;
    }

    @Override
    protected void addContent() throws SOAPException {

    }
}
