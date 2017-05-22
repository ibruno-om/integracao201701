import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import br.com.integracaosigtap.connect.Connection;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.Envelope;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Connection connection = new ConnectionSUS();
        
        JAXBContext jc = JAXBContext.newInstance(Envelope.class);
        
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        System.out.println(connection.getPesquisarProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));
        
        StringReader rs = new StringReader(connection.getPesquisarProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));
        Envelope procedimento = (Envelope) unmarshaller.unmarshal(rs);
        System.out.println(procedimento);
        
      

//        System.out.println(connection.getDetalharProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));

//        System.out.println(connection.getListarCompatibilidadesPossiveis(ConnectionSUS.URL_COMPATIBILIDADE_POSSIVEIS));

//        System.out.println(connection.getPesquisarCompatibilidades(ConnectionSUS.URL_COMPATIBILIDADE));

//        System.out.println(connection.getListarGrupos(ConnectionSUS.URL_GRUPO));

//        System.out.println(connection.getListarSubGrupos(ConnectionSUS.URL_GRUPO));

//        System.out.println(connection.getListarFormaOrganizacao(ConnectionSUS.URL_GRUPO));

    }
}
