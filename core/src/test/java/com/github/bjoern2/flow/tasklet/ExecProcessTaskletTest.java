package com.github.bjoern2.flow.tasklet;

import org.junit.Assert;
import org.junit.Test;

public class ExecProcessTaskletTest {

    @Test
    public void test() throws Throwable {
        ExecProcessTasklet e = new ExecProcessTasklet();
        e.setCommand("ping localhost");
        String result = e.execute();
        Assert.assertEquals("SUCCESS", result);
    }

}
