package br.com.integracaosigtap.connect;

/**
 * Created by astr1x on 24/04/17.
 */

public interface Connection {

    String getPesquisarProcedimentos(String url) throws Exception;

    String getDetalharProcedimentos(String url) throws Exception;

    String getListarCompatibilidadesPossiveis(String url) throws Exception;

    String getPesquisarCompatibilidades(String url) throws Exception;

    String getListarFormaOrganizacao(String url) throws Exception;

    String getListarGrupos(String url) throws Exception;

    String getListarSubGrupos(String url) throws Exception;
}
