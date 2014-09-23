package com.github.bjoern2.flow.loader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.github.bjoern2.flow.model.GenericInjector;
import com.github.bjoern2.flow.model.JobImpl;
import com.github.bjoern2.flow.model.Next;
import com.github.bjoern2.flow.model.NextImpl;
import com.github.bjoern2.flow.model.TaskImpl;
import com.github.bjoern2.flow.model.Tasklet;
import com.github.bjoern2.flow.xml.Bean;
import com.github.bjoern2.flow.xml.Inject;
import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.Property;
import com.github.bjoern2.flow.xml.XMLReader;

public class XmlConfigurationLoader {

    public com.github.bjoern2.flow.model.Job load(InputStream stream) {
        XMLReader reader = new XMLReader();
        Job job = reader.read(stream);
        return load(job);
    }
    
    public com.github.bjoern2.flow.model.Job load(String xml) {
        XMLReader reader = new XMLReader();
        Job job = reader.read(xml);
        return load(job);
    }
    
	public com.github.bjoern2.flow.model.Job load(Job xmlJob) {
		com.github.bjoern2.flow.model.Job job = new JobImpl();
		for (Property prop : xmlJob.getProperties()) {
			Object value = null;
			if (prop.getBean() != null) {
				value = initBean(prop.getBean());
			} else {
				value = prop.getValue();
			}
			job.setProperty(prop.getName(), value);
		}
		for (com.github.bjoern2.flow.xml.Task xmlTask : xmlJob.getTasks()) {
			com.github.bjoern2.flow.model.TaskImpl<Tasklet> task = new TaskImpl<Tasklet>();
			task.setId(xmlTask.getId());
			try {
				task.setTasklet((Tasklet) Class.forName(xmlTask.getClazz()).newInstance());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			GenericInjector injector = new GenericInjector();
			for (Inject xmlInject : xmlTask.getInjects()) {
				injector.addMapping(xmlInject.getPropertyRef(), xmlInject.getName());
			}
			task.setInjector(injector);

			final List<Next> nexts = new ArrayList<Next>();
			for (com.github.bjoern2.flow.xml.Next next : xmlTask.getNexts()) {
			    nexts.add(new NextImpl(next.getOn(), next.getRef()));
			}
			task.setNexts(nexts);

			job.addTask(task);
		}

		return job;
	}

	private Object initBean(Bean b) {
		try {
			Class<?> clazz = Class.forName(b.getClazz());

			Object bean = clazz.newInstance();
			if (b.getProperties() != null) {
				for (Property prop : b.getProperties()) {
					Object value = null;

					if (prop.getBean() != null) {
						value = initBean(prop.getBean());
					} else {
						value = prop.getValue();
					}

//					PropertyUtils.setProperty(bean, prop.getName(), value);
//					PropertyUtils.setMappedProperty(bean, prop.getName(), value);
					BeanUtils.setProperty(bean, prop.getName(), value);
				}
			}
			
			return bean;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Can not initiate bean", e);
		}
	}

}
