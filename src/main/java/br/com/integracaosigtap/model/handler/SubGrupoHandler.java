package br.com.integracaosigtap.model.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import br.com.integracaosigtap.model.Grupo;

public class SubGrupoHandler extends AbstractXMLHandler<Grupo> {

	public SubGrupoHandler() {
		super(Grupo.class, "sub:Subgrupo");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName != null && qName.equals("sub:Grupo")){
			System.out.println("aqui");
		}
		super.startElement(uri, localName, qName, attributes);
	}

}
