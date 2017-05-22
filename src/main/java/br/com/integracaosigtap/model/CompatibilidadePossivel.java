package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class CompatibilidadePossivel extends Model {

    private String tipoCompatibilidade;
    private InstrumentoRegistroPrimario instrumentoRegistroPrimario;
    private InstrumentoRegistroSecundario instrumentoRegistroSecundario;

    public CompatibilidadePossivel(){

    }

    public String getTipoCompatibilidade() {
        return tipoCompatibilidade;
    }

    public void setTipoCompatibilidade(String tipoCompatibilidade) {
        this.tipoCompatibilidade = tipoCompatibilidade;
    }

    public InstrumentoRegistroPrimario getInstrumentoRegistroPrimario() {
        return instrumentoRegistroPrimario;
    }

    public void setInstrumentoRegistroPrimario(InstrumentoRegistroPrimario instrumentoRegistroPrimario) {
        this.instrumentoRegistroPrimario = instrumentoRegistroPrimario;
    }

    public InstrumentoRegistroSecundario getInstrumentoRegistroSecundario() {
        return instrumentoRegistroSecundario;
    }

    public void setInstrumentoRegistroSecundario(InstrumentoRegistroSecundario instrumentoRegistroSecundario) {
        this.instrumentoRegistroSecundario = instrumentoRegistroSecundario;
    }
}
