package utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesController {

    private static final String DEFAULT_PROPERTIES_PATH = "src/test/resources/default.properties";

    public static Properties getDefaultStartUpParams() {
        return loadProperties(DEFAULT_PROPERTIES_PATH);
    }

    private static Properties loadProperties(String path) {
        log.info("Reading properties: " + path);
        Properties properties = new Properties();
        try {
            InputStream stream = new FileInputStream(path);
            properties.load(stream);
        } catch (IOException ioException) {
            throw new IllegalStateException("Failed to load configuration file", ioException);
        }
        return properties;
    }

}
