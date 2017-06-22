package br.com.integracaosigtap.model.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractXMLHandler<T> extends DefaultHandler {

	private List<T> resultList;
	private String content;
	private String headerModel = "";
	private Class<T> classType;

	protected T model;

	public AbstractXMLHandler(Class<T> classType, String headerModel) {
		this.resultList = new ArrayList<T>();
		this.headerModel = headerModel;
		this.classType = classType;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName != null && qName.equals(this.headerModel)) {
			try {
				model = classType.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
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
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	/**
	 * @return
	 */
	public String getContent(){
		return this.content;
	}

}
