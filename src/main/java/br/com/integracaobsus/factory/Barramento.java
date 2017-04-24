package br.com.integracaobsus.factory;

/**
 * Interface de Barramento BSUS
 * 
 * A classe que implementar esta inferface terá que implementar um construtor
 * padrão.
 * 
 * @author Iago Bruno
 * @version 1.0
 *
 */
public interface Barramento {

	public void setURLConnection(String urlProcedimento, String urlCompatibilidadePossivel, String urlGrupos);

}
