package br.com.integracaobsus.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import br.com.integracaobsus.factory.Barramento;
import br.com.integracaosigtap.connect.Connection;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.BaseProcedimento;

public class BSus implements Barramento {

	private String urlProcedimento = "https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1";
	private String urlCompatibilidade = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadeService/v1";
	private String urlCompatibilidadePossivel = "https://servicoshm.saude.gov.br/sigtap/CompatibilidadePossivelService/v1";
	private String urlGrupo = "https://servicoshm.saude.gov.br/sigtap/NivelAgregacaoService/v1";

	private Connection connection;

	public void setProperties(Properties properties) {
		try {
			if (properties != null) {
				urlProcedimento = (String) properties.get("URL_PROCEDIMENTO");
				urlCompatibilidade = (String) properties.get("URL_COMPATIBILIDADE");
				urlGrupo = (String) properties.get("URL_GRUPO");
				connection = new ConnectionSUS(properties);
			} else {
				connection = new ConnectionSUS();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BaseProcedimento> pesquisarProcedimentos() {
		try {
			XMLInputFactory fabrica = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(connection.getPesquisarProcedimentos(urlProcedimento));

			XMLEventReader reader = fabrica.createXMLEventReader(rs);

			BaseProcedimento procedimento = null;
			Set<BaseProcedimento> procedimentos = new HashSet<BaseProcedimento>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("BaseProcedimento")) {
						procedimento = new BaseProcedimento();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						procedimento.setCodigo(event.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("nome")) {
						event = reader.nextEvent();
						procedimento.setNome(event.asCharacters().getData());
						procedimentos.add(procedimento);
					}
				}

			}

			return new ArrayList<BaseProcedimento>(procedimentos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
