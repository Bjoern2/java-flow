package com.github.bjoern2.flow.tasklet.checkstyle;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CheckstyleSAXHandler extends DefaultHandler {

    private long ignore;
    private long info;
    private long warning;
    private long error;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("error")) {
            String severity = attributes.getValue("severity");
            if ("ignore".equals(severity)) {
                ignore++;
            } else if ("info".equals(severity)) {
                info++;
            } else if ("warning".equals(severity)) {
                warning++;
            } else if ("error".equals(severity)) {
                error++;
            }
        }
    }

    public long getIgnore() {
        return ignore;
    }

    public long getInfo() {
        return info;
    }

    public long getWarning() {
        return warning;
    }

    public long getError() {
        return error;
    }
    
}
