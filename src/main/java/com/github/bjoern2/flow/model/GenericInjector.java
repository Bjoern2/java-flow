package com.github.bjoern2.flow.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;

public class GenericInjector implements PropertyInjector<Tasklet> {

	private Map<String, String> mappings = new HashMap<String, String>();
	
	@Override
	public void inject(Tasklet tasklet, Properties properties) {
//    	Method method = null;
//    	try {
//    		method = tasklet.getClass().getMethod("setProperty", String.class, Object.class);
//    	} catch (NoSuchMethodException e) {
//    		method = null;
//    	}
    	Injectable i = null;
    	if (tasklet.getClass().isAssignableFrom(Injectable.class)) {
    		i = (Injectable)tasklet;
    	}
    	
        for (Entry<String, String> mapping : mappings.entrySet()) {
            if (mapping.getKey() == null) {
                continue;
            }
            
        	Object value = properties.get(mapping.getKey());
            try {
            	
                PropertyUtils.setProperty(tasklet, mapping.getValue(), value);
            } catch (Exception e) {
//                if (method != null) {
//                    try {
//						method.invoke(tasklet, mapping.getKey(), value);
//					} catch (Exception e1) {
//						throw new RuntimeException(e1);
//					}
//                }
            	
            	if (i != null) {
            		i.inject(mapping.getValue(), value);
            	} else {
            		throw new RuntimeException("Can not inject property " + mapping.getValue());
            	}
            }
        }
		
	}
	
	public void addMapping(String src, String target) {
		mappings.put(src, target);
	}

}
