import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.com.integracaobsus.impl.BSus;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.Grupo;
import br.com.integracaosigtap.model.handler.SubGrupoHandler;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

	public static void main(String[] args) throws Exception {

		BSus bsus = new BSus();
		bsus.setProperties(null);

		// bsus.pesquisarCompatibilidades();


		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		SubGrupoHandler handler = new SubGrupoHandler();

		String xml = new ConnectionSUS()
				.getListarSubGrupos("https://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1");
		
		System.out.println(xml);
		parser.parse(new ByteArrayInputStream(xml
				.getBytes(StandardCharsets.UTF_8)), handler);

		// Printing the list of employees obtained from XML
		for (Grupo emp : handler.getResultList()) {
			System.out.println(emp.getCodigo() + "-" + emp.getNome());
		}

		// List<Compatibilidade> pesquisarCompatibilidades =
		// bsus.pesquisarCompatibilidades();
		//
		// for (Compatibilidade compatibilidade : pesquisarCompatibilidades) {
		// System.out.println(compatibilidade);
		//
		// }

		// ConnectionSUS connection = new ConnectionSUS();
		// System.out.println(connection.getDetalharProcedimentos("https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1"));

		// System.out.println(connection.getDetalharProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));

		// System.out.println(connection.getListarCompatibilidadesPossiveis(ConnectionSUS.URL_COMPATIBILIDADE_POSSIVEIS));

		// System.out.println(connection.getPesquisarCompatibilidades(ConnectionSUS.URL_COMPATIBILIDADE));

		// System.out.println(connection.getListarGrupos(ConnectionSUS.URL_GRUPO));

		// System.out.println(connection.getListarSubGrupos(ConnectionSUS.URL_GRUPO));

		// System.out.println(connection.getListarFormaOrganizacao(ConnectionSUS.URL_GRUPO));

	}
}
