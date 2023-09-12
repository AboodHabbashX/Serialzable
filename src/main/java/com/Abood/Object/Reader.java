package com.Abood.Object;

import java.io.File;
import java.util.Scanner;


public record Reader(String file) {

    public String readFile() {
        StringBuilder builder = new StringBuilder();
        try {
            Scanner scan = new Scanner(new File(file));

            while (scan.hasNextLine()) {
                builder.append(scan.nextLine()).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(builder);
    }
}
