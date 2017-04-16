package br.ufg.inf;

import br.ufg.inf.soap.message.RequestPesquisarProcedimentosFactory;
import br.ufg.inf.soap.message.RequestSIGTAP;
import br.ufg.inf.soap.message.RequestSIGTAPFactory;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

    static RequestSIGTAPFactory factory;

    public static void main(String[] args) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        /**
         *  Instancia a factory da request que deseja fazer, e depois chama o metodo create()
         *  para obter uma instancia da request
         *  ex. factory = new RequestDetalharProcedimentosFactory();
         */
        //TODO implementar as outras requests baseadas no formato do xml q ele esperada como requisicao.

        //FIXME esta dando um erro que fala
        //FIXME Invalid content was found starting with element 'proc1:CodigoProcedimento'. One of '{&quot;http://servicos.saude.gov.br/schema/sigtap/procedimento/v1/procedimento&quot;:codigoProcedimento}' is expected.]">
//        factory = new RequestDetalharProcedimentosFactory();

        factory = new RequestPesquisarProcedimentosFactory();

        RequestSIGTAP request = factory.create();

        SOAPMessage soapResponse = soapConnection.call(request.getSOAPMessage(), request.url);

        // Process the SOAP Response
        printSOAPResponse(soapResponse);

        soapConnection.close();
    }

    /**
     *
     * @param soapResponse
     * @throws Exception
     */

    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }
}
