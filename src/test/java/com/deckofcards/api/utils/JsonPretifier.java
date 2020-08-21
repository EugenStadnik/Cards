package com.deckofcards.api.utils;

import org.json.JSONObject;

public class JsonPretifier {

    public static String pretify(Object o) {
        return new JSONObject(o).toString(3);
    }

    public static String pretify(String s) {
        return new JSONObject(s).toString(3);
    }
}
