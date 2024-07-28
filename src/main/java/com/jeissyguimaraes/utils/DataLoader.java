package com.jeissyguimaraes.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataLoader {

    private Properties properties;

    public DataLoader(String fileName) {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + fileName);
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets a property value by key.
     *
     * @param key the key to look for.
     * @return the value associated with the key.
     */
    public String getProperty(final String key) {
        return properties.getProperty(key);
    }
}
