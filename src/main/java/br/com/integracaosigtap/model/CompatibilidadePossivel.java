package br.com.integracaosigtap.model;

import br.com.integracaosigtap.model.handler.annotation.XMLAttribute;
import br.com.integracaosigtap.model.handler.annotation.XMLClass;

/**
 * Created by astr1x on 21/05/17.
 */
@XMLClass(nodeName = "ns9:CompatibilidadePossivel", codigo = "com1:codigo")
public class CompatibilidadePossivel extends Model {

	@XMLAttribute(fieldName = "com1:tipoCompatibilidade")
	private String tipoCompatibilidade;
	
	@XMLAttribute(fieldName = "com1:InstrumentoRegistroPrincipal")
	private InstrumentoRegistro instrumentoRegistroPrimario;
	
	@XMLAttribute(fieldName = "com1:InstrumentoRegistroSecundario")
	private InstrumentoRegistro instrumentoRegistroSecundario;

	/**
	 * @return
	 */
	public String getTipoCompatibilidade() {
		return tipoCompatibilidade;
	}

	/**
	 * @param tipoCompatibilidade
	 */
	public void setTipoCompatibilidade(String tipoCompatibilidade) {
		this.tipoCompatibilidade = tipoCompatibilidade;
	}

	/**
	 * @return
	 */
	public InstrumentoRegistro getInstrumentoRegistroPrimario() {
		return instrumentoRegistroPrimario;
	}

	/**
	 * @param instrumentoRegistroPrimario
	 */
	public void setInstrumentoRegistroPrimario(InstrumentoRegistro instrumentoRegistroPrimario) {
		this.instrumentoRegistroPrimario = instrumentoRegistroPrimario;
	}

	/**
	 * @return
	 */
	public InstrumentoRegistro getInstrumentoRegistroSecundario() {
		return instrumentoRegistroSecundario;
	}

	/**
	 * @param instrumentoRegistroSecundario
	 */
	public void setInstrumentoRegistroSecundario(InstrumentoRegistro instrumentoRegistroSecundario) {
		this.instrumentoRegistroSecundario = instrumentoRegistroSecundario;
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
		result = prime * result + ((instrumentoRegistroPrimario == null) ? 0 : instrumentoRegistroPrimario.hashCode());
		result = prime * result
				+ ((instrumentoRegistroSecundario == null) ? 0 : instrumentoRegistroSecundario.hashCode());
		result = prime * result + ((tipoCompatibilidade == null) ? 0 : tipoCompatibilidade.hashCode());
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
		CompatibilidadePossivel other = (CompatibilidadePossivel) obj;
		if (instrumentoRegistroPrimario == null) {
			if (other.instrumentoRegistroPrimario != null)
				return false;
		} else if (!instrumentoRegistroPrimario.equals(other.instrumentoRegistroPrimario))
			return false;
		if (instrumentoRegistroSecundario == null) {
			if (other.instrumentoRegistroSecundario != null)
				return false;
		} else if (!instrumentoRegistroSecundario.equals(other.instrumentoRegistroSecundario))
			return false;
		if (tipoCompatibilidade == null) {
			if (other.tipoCompatibilidade != null)
				return false;
		} else if (!tipoCompatibilidade.equals(other.tipoCompatibilidade))
			return false;
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompatibilidadePossivel [tipoCompatibilidade=" + tipoCompatibilidade + ", instrumentoRegistroPrimario="
				+ instrumentoRegistroPrimario + ", instrumentoRegistroSecundario=" + instrumentoRegistroSecundario
				+ ", toString()=" + super.toString() + "]";
	}

}
