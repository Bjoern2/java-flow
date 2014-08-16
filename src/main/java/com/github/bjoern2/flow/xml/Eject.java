package com.github.bjoern2.flow.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by bjoern on 10.06.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Eject {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "propertyRef")
    private String propertyRef;

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


}
