package com.github.bjoern2.flow.tasklet.ci.git;

import static org.junit.Assert.fail;

import org.junit.Test;

public class GitPullTaskletTest {

    @Test
    public void test() throws Throwable {
        GitPullTasklet t = new GitPullTasklet();
        t.execute();
        
        fail("Not yet implemented");
    }

}
