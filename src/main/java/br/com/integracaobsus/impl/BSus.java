package br.com.integracaobsus.impl;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import br.com.integracaobsus.factory.Barramento;
import br.com.integracaosigtap.connect.Connection;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.BaseProcedimento;
import br.com.integracaosigtap.model.Compatibilidade;
import br.com.integracaosigtap.model.CompatibilidadePossivel;
import br.com.integracaosigtap.model.Grupo;
import br.com.integracaosigtap.model.InstrumentoRegistro;
import br.com.integracaosigtap.model.Procedimento;
import br.com.integracaosigtap.model.SubGrupo;
import br.com.integracaosigtap.model.handler.BaseProcedimentoHandler;
import br.com.integracaosigtap.model.handler.CompatibilidadeHandler;
import br.com.integracaosigtap.model.handler.GrupoHandler;
import br.com.integracaosigtap.model.handler.ProcedimentoHandler;
import br.com.integracaosigtap.model.handler.SubGrupoHandler;

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

			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			BaseProcedimentoHandler handler = new BaseProcedimentoHandler();

			String xml = connection.getPesquisarProcedimentos(urlProcedimento);
			System.out.println(xml);

			parser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

			return handler.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Compatibilidade> pesquisarCompatibilidades() {

		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			CompatibilidadeHandler handler = new CompatibilidadeHandler();

			String xml = connection.getPesquisarCompatibilidades(urlCompatibilidade);
			System.out.println(xml);

			parser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

			return handler.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Grupo> listarGrupos() {
		try {

			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			GrupoHandler handler = new GrupoHandler();

			String xml = connection.getListarGrupos(urlGrupo);
			System.out.println(xml);

			parser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

			return handler.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<SubGrupo> listarSubGrupos() {
		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SubGrupoHandler handler = new SubGrupoHandler();

			String xml = connection.getListarSubGrupos(urlGrupo);
			System.out.println(xml);

			parser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

			return handler.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Grupo> pesquisarGrupos() {
		return null;
	}

	public Procedimento getDetalharProcedimentos() {
		try {

			String xml = connection.getDetalharProcedimentos(urlProcedimento);

			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			ProcedimentoHandler handler = new ProcedimentoHandler();
			parser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

			return handler.getProcedimento();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<CompatibilidadePossivel> listarCompatibilidadesPossiveis() {

		try {
			XMLInputFactory factory = XMLInputFactory.newFactory();

			System.out.println(connection.getListarCompatibilidadesPossiveis(urlCompatibilidadePossivel));
			
			StringReader rs = new StringReader(
					connection.getListarCompatibilidadesPossiveis(urlCompatibilidadePossivel));

			XMLEventReader reader = factory.createXMLEventReader(rs);

			CompatibilidadePossivel compatibilidadePossivel = null;

			InstrumentoRegistro instrumentoPrimario = null;
			InstrumentoRegistro instrumentoSecundario = null;

			Set<CompatibilidadePossivel> compatibilidadesPossiveis = new HashSet<CompatibilidadePossivel>();

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart().equals("CompatibilidadePossivel")) {
						compatibilidadePossivel = new CompatibilidadePossivel();
					} else if (startElement.getName().getLocalPart().equals("codigo")) {
						event = reader.nextEvent();
						compatibilidadePossivel.setCodigo(event.asCharacters().getData());

					} else if (startElement.getName().getLocalPart().equals("InstrumentoRegistroPrincipal")) {
						instrumentoPrimario = new InstrumentoRegistro();

						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									instrumentoPrimario.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									// último elemento desta estrutura
									event = reader.nextEvent();
									instrumentoPrimario.setNome(event.asCharacters().getData());
									break;
								}
							}
						}

						compatibilidadePossivel.setInstrumentoRegistroPrimario(instrumentoPrimario);

					} else if (startElement.getName().getLocalPart().equals("InstrumentoRegistroSecundario")) {
						instrumentoSecundario = new InstrumentoRegistro();

						while (reader.hasNext()) {
							event = reader.nextEvent();
							if (event.isStartElement()) {
								startElement = event.asStartElement();
								if (startElement.getName().getLocalPart().equals("codigo")) {
									event = reader.nextEvent();
									instrumentoSecundario.setCodigo(event.asCharacters().getData());
								} else if (startElement.getName().getLocalPart().equals("nome")) {
									// último elemento desta estrutura
									event = reader.nextEvent();
									instrumentoSecundario.setNome(event.asCharacters().getData());
									break;
								}
							}
						}

						compatibilidadePossivel.setInstrumentoRegistroSecundario(instrumentoSecundario);

					} else if (startElement.getName().getLocalPart().equals("tipoCompatibilidade")) {
						event = reader.nextEvent();
						compatibilidadePossivel.setTipoCompatibilidade(event.asCharacters().getData());
						compatibilidadesPossiveis.add(compatibilidadePossivel);
						continue;
					}

				}

			}

			return new ArrayList<CompatibilidadePossivel>(compatibilidadesPossiveis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
