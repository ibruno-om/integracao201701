package br.com.integracaosigtap.connect;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

/**
 * Created by Leonard0Rocha on 26/06/17.
 */
public class RequestListarSubGruposTest {

	private String URL_COMPATIBILIDADES = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";

	@Test
	public void testListarSubGrupos() throws Exception {
		Connection connection = new RequestListarSubGrupos();
		String resposta = connection.getListarSubGrupos(URL_COMPATIBILIDADES);
		
		String esperado = "requestListarSubgrupos";
		Assert.assertTrue(resposta.contains(esperado));
		
	}
	@Test
	public void testListarSubGruposErro() throws Exception {
		Connection connection = new RequestListarSubGrupos();
		String resposta = connection.getListarSubGrupos(URL_COMPATIBILIDADES);
		
		String esperado = "requestNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
		
		esperado = "respondeNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
	}

}