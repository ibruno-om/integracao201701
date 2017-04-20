package main.java.br.com.integracaosigtap.connect;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarSubGruposFactory extends RequestSIGTAPFactory {
    @Override
    public RequestSIGTAP create() throws Exception {
        return new RequestListarSubGrupos();
    }
}
