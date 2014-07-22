package com.github.bjoern2.flow;

import java.util.Properties;

import com.github.bjoern2.flow.tasklet.Tasklet;

public interface PropertyInjector<T extends Tasklet> {

	void inject(T tasklet, Properties properties);
	
}
