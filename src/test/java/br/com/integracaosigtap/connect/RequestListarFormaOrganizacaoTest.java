package br.com.integracaosigtap.connect;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

/**
 * Created by Leonard0Rocha on 26/06/17.
 */
public class RequestListarFormaOrganizacaoTest {

	private String URL_ORGANIZACOES = "https://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1";

	@Test
	public void testListarFormaOrganizacao() throws Exception {
		Connection connection = new RequestListarFormaOrganizacao();
		String resposta = connection.getListarFormaOrganizacao(URL_ORGANIZACOES);

		String esperado = "for:FormaOrganizacao";
		Assert.assertTrue(resposta.contains(esperado));
		
	}
	@Test
	public void testListarFormaOrganizacaoErro() throws Exception {
		Connection connection = new RequestListarFormaOrganizacao();
		String resposta = connection.getListarFormaOrganizacao(URL_ORGANIZACOES);
		
		String esperado = "requestNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
		
		esperado = "respondeNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
	}

}