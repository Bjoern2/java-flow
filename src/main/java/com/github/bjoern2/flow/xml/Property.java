package com.github.bjoern2.flow.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by bjoern on 09.06.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

	@XmlAttribute
    private String name;

    @XmlAttribute
    private String value;
    
    @XmlElement(required = false)
    private Bean bean;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	@Override
	public String toString() {
		return "Property [name=" + name + ", value=" + value + "]";
	}

	public Bean getBean() {
		return bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}
}
