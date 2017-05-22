package br.com.integracaosigtap.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responsePesquisarProcedimentos", namespace = "http://servicos.saude.gov.br/sigtap/v1/procedimentoservice")
public class ResponsePesquisarProcedimentos {
	
	@XmlElementWrapper(name = "ResultadosPesquisaProcedimentos", namespace = "http://servicos.saude.gov.br/wsdl/mensageria/v1r0/resultadospesquisaprocedimentos")
	@XmlElement(name = "BaseProcedimento", namespace = "http://servicos.saude.gov.br/schema/sigtap/procedimento/redeatencao/v1/redeatencao", type = BaseProcedimento.class)
	public ArrayList<BaseProcedimento> procedimentos;

}
