package com.github.bjoern2.flow;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.github.bjoern2.flow.tasklet.Tasklet;
import com.github.bjoern2.flow.xml.Bean;
import com.github.bjoern2.flow.xml.Inject;
import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.Next;
import com.github.bjoern2.flow.xml.Property;

public class XmlConfigurationLoader {

	public com.github.bjoern2.flow.Job load(Job xmlJob) {
		com.github.bjoern2.flow.Job job = new JobImpl();
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
			com.github.bjoern2.flow.Task<Tasklet> task = new TaskImpl<Tasklet>();
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

			for (Next next : xmlTask.getNexts()) {
				task.addNext(next.getOn(), next.getRef());
			}

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
