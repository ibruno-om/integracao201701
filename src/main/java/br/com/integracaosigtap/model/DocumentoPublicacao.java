package br.com.integracaosigtap.model;

import br.com.integracaosigtap.model.handler.annotation.XMLAttribute;
import br.com.integracaosigtap.model.handler.annotation.XMLClass;

/**
 * Created by astr1x on 21/05/17.
 */
@XMLClass(nodeName = "ns9:DocumentoPublicacao")
public class DocumentoPublicacao {

	@XMLAttribute(fieldName = "ns10:numeroDocumento")
	private String numeroDocumento;

	@XMLAttribute(fieldName = "ns10:dataPublicacao")
	private String dataPublicacao;

	@XMLAttribute(fieldName = "ns10:TipoDocumento")
	private TipoDocumento tipoDocumento;

	@XMLAttribute(fieldName = "ns10:OrgaoOrigem")
	private OrgaoOrigem orgaoOrigem;

	/**
	 * @return
	 */
	public String getDataPublicacao() {
		return dataPublicacao;
	}

	/**
	 * @param dataPublicacao
	 */
	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	/**
	 * @return
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public OrgaoOrigem getOrgaoOrigem() {
		return orgaoOrigem;
	}

	public void setOrgaoOrigem(OrgaoOrigem orgaoOrigem) {
		this.orgaoOrigem = orgaoOrigem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPublicacao == null) ? 0 : dataPublicacao.hashCode());
		result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
		result = prime * result + ((orgaoOrigem == null) ? 0 : orgaoOrigem.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoPublicacao other = (DocumentoPublicacao) obj;
		if (dataPublicacao == null) {
			if (other.dataPublicacao != null)
				return false;
		} else if (!dataPublicacao.equals(other.dataPublicacao))
			return false;
		if (numeroDocumento == null) {
			if (other.numeroDocumento != null)
				return false;
		} else if (!numeroDocumento.equals(other.numeroDocumento))
			return false;
		if (orgaoOrigem == null) {
			if (other.orgaoOrigem != null)
				return false;
		} else if (!orgaoOrigem.equals(other.orgaoOrigem))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		return super.equals(obj);
	}

}
