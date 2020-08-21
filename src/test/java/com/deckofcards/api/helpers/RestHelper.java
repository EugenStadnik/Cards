package com.deckofcards.api.helpers;

import com.deckofcards.api.features.factories.LoggerFactory;
import com.deckofcards.api.utils.JsonPretifier;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONException;

import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class RestHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestHelper.class);
    private static RestHelper instance;

    private RestHelper() {}

    public static RestHelper getInstance() {
        if(instance == null) {
            instance = new RestHelper();
        }
        return instance;
    }

    public<T> Response sendRequest(Method method, URL baseUrl, Map<String, ?> params, Map<String, ?> headers, T body) {
        return given().params(params).headers(headers).body(body)
                .when().request(method, baseUrl);
    }

    public Response sendGETRequest(RequestSpecification spec) {
        FilterableRequestSpecification httpRequest = (FilterableRequestSpecification)spec;
        LOGGER.info("Request: " + Method.GET + " - " + httpRequest.getURI());
        Response response = given(spec).when().get();
        String pretypiedResponse;
        try {
            pretypiedResponse = JsonPretifier.pretify(response.asString());
        } catch(JSONException je) {
            pretypiedResponse = response.asString();
        }
        LOGGER.info("Retrieved response:\n"
                + response.statusLine() + "\n" + pretypiedResponse);
        return response;
    }

}
