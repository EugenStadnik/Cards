package com.deckofcards.api.helpers;

import com.deckofcards.api.pojo.deck.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperHelper {

    private static ObjectMapperHelper instance;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperHelper() {}

    public static ObjectMapperHelper getInstance() {
        if(instance == null) {
            instance = new ObjectMapperHelper();
        }
        return instance;
    }

    public Deck mapDeck(String content) {
        try {
            return objectMapper.readValue(content, Deck.class);
        } catch(JsonProcessingException jpre) {
            throw new AssertionError("Invalid or unparsable format data is stored in source." +
                    "\nSource: " + content);
        }
    }
}
