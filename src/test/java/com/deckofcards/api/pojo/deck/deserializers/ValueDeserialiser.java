package com.deckofcards.api.pojo.deck.deserializers;

import com.deckofcards.api.pojo.deck.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ValueDeserialiser extends JsonDeserializer<Value> {
    @Override
    public Value deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Data data = p.readValueAs(Data.class);
        Value enumValue;
        if (data != null && data.stringValue != null) {
            try {
                enumValue = Value.getValueOf(data.stringValue);
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
