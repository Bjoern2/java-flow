package com.github.bjoern2.flow.tasklet;

import javax.tools.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by bjoern on 16.06.2014.
 */
public class GroovyTasklet implements Tasklet {

    private String code;

    public class StringJavaFileObject extends SimpleJavaFileObject {
        private final CharSequence code;

        public StringJavaFileObject(String name, CharSequence code) {
            super( URI.create( "string:///" + name.replace( '.', '/' ) + Kind.SOURCE.extension ),
                    Kind.SOURCE );
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    @Override
    public String execute() throws Throwable {
        JavaFileObject javaFile = new StringJavaFileObject("test", "class A { static { System.out.println(\"Java Compiler API 2\"); } }");


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager( null, null, null );
        Iterable<? extends JavaFileObject> units = Arrays.asList( javaFile );
        JavaCompiler.CompilationTask task = compiler.getTask( null, fileManager, null, null, null, units );
        task.call();
        fileManager.close();

        URLClassLoader classLoader = new URLClassLoader( new URL[] { new File(".").getAbsoluteFile().toURI().toURL() } );
        Class.forName("A", true, classLoader);    // Java Compiler API 2



        return TaskletStatus.SUCCESS;
    }
}
