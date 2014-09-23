package com.github.bjoern2.flow.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by bjoern on 10.06.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Inject {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "propertyRef")
    private String propertyRef;

    @XmlAttribute(name = "value")
    private String value;
    
    @XmlElement
    private Bean bean;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyRef() {
		return propertyRef;
	}

	public void setPropertyRef(String propertyRef) {
		this.propertyRef = propertyRef;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Bean getBean() {
		return bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}

}
