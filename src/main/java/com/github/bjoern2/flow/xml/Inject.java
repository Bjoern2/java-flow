package com.github.bjoern2.flow.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by bjoern on 10.06.2014.
 */
public class Inject {

    @XmlAttribute(name = "fieldName")
    private String fieldName;

    @XmlAttribute(name = "propertyName")
    private String propertyName;

    @XmlAttribute(name = "value")
    private String value;

    @XmlValue
    private String text;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
