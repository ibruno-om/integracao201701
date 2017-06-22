package br.com.integracaosigtap.model.handler;

import org.xml.sax.SAXException;

import br.com.integracaosigtap.model.Grupo;

public class GrupoHandler extends AbstractXMLHandler<Grupo> {



	public GrupoHandler() {
		super(Grupo.class, "grup:Grupo");
	}



	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "grup:Grupo":
			getResultList().add(model);
			break;
		case "ns4:codigo":
			model.setCodigo(getContent());
			break;
		case "ns4:nome":
			model.setNome(getContent());
			break;
		}
	}




}
