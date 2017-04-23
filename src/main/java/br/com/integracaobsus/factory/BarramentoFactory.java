package br.com.integracaobsus.factory;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;

/**
 * Factory responsável por retornar instâncias de {@link Barramento}.
 * 
 * @author Iago Bruno
 * @version 1.0
 */
public class BarramentoFactory {

	private String consulURLService;

	@SuppressWarnings("null")
	public BarramentoFactory(String consulURLService) {
		if (consulURLService != null || consulURLService.isEmpty()) {
			this.consulURLService = consulURLService;
		} else {
			this.consulURLService = "localhost";
		}
	}

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
	public Barramento getIntanceBarramento(String key) {

		try {
			String className = getClassName(key);

			return (Barramento) Class.forName(className).cast(Barramento.class);

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
	 * não tenha sido fornecida URL do Consul, a mesma é definida como padrão Localhost
	 * 
	 * @param key
	 * @return
	 */
	private String getClassName(String key) {

		ConsulClient client = new ConsulClient(this.consulURLService);

		Response<GetValue> keyValueResponse = client.getKVValue(key);

		return keyValueResponse.getValue().getDecodedValue();
	}

}
