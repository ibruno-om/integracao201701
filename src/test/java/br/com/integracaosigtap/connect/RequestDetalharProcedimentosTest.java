package br.com.integracaosigtap.connect;

import org.junit.Assert;
import org.junit.Test;

public class RequestDetalharProcedimentosTest {

	private String URL_PROCEDIMENTOS = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";

	@Test
	public void testDetalharProcedimentos() throws Exception {
		Connection connection = new RequestDetalharProcedimentos();
		String resposta = connection.getDetalharProcedimentos(URL_PROCEDIMENTOS);

		String esperado = "proc:responseDetalharProcedimento";
		Assert.assertTrue(resposta.contains(esperado));

		esperado = "res:ResultadosDetalhaProcedimentos";
		Assert.assertTrue(resposta.contains(esperado));
	}

	@Test
	public void testDetalharProcedimentosErro() throws Exception {
		Connection connection = new RequestDetalharProcedimentos();
		String resposta = connection.getPesquisarProcedimentos(URL_PROCEDIMENTOS);

		String esperado = "proc:responsePesquisarProcedimentos";
		Assert.assertTrue(resposta.contains(esperado));

		esperado = "res:ResultadosPesquisaProcedimentos";
		Assert.assertTrue(resposta.contains(esperado));
	}
}
