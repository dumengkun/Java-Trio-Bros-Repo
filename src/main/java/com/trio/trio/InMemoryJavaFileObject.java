package com.trio.trio;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class InMemoryJavaFileObject extends SimpleJavaFileObject
{
    private final String code;

    public InMemoryJavaFileObject(String className, String code) 
    {
        super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) 
    {
        return code;
    }
}