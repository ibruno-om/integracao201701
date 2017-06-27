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
	private Object parentField;

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

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName != null) {

			if (qName.equals(this.xmlClass.nodeName())) {
				try {
					this.model = this.classType.newInstance();
					setInstanceXML(this.model);
					this.modelIn = this.model;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				if (this.model != null) {
					setFieldAndModel(qName, this.classType, this.model);
				}
			}
		}

	}

	private void setInstanceXML(Object model) {
		for (Field field : model.getClass().getDeclaredFields()) {
			if (field.getType().getAnnotation(XMLClass.class) != null) {
				try {
					Object newInstance = field.getType().newInstance();
					field.setAccessible(true);
					field.set(model, newInstance);
					setInstanceXML(newInstance);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setFieldAndModel(String qName, Class<?> classType, Object parentField) {

		try {
			for (Field field : classType.getDeclaredFields()) {

				XMLAttribute attribute = field.getAnnotation(XMLAttribute.class);

				if (attribute != null) {
					field.setAccessible(true);
					if (qName.equals(attribute.fieldName())) {
						this.fieldIn = field;
						this.parentField = parentField;
						this.modelIn = field.get(this.parentField);
					} else {
						if (field.getType().getAnnotation(XMLClass.class) != null) {
							setFieldAndModel(qName, field.getType(), field.get(parentField));
						}
					}
					field.setAccessible(false);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
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
					if (this.fieldIn != null) {
						try {
							this.fieldIn.setAccessible(true);

							XMLAttribute attribute = this.fieldIn.getAnnotation(XMLAttribute.class);
							XMLClass xmlClass = this.fieldIn.getType().getAnnotation(XMLClass.class);

							if (attribute != null && qName.equals(attribute.fieldName())) {
								if (Number.class.isAssignableFrom(this.fieldIn.getType())) {
									this.fieldIn.set(this.parentField, new Integer(getContent()));
								} else if (String.class.isAssignableFrom(this.fieldIn.getType())) {
									this.fieldIn.set(this.parentField, getContent());
								}
							} else if (xmlClass != null) {

								if (qName.equals(xmlClass.codigo())) {
									((Model) this.fieldIn.get(this.parentField)).setCodigo(getContent());
								} else if (qName.equals(xmlClass.nome())) {
									((Model) this.fieldIn.get(this.parentField)).setNome(getContent());
								}

							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						} finally {
							this.fieldIn.setAccessible(false);
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
