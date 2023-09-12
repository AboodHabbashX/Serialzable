package com.Abood.Object;

import com.Abood.annotation.XMLElement;
import com.Abood.annotation.XMLSerializable;
import com.Abood.util.AbstractSerializable;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.lang.reflect.Field;

@SuppressWarnings({"unused","unchecked"})

public class XML extends AbstractSerializable  {

    private Writer writer;
    private Reader reader;

    public XML(Writer writer , Reader reader){
        this.writer=writer;
        this.reader=reader;
    }
    public XML(Writer writer){
        this.writer=writer;

    }
    public XML(Reader reader){
        this.reader=reader;
    }
    public XML() {

    }
    public String objectAsXML(Object object){
        Class<?> clazz = object.getClass();
        StringBuilder str =new StringBuilder();

        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(XMLElement.class)) {


                    str.append("\t<").append(getElementTag(field)).append(">")
                            .append(field.get(object).toString())
                            .append("</").append(getElementTag(field)).append(">\n");

                } else {
                    throw new IllegalArgumentException(field.getName() +"not present ");
                }

            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        String classTag = getClassTag(clazz);
        return "<"+classTag+">\n"+str+"</" +classTag+">";

    }

    public <T> T xmlAsObject(String xml , Class<T> clazz) throws IOException {
            return mapper.readValue(xml,clazz);
    }
    @Override
    public boolean annotatedElement(Field element) {
        return element.isAnnotationPresent(XMLElement.class);
    }

    @Override
    @Contract(value = "null -> fail")
    protected String getElementTag(Field field) {
        return field.getAnnotation(XMLElement.class).tag();
    }

    @Override
    @Contract(pure = true)
    protected String getClassTag(Class<?> clazz) {
        return clazz.getAnnotation(XMLSerializable.class).tag();
    }

    public Reader getReader() {
        return reader;
    }

    public Writer getWriter() {
        return writer;
    }
}
