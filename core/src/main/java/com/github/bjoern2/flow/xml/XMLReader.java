package com.github.bjoern2.flow.xml;

import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by bjoern on 18.06.2014.
 */
public class XMLReader {

    public Job read(String xml) {
        try {
            JAXBContext c = JAXBContext.newInstance(Job.class);
            Unmarshaller u = c.createUnmarshaller();
            return (Job) u.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Job read(InputStream xml) {
        try {
            JAXBContext c = JAXBContext.newInstance(Job.class);
            Unmarshaller u = c.createUnmarshaller();
            return (Job) u.unmarshal(xml);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
