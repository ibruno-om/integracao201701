package br.com.integracaobsus.factory;

import java.util.List;
import java.util.Properties;

import br.com.integracaosigtap.model.Procedimento;

/**
 * Interface de Barramento BSUS
 * 
 * A classe que implementar esta inferface terá que implementar um construtor
 * padrão.
 * 
 * @author Iago Bruno
 * @version 1.0.1
 *
 */
public interface Barramento {

	/**
	 * Recebe as propriedades padrões de Conexão, tendo as seguintes chaves:
	 * 
	 * <li>URL_PROCEDIMENTO: Url do serviço de procedimentos</li>
	 * <li>URL_COMPATIBILIDADE: Url do serviço de Compatibilidades</li>
	 * <li>URL_GRUPO: Url do serviço de Grupos</li>
	 * <li>CREDENCIAL: Credencial para acessar o serviço</li>
	 * 
	 * @param properties
	 */
	public void setProperties(Properties properties);
	
	public List<Procedimento> pesquisarProcedimentos();

}
