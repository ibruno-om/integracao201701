import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import br.com.integracaosigtap.connect.Connection;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.Procedimento;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	
    	
    	XMLInputFactory fabrica = XMLInputFactory.newFactory();

        Connection connection = new ConnectionSUS();
        
        
        System.out.println(connection.getPesquisarProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));
        
        StringReader rs = new StringReader(connection.getPesquisarProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));
        
        XMLEventReader reader = fabrica.createXMLEventReader(rs);
        
        Procedimento procedimento = null;
        List<Procedimento> procedimentos = new ArrayList<Procedimento>();
        
        while(reader.hasNext()){
        	 XMLEvent event = reader.nextEvent();
        	 
        	 if (event.isStartElement()){
        		 StartElement startElement = event.asStartElement();
        		 
        		 if (startElement.getName().getLocalPart().equals("BaseProcedimento")){
        			 procedimento = new Procedimento();
        		 } else if (startElement.getName().getLocalPart().equals("codigo")){
        			 event = reader.nextEvent();
        			 System.out.println(event.asCharacters().getData());
        			 procedimento.setCodigo(event.asCharacters().getData());
        		 } else if (startElement.getName().getLocalPart().equals("nome")){
        			 event = reader.nextEvent();
        			 System.out.println(event.asCharacters().getData());
        			 procedimento.setNome(event.asCharacters().getData());
        		 }
        	 }
        	 
        	 if (event.isEndElement()){
        		 procedimentos.add(procedimento);
        	 }
        	 
        }
        
        System.out.println(procedimentos.size());

//        System.out.println(connection.getDetalharProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));

//        System.out.println(connection.getListarCompatibilidadesPossiveis(ConnectionSUS.URL_COMPATIBILIDADE_POSSIVEIS));

//        System.out.println(connection.getPesquisarCompatibilidades(ConnectionSUS.URL_COMPATIBILIDADE));

//        System.out.println(connection.getListarGrupos(ConnectionSUS.URL_GRUPO));

//        System.out.println(connection.getListarSubGrupos(ConnectionSUS.URL_GRUPO));

//        System.out.println(connection.getListarFormaOrganizacao(ConnectionSUS.URL_GRUPO));

    }
}
