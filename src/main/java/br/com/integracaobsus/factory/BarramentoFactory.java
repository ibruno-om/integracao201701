package br.com.integracaobsus.factory;

import java.util.Properties;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;

/**
 * Factory responsável por retornar instâncias de {@link Barramento}.
 * 
 * Para o funcionamento correto desta classe e suas depenências, o serviço
 * Consul deverá ser configurado, e deverá conter as seguintes chaves
 * configuradas com seu valor correspondente:
 * 
 * <ul>
 * <li>URL_PROCEDIMENTO_BSUS: Url do serviço de procedimentos</li>
 * <li>URL_COMPATIBILIDADE_BSUS: Url do serviço de Compatibilidades</li>
 * <li>URL_GRUPO_BSUS: Url do serviço de Grupos</li>
 * <li>CREDENCIAL_BSUS: Credencial para acessar o serviço</li>
 * </ul>
 * 
 * O caminho do Consul service deverá ser configurado como uma variável de
 * ambiente de nome: <b>CONSUL_HOST</b>, caso a mesma não seja informada, o
 * caminho padrão <b>CONSUL_SERVER</b> será procurado como nome de servidor para
 * o serviço Consul.
 * 
 * @author Iago Bruno
 * @version 1.0.1
 */
public class BarramentoFactory {

	private static String URL_CONSUL = "CONSUL_SERVER";
	private static String URL_PROCEDIMENTO = "URL_PROCEDIMENTO_BSUS";
	private static String URL_COMPATIBILIDADE = "URL_COMPATIBILIDADE_BSUS";
	private static String URL_GRUPO = "URL_GRUPO_BSUS";
	private static String CREDENCIAL = "CREDENCIAL_BSUS";

	/**
	 * Retorna uma instância de {@link Barramento}, referente a chave informada.
	 * A instância será retornada conforme o valor informado parao nome da
	 * Classe para uma chave, através do serviço Consul
	 * 
	 * @param key
	 *            Chave cadastrada no serviço Consul, referente a classe
	 *            utilizada para instanciar um Barramento
	 * @return Retorna uma instância de {@link Barramento} caso a chave
	 *         informada tenha um valor válido, caso não, null será retornado.
	 */
	public static Barramento getIntanceBarramento(String key) {

		try {
			String className = getConsulValue(key);

			Barramento barramento = (Barramento) Class.forName(className).cast(Barramento.class);
			
			Properties properties = new Properties();
			
			properties.put("URL_PROCEDIMENTO", getConsulValue(URL_PROCEDIMENTO));
			properties.put("URL_COMPATIBILIDADE", getConsulValue(URL_COMPATIBILIDADE));
			properties.put("URL_GRUPO", getConsulValue(URL_GRUPO));
			properties.put("CREDENCIAL_BSUS", getConsulValue(CREDENCIAL));

			barramento.setURLConnection(properties);

		} catch (ClassCastException e) {
			System.err.println("Instância de classe incompatível com barramento, informe uma chave correta.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retorna o nome da classe cadastrada com uma chave no serviço Consul. Caso
	 * não tenha sido fornecida URL do Consul, a mesma é definida como padrão
	 * Localhost
	 * 
	 * @param key
	 * @return
	 */
	private static String getConsulValue(String key) {

		String consulURL = System.getenv("CONSUL_HOST");

		ConsulClient client = new ConsulClient(consulURL != null ? consulURL : URL_CONSUL);

		Response<GetValue> keyValueResponse = client.getKVValue(key);

		return keyValueResponse.getValue().getDecodedValue();
	}

}
