package br.com.integracaosigtap.connect;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

/**
 * Created by Leonard0Rocha on 26/06/17.
 */
public class RequestListarGruposTest {

	private String URLGRUPO = "https://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1";

	@Test
	public void testListarGrupos() throws Exception {
		Connection connection = new RequestListarGrupos();
		String resposta = connection.getListarGrupos(URLGRUPO);
		
		String esperado = "niv:responseListarGrupos";
		Assert.assertTrue(resposta.contains(esperado));
		
		esperado = "grup:Grupo";
		Assert.assertTrue(resposta.contains(esperado));
		
		esperado = "sub:Subgrupo";
		Assert.assertFalse(resposta.contains(esperado));
		
	}
	@Test
	public void testListarGruposErro() throws Exception {
		Connection connection = new RequestListarGrupos();
		String resposta = connection.getListarGrupos(URLGRUPO);
		
		String esperado = "requestNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
		
		esperado = "respondeNotMatch";
		Assert.assertFalse(resposta.contains(esperado));
	}

}