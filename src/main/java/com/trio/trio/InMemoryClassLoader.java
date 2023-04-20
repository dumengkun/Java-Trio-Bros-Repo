package com.trio.trio;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Map;

public class InMemoryClassLoader extends URLClassLoader 
{
    private final Map<String, ByteArrayOutputStream> classBytes;

    public InMemoryClassLoader(ClassLoader parent, Map<String, ByteArrayOutputStream> classBytes) 
    {
        super(new URL[0], parent);
        this.classBytes = classBytes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException 
    {
        ByteArrayOutputStream buf = classBytes.get(name);

        if (buf == null)
        {
            return super.findClass(name);
        }

        byte[] bytes = buf.toByteArray();
        return defineClass(name, bytes, 0, bytes.length);
    }

    public static Class<?> load(String className, String code, Map<String, ByteArrayOutputStream> classBytes) throws Exception
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        JavaFileObject file = new InMemoryJavaFileObject(className, code);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);

        JavaFileManager fileManager = new ForwardingJavaFileManager<>(compiler.getStandardFileManager(diagnostics, null, null)) 
        {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) 
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                classBytes.put(className, baos);
                return new SimpleJavaFileObject(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind) 
                {
                    @Override
                    public OutputStream openOutputStream() 
                    {
                        return baos;
                    }
                };
            }
        };

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

        if (!task.call()) 
        {
            throw new Exception("Failed to compile the class " + className);
        }

        InMemoryClassLoader classLoader = new InMemoryClassLoader(ClassLoader.getSystemClassLoader(), classBytes);

        return classLoader.loadClass(className);
    }
}