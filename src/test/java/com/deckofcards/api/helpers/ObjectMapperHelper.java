package com.deckofcards.api.helpers;

import com.deckofcards.api.pojo.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperHelper {

    private static ObjectMapperHelper instance;
    private ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperHelper() {}

    public static ObjectMapperHelper getInstance() {
        if(instance == null) {
            instance = new ObjectMapperHelper();
        }
        return instance;
    }

    public Deck mapDeck(String content) throws JsonProcessingException {
        return objectMapper.readValue(content, Deck.class);
    }
}
