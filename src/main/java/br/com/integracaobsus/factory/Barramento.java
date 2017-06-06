package br.com.integracaobsus.factory;

import java.util.List;
import java.util.Properties;

import br.com.integracaosigtap.model.BaseProcedimento;

import br.com.integracaosigtap.model.Compatibilidade;
import br.com.integracaosigtap.model.CompatibilidadePossivel;
import br.com.integracaosigtap.model.Grupo;

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
	
	public List<BaseProcedimento> pesquisarProcedimentos();
	

	public List<Compatibilidade> pesquisarCompatibilidades();
	
	public List<CompatibilidadePossivel> listarCompatibilidadesPossiveis();
	
	public List<Grupo> listarGrupos();
	
	public List<Grupo> listarSubGrupos();

}
