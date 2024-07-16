package com.jeissyguimaraes.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DataLoader {
    private Map<String, String> data;

    public DataLoader(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(new File("src/test/resources/data/" + fileName), new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data from JSON file: " + fileName, e);
        }
    }

    public String getProperty(String key) {
        return data.get(key);
    }
}
