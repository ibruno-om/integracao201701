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

	private Connection connection;

	public void setProperties(Properties properties) {
		try {
			connection = new ConnectionSUS();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BaseProcedimento> pesquisarProcedimentos() {
		try {
			XMLInputFactory fabrica = XMLInputFactory.newFactory();

			StringReader rs = new StringReader(connection.getPesquisarProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));

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
					}
				}

				if (event.isEndElement()) {
					if (procedimento != null){
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