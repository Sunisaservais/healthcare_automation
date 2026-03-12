
package com.healthcare.utilities;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties = new Properties();
    private static Dotenv dotenv = Dotenv.load();

    static {

        try {
            FileInputStream file = new FileInputStream("Configuration.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);
        if (value != null && value.startsWith("${") && value.endsWith("}")) {
            String envKey = value.substring(2, value.length() - 1);
            return dotenv.get(envKey);
        }
        return value;
    }
}

