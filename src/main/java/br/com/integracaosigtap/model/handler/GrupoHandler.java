package br.com.integracaosigtap.model.handler;

import org.xml.sax.SAXException;

import br.com.integracaosigtap.model.Grupo;

public class GrupoHandler extends AbstractXMLHandler<Grupo> {

	public GrupoHandler() {
		super(Grupo.class, "grup:Grupo");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//Obrigatório utilizar método da superClass
		super.endElement(uri, localName, qName);
	}

}
