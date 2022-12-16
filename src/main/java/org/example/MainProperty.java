package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MainProperty {
    //https://stackoverflow.com/questions/40129474/folder-path-in-properties-file
    public static void main(String[] args) throws IOException {
        System.out.println("START Property");

        Properties p = new Properties();
        //p.load(new FileInputStream("C://Palmus-HACMS/PV/PVInfo.ini"));
        p.load(new FileInputStream("/Users/prakash/Desktop/ReadJsonFiles/src/main/resources/PVInfo.ini"));
        String pvId = p.getProperty("PV-ID");
        String palmusId = p.getProperty("PALMUS-ID");
        System.out.println(pvId);
        System.out.println(palmusId);

        System.out.println("END Property");

        //
        Properties props = new Properties();
        props.setProperty("test", "C:\\dev\\sdk\\test.dat");
        System.out.println(props.getProperty("test"));

        props.setProperty("test2", "//dev//sdk//test.dat");
        System.out.println(props.getProperty("test2"));
    }
}
