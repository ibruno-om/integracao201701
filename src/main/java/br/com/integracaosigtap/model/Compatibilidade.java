package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class Compatibilidade extends Model {

	private CompatibilidadePossivel compatibilidadePossivel;
	private Procedimento procedimentoPrimario;
	private Procedimento procedimentoSecundario;
	private String competenciaInicial;
	private String competenciaFinal;
	private Integer quantidadePermitida;
	private DocumentoPublicacao documentoPublicacao;

	public Compatibilidade() {

	}

	public CompatibilidadePossivel getCompatibilidadePossivel() {
		return compatibilidadePossivel;
	}

	public void setCompatibilidadePossivel(CompatibilidadePossivel compatibilidadePossivel) {
		this.compatibilidadePossivel = compatibilidadePossivel;
	}

	public Procedimento getProcedimentoPrimario() {
		return procedimentoPrimario;
	}

	public void setProcedimentoPrimario(Procedimento procedimentoPrimario) {
		this.procedimentoPrimario = procedimentoPrimario;
	}

	public Procedimento getProcedimentoSecundario() {
		return procedimentoSecundario;
	}

	public void setProcedimentoSecundario(Procedimento procedimentoSecundario) {
		this.procedimentoSecundario = procedimentoSecundario;
	}

	public String getCompetenciaInicial() {
		return competenciaInicial;
	}

	public void setCompetenciaInicial(String competenciaInicial) {
		this.competenciaInicial = competenciaInicial;
	}

	public String getCompetenciaFinal() {
		return competenciaFinal;
	}

	public void setCompetenciaFinal(String competenciaFinal) {
		this.competenciaFinal = competenciaFinal;
	}

	public Integer getQuantidadePermitida() {
		return quantidadePermitida;
	}

	public void setQuantidadePermitida(Integer quantidadePermitida) {
		this.quantidadePermitida = quantidadePermitida;
	}

	public DocumentoPublicacao getDocumentoPublicacao() {
		return documentoPublicacao;
	}

	public void setDocumentoPublicacao(DocumentoPublicacao documentoPublicacao) {
		this.documentoPublicacao = documentoPublicacao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((compatibilidadePossivel == null) ? 0 : compatibilidadePossivel.hashCode());
		result = prime * result + ((competenciaFinal == null) ? 0 : competenciaFinal.hashCode());
		result = prime * result + ((competenciaInicial == null) ? 0 : competenciaInicial.hashCode());
		result = prime * result + ((documentoPublicacao == null) ? 0 : documentoPublicacao.hashCode());
		result = prime * result + ((procedimentoPrimario == null) ? 0 : procedimentoPrimario.hashCode());
		result = prime * result + ((procedimentoSecundario == null) ? 0 : procedimentoSecundario.hashCode());
		result = prime * result + ((quantidadePermitida == null) ? 0 : quantidadePermitida.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Compatibilidade other = (Compatibilidade) obj;
		if (compatibilidadePossivel == null) {
			if (other.compatibilidadePossivel != null)
				return false;
		} else if (!compatibilidadePossivel.equals(other.compatibilidadePossivel))
			return false;
		if (competenciaFinal == null) {
			if (other.competenciaFinal != null)
				return false;
		} else if (!competenciaFinal.equals(other.competenciaFinal))
			return false;
		if (competenciaInicial == null) {
			if (other.competenciaInicial != null)
				return false;
		} else if (!competenciaInicial.equals(other.competenciaInicial))
			return false;
		if (documentoPublicacao == null) {
			if (other.documentoPublicacao != null)
				return false;
		} else if (!documentoPublicacao.equals(other.documentoPublicacao))
			return false;
		if (procedimentoPrimario == null) {
			if (other.procedimentoPrimario != null)
				return false;
		} else if (!procedimentoPrimario.equals(other.procedimentoPrimario))
			return false;
		if (procedimentoSecundario == null) {
			if (other.procedimentoSecundario != null)
				return false;
		} else if (!procedimentoSecundario.equals(other.procedimentoSecundario))
			return false;
		if (quantidadePermitida == null) {
			if (other.quantidadePermitida != null)
				return false;
		} else if (!quantidadePermitida.equals(other.quantidadePermitida))
			return false;
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Compatibilidade [compatibilidadePossivel=" + compatibilidadePossivel + ", procedimentoPrimario="
				+ procedimentoPrimario + ", procedimentoSecundario=" + procedimentoSecundario + ", competenciaInicial="
				+ competenciaInicial + ", competenciaFinal=" + competenciaFinal + ", quantidadePermitida="
				+ quantidadePermitida + ", documentoPublicacao=" + documentoPublicacao + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
