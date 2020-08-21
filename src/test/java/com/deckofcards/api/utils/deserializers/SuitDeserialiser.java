package com.deckofcards.api.utils.deserializers;

import com.deckofcards.api.pojo.Suit;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SuitDeserialiser extends JsonDeserializer<Suit> {
    @Override
    public Suit deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Data data = p.readValueAs(Data.class);
        Suit enumValue;
        if (data != null && data.stringValue != null) {
            //TODO: do not catch here, catch in test and report as an issue
            try {
                enumValue = Suit.valueOf(data.stringValue);
            } catch(IllegalArgumentException iae) {
                enumValue = null;
            }
        } else {
            enumValue = null;
        }
        return enumValue;
    }

    private static class Data {
        public String stringValue;

        public Data(String stringValue) {
            this.stringValue = stringValue;
        }
    }

}
