package br.com.integracaosigtap.factory;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import br.com.integracaobsus.factory.Barramento;
import br.com.integracaobsus.factory.BarramentoFactory;

public class BarramentoFactoryTest {

	@Test
	public void testRetornarBarramento() {
		Barramento barramento = BarramentoFactory.getIntanceBarramento("PROCEDIMENTO");

		Assert.assertTrue(barramento != null);
	}

	@Test
	public void testDefinirUrlBarramento() {
		Barramento barramento = BarramentoFactory.getIntanceBarramento("PROCEDIMENTO");

		Properties properties = new Properties();
		
		properties.put("URL_PROCEDIMENTO", "http://url.procedimento.com");
		properties.put("URL_COMPATIBILIDADE", "http://url.compatibilidade.com");
		properties.put("URL_GRUPO", "http://url.grupo.com");
		properties.put("CREDENCIAL_BSUS", "http://url.credencial.com");
		
		barramento.setURLConnection(properties);

		// TODO verificar se url da conexão foi realmente definida
	}
}
