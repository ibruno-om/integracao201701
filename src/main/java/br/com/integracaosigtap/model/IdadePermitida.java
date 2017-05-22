package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class IdadePermitida {

    private Integer quantidadeLimite;
    private UnidadeLimite unidadeLimite;

    public IdadePermitida(){

    }

    public Integer getQuantidadeLimite() {
        return quantidadeLimite;
    }

    public void setQuantidadeLimite(Integer quantidadeLimite) {
        this.quantidadeLimite = quantidadeLimite;
    }

    public UnidadeLimite getUnidadeLimite() {
        return unidadeLimite;
    }

    public void setUnidadeLimite(UnidadeLimite unidadeLimite) {
        this.unidadeLimite = unidadeLimite;
    }
}
