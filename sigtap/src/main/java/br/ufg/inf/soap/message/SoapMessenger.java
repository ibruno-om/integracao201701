package br.ufg.inf.soap.message;

import javax.xml.soap.SOAPMessage;

/**
 * Created by astr1x on 15/04/17.
 */
public interface SoapMessenger {

    SOAPMessage getSOAPMessage() throws Exception;
}
