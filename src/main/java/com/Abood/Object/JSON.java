package com.Abood.Object;

import com.Abood.annotation.JSONElement;
import com.Abood.util.AbstractSerializable;
import org.jetbrains.annotations.Contract;

import java.lang.reflect.Field;



@SuppressWarnings("unused")
public class JSON extends AbstractSerializable {

    private String file;
    private Writer writer;
    private Reader reader;

    public JSON(Writer writer) {
        this.writer=writer;
    }
   public JSON(Reader reader){
        this.reader=reader;
   }
   public JSON(){

   }
    @Contract("null -> fail")
    public String objectAsJson(Object object) {
        if (object ==null) throw new AssertionError("object is null !");

        StringBuilder builder =new StringBuilder();

        try {
            Field[] fields = object.getClass().getDeclaredFields();
            int counter = fields.length-1;
            for (Field f : fields) {
                if (f.isAnnotationPresent(JSONElement.class)) { // Works With @JSONElement
                    f.setAccessible(true);
                    if (counter < fields.length) {

                        builder.append("\"").append(getElementTag(f)).append("\": ")
                                .append(f.get(object).toString()).append(",");
                    } else {
                        builder.append("\"").append(getElementTag(f)).append("\": ")
                                .append(f.get(object).toString());
                    }
                    counter++;
                } else {
                    throw new IllegalArgumentException(f.getName() +"not present");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "{"+builder+"}";
    }

    public <T> T jsonAsObject(String jsonString ,Class<T>clazz ) {
        return gson.fromJson(jsonString ,clazz);
    }

    @Override
    public boolean annotatedElement(Field element) {
        return element.isAnnotationPresent(JSONElement.class);
    }

    @Override
    protected String getElementTag(Field field) {
        return field.getAnnotation(JSONElement.class).tag();
    }


    public void setFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public Reader getReader() {
        return reader;
    }

    public Writer getWriter() {
        return writer;
    }
}
