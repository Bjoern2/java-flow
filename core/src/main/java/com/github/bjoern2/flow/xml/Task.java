package com.github.bjoern2.flow.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by bjoern on 09.06.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

	@XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "class")
    private String clazz;

    @XmlElementWrapper(name = "injects")
    @XmlElement(name = "inject")
    private List<Inject> injects;

    @XmlElementWrapper(name = "ejects")
    @XmlElement(name = "eject")
    private List<Eject> ejects;
    
    @XmlElementWrapper(name = "nexts")
    @XmlElement(name = "next")
    private List<Next> nexts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<Inject> getInjects() {
        return injects;
    }

    public void setInjects(List<Inject> injects) {
        this.injects = injects;
    }

    public List<Eject> getEjects() {
        return ejects;
    }

    public void setEjects(List<Eject> ejects) {
        this.ejects = ejects;
    }

    public List<Next> getNexts() {
        return nexts;
    }

    public void setNexts(List<Next> nexts) {
        this.nexts = nexts;
    }

	@Override
	public String toString() {
		return "Task [id=" + id + ", clazz=" + clazz + ", injects=" + injects + ", ejects=" + ejects + ", nexts=" + nexts + "]";
	}
}
