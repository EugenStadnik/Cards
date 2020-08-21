package com.deckofcards.api.helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.deckofcards.api.utils.Constants.*;

public class PropertiesHelper {

    private static PropertiesHelper instance;
    private static Properties properties;

    private PropertiesHelper() throws IOException {
        String configArg = System.getProperty(PROPERTIES_VM_OPTION_NAME) != null
                ? System.getProperty(PROPERTIES_VM_OPTION_NAME)
                : DEFAULT_PROPERTY_FILE_PATH;
        FileReader reader = new FileReader(configArg);
        properties = new Properties();
        properties.load(reader);
    }

    public static PropertiesHelper getInstance() {
        if(instance == null || properties == null) {
            try {
                instance = new PropertiesHelper();
            } catch (IOException ioException) {
                throw new RuntimeException("Unable to evaluate " + System.getProperty(PROPERTIES_VM_OPTION_NAME) + " property file.");
            }
        }
        return instance;
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

}
