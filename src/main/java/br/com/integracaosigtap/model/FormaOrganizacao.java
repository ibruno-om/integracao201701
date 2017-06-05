package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class FormaOrganizacao extends Model {

    private Grupo grupo;

    public FormaOrganizacao(){

    }

    public Grupo getSubGrupo() {
        return grupo;
    }

    public void setSubGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
