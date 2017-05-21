package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class FormaOrganizacao extends Model {

    private SubGrupo subGrupo;

    public FormaOrganizacao(){

    }

    public SubGrupo getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(SubGrupo subGrupo) {
        this.subGrupo = subGrupo;
    }
}
