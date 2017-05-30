package br.com.integracaosigtap.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ResultadosPesquisaProcedimentos", namespace = "http://servicos.saude.gov.br/wsdl/mensageria/v1r0/resultadospesquisaprocedimentos")
public class ResultadosPesquisaProcedimentos {

	@XmlElement(name = "ResultadosPesquisaProcedimentos")
	public ArrayList<BaseProcedimento> procedimentos;

}
