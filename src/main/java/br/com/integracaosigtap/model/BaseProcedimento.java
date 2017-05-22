package br.com.integracaosigtap.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BaseProcedimento", namespace = "http://servicos.saude.gov.br/schema/sigtap/procedimento/redeatencao/v1/redeatencao")
@XmlType(propOrder = { "codigo", "nome" })
public class BaseProcedimento {

	private String codigo;

	private String nome;

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
