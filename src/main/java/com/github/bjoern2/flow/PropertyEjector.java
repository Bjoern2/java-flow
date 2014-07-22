package com.github.bjoern2.flow;

import java.util.Properties;

import com.github.bjoern2.flow.tasklet.Tasklet;

public interface PropertyEjector<T extends Tasklet> {

	void eject(Tasklet tasklet, Properties properties);
	
}
