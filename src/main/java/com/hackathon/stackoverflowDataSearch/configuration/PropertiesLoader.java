package com.hackathon.stackoverflowDataSearch.configuration;

import com.hackathon.stackoverflowDataSearch.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    static private PropertiesLoader propertiesLoader;
    Properties prop;

    private PropertiesLoader() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(Constants.CONFIG_FILE);
            prop = new Properties();
            prop.load(input);
        } catch (NullPointerException | IOException e) {
//            logger.error(e);
//            throw new InvalidFileException(Constants.INVALID_FILE);
        }
    }

    public static PropertiesLoader getInstance() {
        if (propertiesLoader == null) {
            propertiesLoader = new PropertiesLoader();
        }
        return propertiesLoader;
    }

    public String getValue(String key) {
        return this.prop.getProperty(key);
    }


}


