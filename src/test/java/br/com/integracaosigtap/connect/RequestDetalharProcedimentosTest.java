package br.com.integracaosigtap.connect;

import org.junit.Assert;
import org.junit.Test;

public class RequestDetalharProcedimentosTest {

	@Test
	public void testDetalharProcedimentos() throws Exception {
		Connection connection = new RequestDetalharProcedimentos();
		String resposta = connection.getDetalharProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS);
		
		String esperado = "proc:responseDetalharProcedimento";
		Assert.assertTrue(resposta.contains(esperado));
		
		esperado = "res:ResultadosDetalhaProcedimentos";
		Assert.assertTrue(resposta.contains(esperado));
	}
	
	@Test
	public void testDetalharProcedimentosErro() throws Exception {
		Connection connection = new RequestDetalharProcedimentos();
		String resposta = connection.getPesquisarProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS);
		
		String esperado = "proc:responsePesquisarProcedimentos";
		Assert.assertTrue(resposta.contains(esperado));
		
		esperado = "res:ResultadosPesquisaProcedimentos";
		Assert.assertTrue(resposta.contains(esperado));
	}
}
