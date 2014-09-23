package com.github.bjoern2.flow.model;

import java.util.Properties;

public interface PropertyEjector<T extends Tasklet> {

	void eject(Tasklet tasklet, Properties properties);
	
}
