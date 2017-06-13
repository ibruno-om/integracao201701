package br.com.integracaosigtap.model.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.integracaosigtap.model.Grupo;

public class GrupoHandler extends DefaultHandler {

	private Grupo grupo;
	private List<Grupo> grupos;
	private String content;

	public GrupoHandler() {
		this.grupos = new ArrayList<Grupo>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		switch (qName) {
		case "grup:Grupo":
			grupo = new Grupo();
			break;

		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "grup:Grupo":
			grupos.add(grupo);
			break;
		case "ns4:codigo":
			grupo.setCodigo(content);
			break;
		case "ns4:nome":
			grupo.setNome(content);
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		this.content = String.copyValueOf(ch, start, length).trim();
	}

	/**
	 * @return the grupos
	 */
	public List<Grupo> getList() {
		return grupos;
	}


}
