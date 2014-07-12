package com.github.bjoern2.flow.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bjoern on 09.06.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "job")
public class Job {

	@XmlAttribute
    private String id;

	@XmlAttribute
    private String version;

    @XmlElement(name = "property")
    @XmlElementWrapper(name = "properties")
    private List<Property> properties;

    @XmlElement(name = "task")
    @XmlElementWrapper(name = "tasks")
    private List<Task> tasks;



    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

	@Override
	public String toString() {
		return "Job [id=" + id + ", version=" + version + ", properties=" + properties + ", tasks=" + tasks + "]";
	}

}
