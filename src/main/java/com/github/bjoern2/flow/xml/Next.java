package com.github.bjoern2.flow.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by bjoern on 16.06.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Next {

	@XmlAttribute
    private String on;

	@XmlAttribute
    private String ref;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

	@Override
	public String toString() {
		return "Next [on=" + on + ", ref=" + ref + "]";
	}
}
