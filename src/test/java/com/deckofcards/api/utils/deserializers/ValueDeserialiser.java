package com.deckofcards.api.utils.deserializers;

import com.deckofcards.api.pojo.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ValueDeserialiser extends JsonDeserializer<Value> {
    @Override
    public Value deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Data data = p.readValueAs(Data.class);
        Value enumValues;
        if (data != null && data.stringValue != null) {
            //TODO: do not catch here, catch in test and report as an issue
            try {
                enumValues = Value.getValueOf(data.stringValue);
            } catch(IllegalArgumentException iae) {
                enumValues = null;
            }
        } else {
            enumValues = null;
        }
        return enumValues;
    }

    private static class Data {
        public String stringValue;

        public Data(String stringValue) {
            this.stringValue = stringValue;
        }
    }

}
