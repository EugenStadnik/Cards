package com.deckofcards.api.utils.factories;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static com.deckofcards.api.utils.Constants.*;

public class LoggerFactory {

    private static boolean isConfigured;

    private LoggerFactory() {}

    public static Logger getLogger(Class aClass) {
        if(isConfigured) {
            return LogManager.getLogger(aClass);
        }
        PropertyConfigurator.configure(LOG_PROPERTIES);
        isConfigured = true;
        return LogManager.getLogger(aClass);
    }
}
