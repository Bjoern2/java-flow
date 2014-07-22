package com.github.bjoern2.flow;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;

import com.github.bjoern2.flow.tasklet.Tasklet;

public class GenericInjector implements PropertyInjector<Tasklet> {

	private Map<String, String> mappings;
	
	@Override
	public void inject(Tasklet tasklet, Properties properties) {
    	Method method = null;
    	try {
    		method = tasklet.getClass().getMethod("setProperty", String.class, Object.class);
    	} catch (NoSuchMethodException e) {
    		method = null;
    	}
        for (Entry<String, String> mapping : mappings.entrySet()) {
        	Object value = properties.get(mapping.getKey());
            try {
            	
                PropertyUtils.setProperty(tasklet, mapping.getValue(), value);
            } catch (Exception e) {
                if (method != null) {
                    try {
						method.invoke(tasklet, mapping.getKey(), value);
					} catch (Exception e1) {
						throw new RuntimeException(e1);
					}
                }
            }
        }
		
	}

}