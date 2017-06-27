package br.com.integracaosigtap.model;

import br.com.integracaosigtap.model.handler.annotation.XMLAttribute;
import br.com.integracaosigtap.model.handler.annotation.XMLClass;

@XMLClass(nodeName = "sub:Subgrupo", codigo = "sub:codigo", nome = "sub:nome")
public class SubGrupo extends Grupo {
	
	@XMLAttribute(fieldName = "sub:Grupo")
	private Grupo grupoPai;

	/**
	 * @return the grupoPai
	 */
	public Grupo getGrupoPai() {
		return grupoPai;
	}

	/**
	 * @param grupoPai
	 *            the grupoPai to set
	 */
	public void setGrupoPai(Grupo grupoPai) {
		this.grupoPai = grupoPai;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grupoPai == null) ? 0 : grupoPai.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubGrupo other = (SubGrupo) obj;
		if (grupoPai == null) {
			if (other.grupoPai != null)
				return false;
		} else if (!grupoPai.equals(other.grupoPai))
			return false;
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubGrupo [grupoPai=" + grupoPai + ", getNome()=" + getNome() + ", getCodigo()=" + getCodigo() + "]";
	}
	
	

}
