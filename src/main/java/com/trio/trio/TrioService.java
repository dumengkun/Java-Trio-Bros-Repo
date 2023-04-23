package com.trio.trio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.lang.reflect.Method;

import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrioService 
{
    public String execute(String code) throws Exception 
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    
        String className = extractClassName(code);
        JavaFileObject file = new InMemoryJavaFileObject(className, code);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
    
        Map<String, ByteArrayOutputStream> classBytes = new HashMap<>();
        JavaFileManager fileManager = new ForwardingJavaFileManager<>(standardFileManager) 
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
    
        JavaCompiler.CompilationTask task = compiler.getTask(new PrintWriter(outputStream), fileManager, diagnostics, null, null, compilationUnits);
        boolean success = task.call();
        
        if (success) 
        {
            return runCode(className, code, classBytes);
        } 
        else 
        {
            throw new Exception("Compilation failed:\n" + outputStream.toString());
        }
    }
    

    private String runCode(String className, String code, Map<String, ByteArrayOutputStream> classBytes) throws Exception 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    
        try 
        {
            Class<?> codeRunnerClass = InMemoryClassLoader.load(className, code, classBytes);
            Method mainMethod = codeRunnerClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[]{});
    
            return outputStream.toString();
        } 
        finally 
        {
            System.setOut(originalOut);
        }
    }

    private String extractClassName(String code)
    {
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(code);
    
        if (matcher.find()) 
        {
            return matcher.group(1);
        } 
        else
        {
            throw new IllegalArgumentException("No public class found in the submitted code");
        }
    }
}