package com.github.bjoern2.flow.xml;

import java.util.Properties;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PropertyXmlAdapter extends XmlAdapter<Property[], Properties> {

	@Override
	public Properties unmarshal(Property[] v) throws Exception {
		Properties props = new Properties();
		for (Property p : v) {
			props.setProperty(p.getName(), p.getValue());
		}
		return props;
	}

	@Override
	public Property[] marshal(Properties v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
