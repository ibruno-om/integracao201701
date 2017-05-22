package br.com.integracaosigtap.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2003/05/soap-envelope")
@XmlType(propOrder = "responsePesquisarProcedimentos")
public class Body {

	@XmlElement(name = "responsePesquisarProcedimentos", namespace = "http://servicos.saude.gov.br/sigtap/v1/procedimentoservice", type = ResponsePesquisarProcedimentos.class)
	public ResponsePesquisarProcedimentos responsePesquisarProcedimentos;

}
