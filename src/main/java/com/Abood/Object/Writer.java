package com.Abood.Object;

import java.io.PrintWriter;

record Writer(String file) {
    public void writeInFile(String jsonObject) {
        try {
            PrintWriter writer = new PrintWriter(file);
            JSON json =new JSON();

            writer.println(json.objectAsJson(jsonObject));
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
