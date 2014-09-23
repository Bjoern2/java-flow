package com.github.bjoern2.flow;

import com.github.bjoern2.flow.model.Tasklet;

/**
 * Created by bjoern on 07.06.2014.
 */
public class TestTask implements Tasklet {

    private String name;

    private String greetings;

    @Override
    public String execute() {
        this.greetings = "Hello " + name;
        return "SUCCESS";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreetings() {
        return greetings;
    }

}
