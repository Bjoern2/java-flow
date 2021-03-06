package com.github.bjoern2.flow.run;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.github.bjoern2.flow.loader.XmlConfigurationLoader;
import com.github.bjoern2.flow.model.Job;
import com.github.bjoern2.flow.model.JobImpl;
import com.github.bjoern2.flow.model.Next;
import com.github.bjoern2.flow.model.NextImpl;
import com.github.bjoern2.flow.model.PropertyInjector;
import com.github.bjoern2.flow.model.TaskImpl;
import com.github.bjoern2.flow.tasklet.GreetingsTasklet;
import com.github.bjoern2.flow.xml.XMLReader;

public class DefaultJobRunnerTest {

    @Test
    public void testManual() throws Exception {
        Job job = new JobImpl();
        job.setProperty("message", "Hello GitHub!");
        
        
        TaskImpl<GreetingsTasklet> task1 = new TaskImpl<GreetingsTasklet>();
        task1.setId("step1");
        task1.setTasklet(new GreetingsTasklet());
        task1.setInjector(new PropertyInjector<GreetingsTasklet>() {
            @Override
            public void inject(GreetingsTasklet tasklet, Properties properties) {
                tasklet.setMessage(properties.getProperty("message"));
                
            }
        });
        List<Next> nexts = new ArrayList<>();
        nexts.add(new NextImpl("SUCCESS", "step2"));
        task1.setNexts(nexts);
        job.addTask(task1);
        
        TaskImpl<GreetingsTasklet> task2 = new TaskImpl<GreetingsTasklet>();
        task2.setId("step2");
        task2.setTasklet(new GreetingsTasklet());
        task2.setInjector(new PropertyInjector<GreetingsTasklet>() {
            @Override
            public void inject(GreetingsTasklet tasklet, Properties properties) {
                tasklet.setMessage("Hello Björn");
                
            }
        });
        job.addTask(task2);
        
        DefaultJobRunner runner = new DefaultJobRunner();
        runner.run(job, new Properties(), Arrays.asList("step1"));
    }
    
    @Test
    public void testXml() throws Exception {
        InputStream in = getClass().getResourceAsStream("/com/github/bjoern2/flow/xml/helloworld.xml");
        String xml = IOUtils.toString(in);
        
        XMLReader reader = new XMLReader();
        com.github.bjoern2.flow.xml.Job xmlJob = reader.read(xml);
        
        XmlConfigurationLoader loader = new XmlConfigurationLoader();
        Job job = loader.load(xmlJob);
        
        DefaultJobRunner runner = new DefaultJobRunner();
        runner.run(job, new Properties(), Arrays.asList("start"));
    }

}
