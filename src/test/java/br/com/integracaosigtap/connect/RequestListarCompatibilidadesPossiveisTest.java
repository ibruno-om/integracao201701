package br.com.integracaosigtap.connect;

import org.junit.Assert;
import org.junit.Test;

public class RequestListarCompatibilidadesPossiveisTest {
	
	private String URL_PROCEDIMENTOS = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";

	@Test
	public void testListarCompatibilidadesPossiveisSuccesso() throws Exception {
		Connection connection = new RequestListarCompatibilidadesPossiveis();
		String resposta = connection.getListarCompatibilidadesPossiveis(URL_PROCEDIMENTOS);
		

		String esperado = "requestListarCompatibilidadesPossiveis";
		Assert.assertTrue(resposta.contains(esperado));
		
		esperado = "env:Fault";
		Assert.assertTrue(resposta.contains(esperado));
	}

	@Test
	public void testListarCompatibilidadesPossiveisErro() throws Exception {
		Connection connection = new RequestListarCompatibilidadesPossiveis();
		String resposta = connection.getListarCompatibilidadesPossiveis(URL_PROCEDIMENTOS);
		
		String esperado = "requestNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
		
		esperado = "respondeNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
	}
}
