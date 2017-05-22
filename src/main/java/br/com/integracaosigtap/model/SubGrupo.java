package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class SubGrupo extends Model {

    private Grupo grupo;

    public SubGrupo(){

    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
