package com.naamad.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;
    private static final Logger log = LoggerFactory.getLogger(ApplicationProperties.class);

    public ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

        } catch (IOException ioException) {
            log.info("IOException Occured while loading properties file: ", ioException);
        }
    }

    public String readProperty(String keyName){
        log.info("reading property: " + keyName);
        return properties.getProperty(keyName, "There is no such key in the properties file");
    }
}
