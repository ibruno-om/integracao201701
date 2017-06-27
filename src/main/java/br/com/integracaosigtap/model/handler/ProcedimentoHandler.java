package br.com.integracaosigtap.model.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.integracaosigtap.model.FormaOrganizacao;
import br.com.integracaosigtap.model.Grupo;
import br.com.integracaosigtap.model.Procedimento;

public class ProcedimentoHandler extends DefaultHandler {
	Procedimento procedimento;
	String content;
		
	public Procedimento getProcedimento() {
		return procedimento;
	}

	@Override
	// Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "ns6:Procedimento":
			procedimento = new Procedimento();
			break;
		case "ns6:FormaOrganizacao":
			procedimento.setFormaOrganizacao(new FormaOrganizacao());
			break;
		case "ns8:Subgrupo":
			procedimento.getFormaOrganizacao().setSubGrupo(new Grupo());
			break;
		case "ns2:Grupo":
			procedimento.getFormaOrganizacao().getSubGrupo().setGrupo(new Grupo());
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "ns6:codigo":
			procedimento.setCodigo(content);
			break;
		case "ns6:nome":
			procedimento.setNome(content);
			break;
		case "ns8:codigo":
			procedimento.getFormaOrganizacao().setCodigo(content);
			break;
		case "ns8:nome":
			procedimento.getFormaOrganizacao().setNome(content);
			break;
		case "ns2:codigo":
			procedimento.getFormaOrganizacao().getSubGrupo().setCodigo(content);
			break;
		case "ns2:nome":
			procedimento.getFormaOrganizacao().getSubGrupo().setNome(content);
			break;
		case "grup:codigo":
			procedimento.getFormaOrganizacao().getSubGrupo().getGrupo().setCodigo(content);
			break;
		case "grup:nome":
			procedimento.getFormaOrganizacao().getSubGrupo().getGrupo().setNome(content);
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

}