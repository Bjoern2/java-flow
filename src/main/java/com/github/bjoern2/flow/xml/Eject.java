package com.github.bjoern2.flow.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by bjoern on 10.06.2014.
 */
public class Eject {

    @XmlAttribute(name = "fieldName")
    private String fieldName;

    @XmlAttribute(name = "propertyName")
    private String propertyName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
