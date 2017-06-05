package br.com.integracaosigtap.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by astr1x on 21/05/17.
 */
public class Procedimento extends Model{

    private FormaOrganizacao formaOrganizacao;
    private String competenciaInicial;
    private String finalidadePublicacao;
    private DocumentoPublicacao documentoPublicacao;
    private List<ModalidadeAtendimento> modalidadesAtendimento;
    private List<InstrumentoRegistro> instrumentosRegistro;
    private List<AtributoComplementar> atributosComplementares;
    private String complexidade;
    private TipoFinanciamento tipoFinanciamento;
    private SexoPermitido sexoPermitido;
    private IdadePermitida idadeMinimaPermitida;
    private IdadePermitida idadeMaximaPermitida;
    private BigDecimal valorSH;
    private BigDecimal valorSA;
    private BigDecimal valorSP;
    private String descricao;
    private DetalheAdicional detalheAdicional;

    //TODO atributos adicionais do xml que possivelmente serao usados, podem ser simples ou compostos
    //    <ns6:CIDsVinculados/>
    //    <ns6:CBOsVinculados/>
    //    <ns6:CategoriasCBOVinculadas/>
    //    <ns6:TiposLeitoVinculados/>
    //    <ns6:ServicosClassificacoesVinculados/>
    //    <ns6:HabilitacoesVinculadas/>
    //    <ns6:GruposHabilitacaoVinculados/>
    //    <ns6:IncrementosVinculados/>
    //    <ns6:ComponentesRedesVinculados/>
    //    <ns6:OrigensSIGTAP/>
    //    <ns6:OrigensSIASIH/>
    //    <ns6:RegrasCondicionadasVinculadas/>
    //    <ns6:RENASESVinculadas/>
    //    <ns6:TUSSVinculadas/>

    public Procedimento(){

    }

    public FormaOrganizacao getFormaOrganizacao() {
        return formaOrganizacao;
    }

    public void setFormaOrganizacao(FormaOrganizacao formaOrganizacao) {
        this.formaOrganizacao = formaOrganizacao;
    }

    public String getCompetenciaInicial() {
        return competenciaInicial;
    }

    public void setCompetenciaInicial(String competenciaInicial) {
        this.competenciaInicial = competenciaInicial;
    }

    public String getFinalidadePublicacao() {
        return finalidadePublicacao;
    }

    public void setFinalidadePublicacao(String finalidadePublicacao) {
        this.finalidadePublicacao = finalidadePublicacao;
    }

    public DocumentoPublicacao getDocumentoPublicacao() {
        return documentoPublicacao;
    }

    public void setDocumentoPublicacao(DocumentoPublicacao documentoPublicacao) {
        this.documentoPublicacao = documentoPublicacao;
    }

    public List<ModalidadeAtendimento> getModalidadesAtendimento() {
        return modalidadesAtendimento;
    }

    public void setModalidadesAtendimento(List<ModalidadeAtendimento> modalidadesAtendimento) {
        this.modalidadesAtendimento = modalidadesAtendimento;
    }

    public List<InstrumentoRegistro> getInstrumentosRegistro() {
        return instrumentosRegistro;
    }

    public void setInstrumentosRegistro(List<InstrumentoRegistro> instrumentosRegistro) {
        this.instrumentosRegistro = instrumentosRegistro;
    }

    public List<AtributoComplementar> getAtributosComplementares() {
        return atributosComplementares;
    }

    public void setAtributosComplementares(List<AtributoComplementar> atributosComplementares) {
        this.atributosComplementares = atributosComplementares;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }

    public TipoFinanciamento getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public void setTipoFinanciamento(TipoFinanciamento tipoFinanciamento) {
        this.tipoFinanciamento = tipoFinanciamento;
    }

    public SexoPermitido getSexoPermitido() {
        return sexoPermitido;
    }

    public void setSexoPermitido(SexoPermitido sexoPermitido) {
        this.sexoPermitido = sexoPermitido;
    }

    public IdadePermitida getIdadeMinimaPermitida() {
        return idadeMinimaPermitida;
    }

    public void setIdadeMinimaPermitida(IdadePermitida idadeMinimaPermitida) {
        this.idadeMinimaPermitida = idadeMinimaPermitida;
    }

    public IdadePermitida getIdadeMaximaPermitida() {
        return idadeMaximaPermitida;
    }

    public void setIdadeMaximaPermitida(IdadePermitida idadeMaximaPermitida) {
        this.idadeMaximaPermitida = idadeMaximaPermitida;
    }

    public BigDecimal getValorSH() {
        return valorSH;
    }

    public void setValorSH(BigDecimal valorSH) {
        this.valorSH = valorSH;
    }

    public BigDecimal getValorSA() {
        return valorSA;
    }

    public void setValorSA(BigDecimal valorSA) {
        this.valorSA = valorSA;
    }

    public BigDecimal getValorSP() {
        return valorSP;
    }

    public void setValorSP(BigDecimal valorSP) {
        this.valorSP = valorSP;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DetalheAdicional getDetalheAdicional() {
        return detalheAdicional;
    }

    public void setDetalheAdicional(DetalheAdicional detalheAdicional) {
        this.detalheAdicional = detalheAdicional;
    }
    
    
}
