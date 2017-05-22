package br.com.integracaosigtap.model;

import java.util.Date;

/**
 * Created by astr1x on 21/05/17.
 */
public class DocumentoPublicacao{

    private String numeroDocumento;
    private Date dataPublicacao;
    private TipoDocumento tipoDocumento;
    private OrgaoOrigem orgaoOrigem;

    public DocumentoPublicacao(){

    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

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
}
