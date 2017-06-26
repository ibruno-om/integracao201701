package br.com.integracaosigtap.model.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.integracaosigtap.model.Model;
import br.com.integracaosigtap.model.handler.annotation.XMLAttribute;
import br.com.integracaosigtap.model.handler.annotation.XMLClass;

public abstract class AbstractXMLHandler<T extends Model> extends DefaultHandler {

	private Set<T> resultList;
	private String content;
	private XMLClass xmlClass;
	private Class<T> classType;
	private Object modelIn;
	private Field fieldIn;

	protected T model;

	public AbstractXMLHandler(Class<T> classType) throws NullPointerException {

		if (classType == null) {
			throw new NullPointerException("Não se pode enviar um classType Nulo");
		}

		this.xmlClass = classType.getAnnotation(XMLClass.class);

		if (xmlClass == null) {
			throw new NullPointerException("Classe não anotada com XMLClass");
		}

		this.resultList = new HashSet<T>();
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

			if (qName.equals(this.xmlClass.nodeName())) {
				try {
					this.model = this.classType.newInstance();
					this.modelIn = this.model;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				for (Field field : this.classType.getDeclaredFields()) {
					XMLAttribute attribute = field.getAnnotation(XMLAttribute.class);

					if (attribute != null) {
						if (qName.equals(attribute.fieldName())) {
							try {
								this.modelIn = field.getType().newInstance();
								this.fieldIn = field;
							} catch (InstantiationException | IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName != null) {
			if (qName.equals(this.xmlClass.nodeName())) {
				this.resultList.add(model);
			} else {
				if (this.model == this.modelIn) {
					if (qName.equals(this.xmlClass.codigo())) {
						model.setCodigo(getContent());
					} else if (qName.equals(this.xmlClass.nome())) {
						model.setNome(getContent());
					}
				} else {
					if (this.fieldIn != null && this.modelIn != null) {
						XMLAttribute attribute = this.fieldIn.getAnnotation(XMLAttribute.class);
						XMLClass xmlClass = this.modelIn.getClass().getAnnotation(XMLClass.class);
						if (attribute != null && qName.equals(attribute.fieldName())) {
							try {
								this.fieldIn.setAccessible(true);
								this.fieldIn.set(this.model, this.modelIn);
								this.fieldIn.setAccessible(false);

								this.fieldIn = null;
							} catch (IllegalArgumentException | IllegalAccessException e) {
								e.printStackTrace();
							}
						} else if (qName.equals(xmlClass.codigo())) {
							((Model) this.modelIn).setCodigo(getContent());
						} else if (qName.equals(xmlClass.nome())) {
							((Model) this.modelIn).setNome(getContent());
						}
					}
				}
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
		return new ArrayList<T>(resultList);
	}

	/**
	 * @return Modelo da Iteração
	 */
	public T getModel() {
		return this.model;
	}

	/**
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

}
