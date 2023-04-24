package com.trio.trio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.StandardJavaFileManager;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrioService 
{
    public String compileAndRunJavaCode(String code)
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String className = "Trio";
        String fileName = className + ".java";
        String outputDirectory = "./tempCode/";

        File sourceFile = new File(outputDirectory + fileName);
        sourceFile.getParentFile().mkdirs();

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(java.util.Collections.singletonList(sourceFile));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

        try (FileWriter fileWriter = new FileWriter(sourceFile)) 
        {
            fileWriter.write(code);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }

        if (!task.call()) 
        {
            StringBuilder errorMessage = new StringBuilder();

            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) 
            {
                errorMessage.append(diagnostic).append("\n");
            }

            return errorMessage.toString();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            try (URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(outputDirectory).toURI().toURL()})) 
            {
                Class<?> cls = classLoader.loadClass(className);
                cls.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
        
        return outputStream.toString();
    } 
}