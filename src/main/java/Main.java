import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import br.com.integracaobsus.impl.BSus;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.Compatibilidade;
import br.com.integracaosigtap.model.handler.GrupoHandler;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

	public static void main(String[] args) throws Exception {

		BSus bsus = new BSus();
		bsus.setProperties(null);

		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		GrupoHandler handler = new GrupoHandler();

		String xml = new ConnectionSUS()
				.getListarGrupos("https://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1");
		
		System.out.println(xml);

		parser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

		for (Compatibilidade emp : bsus.pesquisarCompatibilidades()) {
			System.out.println(emp);
		}

	}
}
