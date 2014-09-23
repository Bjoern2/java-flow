package com.github.bjoern2.flow.model;

public class NextImpl implements Next {

    private String on;
    private String ref;
    
    public NextImpl() {
        super();
    }
    
    public NextImpl(String on, String ref) {
        super();
        this.on = on;
        this.ref = ref;
    }

    @Override
    public String getOn() {
        return on;
    }

    @Override
    public String getRef() {
        return ref;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
