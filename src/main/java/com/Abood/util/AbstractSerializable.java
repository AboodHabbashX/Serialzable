package com.Abood.util;


import com.fasterxml.jackson.xml.XmlMapper;
import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public abstract class AbstractSerializable {

    protected Gson gson =new Gson();
    protected XmlMapper mapper =new XmlMapper();

    @Contract(pure = true)
    public abstract boolean annotatedElement(Field element);

    @Contract(pure = true)
    protected abstract String getElementTag(Field field);

    @Contract(pure = true)
    protected String getClassTag(Class<?> clazz){
        return null;
    }


}
