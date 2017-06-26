package br.com.integracaosigtap.connect;

import org.junit.Assert;
import org.junit.Test;

public class RequestListarCompatibilidadesPossiveisTest {
	
	private String URL_COMPATIBILIDADE_POSSIVEIS = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";
	
	@Test
	public void testListarCompatibilidadesPossiveisSuccesso() throws Exception {
		Connection connection = new RequestListarCompatibilidadesPossiveis();
		String resposta = connection.getListarCompatibilidadesPossiveis(URL_COMPATIBILIDADE_POSSIVEIS);
		
		String esperado = "responseListarCompatibilidadesPossiveis";
		Assert.assertTrue(resposta.contains(esperado));
		
		esperado = "com1:CompatibilidadePossive";
		Assert.assertTrue(resposta.contains(esperado));
	}

	@Test
	public void testListarCompatibilidadesPossiveisErro() throws Exception {
		Connection connection = new RequestListarCompatibilidadesPossiveis();
		String resposta = connection.getListarCompatibilidadesPossiveis(URL_COMPATIBILIDADE_POSSIVEIS);
		
		String esperado = "requestNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
		
		esperado = "respondeNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
	}
}
