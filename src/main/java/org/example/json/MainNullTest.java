package org.example.json;

import ch.qos.logback.classic.joran.JoranConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainNullTest {
    private static final Logger log = LoggerFactory.getLogger(MainNullTest.class);
    private static String propsPath;

    public static void main(String[] args) throws IOException {
        //propsPath = "testLocation";
        System.out.println("START");

        /*List<String> obj1 = null;

        for(String counter : obj1) {
            System.out.println("Checking Null Pointer Exception");
        }*/


        StringBuilder sb = new StringBuilder();
        sb.append("test1");
        sb.append(" test2");
        sb.append(" test3");

        System.out.println(String.format("PRINT: %s", sb));
        log.debug(String.format("PRINT: %s", sb));

        System.out.println("STOP");

        initialise("testyLoc");
        initLogger();
    }

    public static void initialise(String propPath) {
        propsPath = propPath;

        JoranConfigurator configurator = new JoranConfigurator();


    }

    private static void initLogger() {

        log.debug(String.format("Logging to initialize from logback.xml file in %s", propsPath));
    }
}
