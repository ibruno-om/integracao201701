package br.com.integracaosigtap.model.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.integracaosigtap.model.Model;

public abstract class AbstractXMLHandler<T extends Model> extends DefaultHandler {

	private List<T> resultList;
	private String content;
	private String headerModel = "";
	private Class<T> classType;

	protected T model;

	public AbstractXMLHandler(Class<T> classType, String headerModel) throws NullPointerException {

		if (classType == null) {
			throw new NullPointerException("Não se pode enviar um classType Nulo");
		}

		this.resultList = new ArrayList<T>();
		this.headerModel = headerModel;
		this.classType = classType;
		try {
			model = classType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName != null) {

			if (qName.equals(this.headerModel)) {
				try {
					model = classType.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName != null && qName.equals(this.headerModel)) {
			getResultList().add(model);
		} else {
			switch (qName) {
			case "ns4:codigo":
				model.setCodigo(getContent());
				break;
			case "ns4:nome":
				model.setNome(getContent());
				break;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		this.content = String.copyValueOf(ch, start, length).trim();
	}

	/**
	 * @return the resultList
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * @return Modelo da Iteração
	 */
	public T getModel() {
		return this.model;
	}

	/**
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(List<T> resultList) {
		if (this.resultList == null)
			this.resultList = new ArrayList<T>();
		this.resultList = resultList;
	}

	/**
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

}
