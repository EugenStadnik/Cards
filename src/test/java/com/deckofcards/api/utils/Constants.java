package com.deckofcards.api.utils;

import com.deckofcards.api.helpers.PropertiesHelper;

public class Constants {

    private static final PropertiesHelper PROPERTIES_HELPER = PropertiesHelper.getInstance();

    public static final String DEFAULT_PROPERTY_FILE_PATH = "src/test/resources/properties/default.properties";
    public static final String PROPERTIES_VM_OPTION_NAME = "config";
    public static final String LOGGER_PROPERTY_NAME = "loggerProperties";
    public static final String SHUFFLED_SCRIPT_PARAM = "SHUFFLED";
    public static final String ALL_SCRIPT_PARAM = "ALL";
    public static final String BOTTOM_SCRIPT_PARAM = "bottom";

    public static final String BASE_API_URL = PROPERTIES_HELPER.getProperty("baseApiUrl");
    public static final String BASE_IMAGES_URL = PROPERTIES_HELPER.getProperty("baseImagesUrl");
    public static final String CREATE_PATH = PROPERTIES_HELPER.getProperty("createPath");
    public static final String SHUFFLE_PATH = PROPERTIES_HELPER.getProperty("shufflePath");
    public static final String DRAW_PATH = PROPERTIES_HELPER.getProperty("drawPath");
    public static final String BOTTOM_PATH = PROPERTIES_HELPER.getProperty("bottomPath");
    public static final String DECKS_COUNT_PARAM = PROPERTIES_HELPER.getProperty("decksCountParam");
    public static final String CARDS_COUNT_PARAM = PROPERTIES_HELPER.getProperty("cardsCountParam");
    public static final String SPECIFIC_CARDS_PARAM = PROPERTIES_HELPER.getProperty("specificCardsParam");
    public static final Integer FULL_DECK_VOLUME = Integer.parseInt(PROPERTIES_HELPER.getProperty("fullDeckVolume"));

    public static final String PNG_EXTENSION = ".png";
    public static final String SVG_EXTENSION = ".svg";
    public static final String SPECIFIC_CARDS_DELIMITER = ",";
    public static final String EMPTY_STRING = "";
}
