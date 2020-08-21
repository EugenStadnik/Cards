package com.deckofcards.api.features.factories;

import com.deckofcards.api.helpers.PropertiesHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static com.deckofcards.api.utils.Constants.*;

public class LoggerFactory {

    private static final PropertiesHelper PROPERTIES_HELPER = PropertiesHelper.getInstance();
    private static boolean isConfigured;

    private LoggerFactory() { }

    public static Logger getLogger(Class aClass) {
        if(isConfigured) {
            return LogManager.getLogger(aClass);
        }
        PropertyConfigurator.configure(PROPERTIES_HELPER.getProperty(LOGGER_PROPERTY_NAME));
        isConfigured = true;
        return LogManager.getLogger(aClass);
    }
}
