package br.com.integracaosigtap.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelope", namespace="http://www.w3.org/2003/05/soap-envelope")
public class Envelope {

	@XmlElement(name = "Body", namespace = "http://www.w3.org/2003/05/soap-envelope", type = Body.class)
	public Body body;

}
