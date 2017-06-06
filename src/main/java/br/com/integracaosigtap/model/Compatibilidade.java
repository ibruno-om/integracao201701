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
}
