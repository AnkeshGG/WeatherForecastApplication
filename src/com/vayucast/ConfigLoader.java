package com.vayucast;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("config.properties file not found in resources!");
            }
        } catch (Exception e) {
            System.err.println("Error loading API key: " + e.getMessage());
        }
    }

    public static String getApiKey() {
        return properties.getProperty("api_key", "");
    }
}
