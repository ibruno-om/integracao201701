package br.com.integracaosigtap.connect;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestPesquisarCompatibilidadesFactory extends RequestSIGTAPFactory {

    @Override
    public RequestSIGTAP create() throws Exception {
        return new RequestPesquisarCompatibilidades();
    }
}
