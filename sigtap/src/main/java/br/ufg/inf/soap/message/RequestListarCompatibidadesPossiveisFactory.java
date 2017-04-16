package br.ufg.inf.soap.message;

/**
 * Created by astr1x on 16/04/17.
 */
public class RequestListarCompatibidadesPossiveisFactory extends RequestSIGTAPFactory {

    @Override
    public RequestSIGTAP create() throws Exception {
        return new RequestListarCompatibilidadesPossiveis();
    }
}
