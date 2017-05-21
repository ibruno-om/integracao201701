package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public abstract class Model {

    private String nome;
    private String codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
