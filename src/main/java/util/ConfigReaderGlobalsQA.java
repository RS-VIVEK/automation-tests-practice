package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderGlobalsQA {

    private Properties properties;

    public ConfigReaderGlobalsQA() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config2.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}

