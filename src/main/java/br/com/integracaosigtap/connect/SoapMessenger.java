package br.com.integracaosigtap.connect;

import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 15/04/17.
 */
public interface SoapMessenger {

    SOAPMessage getSOAPMessage() throws Exception;
}
