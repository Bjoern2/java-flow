package com.github.bjoern2.flow.model;

import java.util.Properties;

public interface PropertyInjector<T extends Tasklet> {

	void inject(T tasklet, Properties properties);
	
}
