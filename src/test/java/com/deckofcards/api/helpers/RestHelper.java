package com.deckofcards.api.helpers;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class RestHelper {

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
        return given(spec)
                .when().get();
    }

    public Response sendPOSTRequest(RequestSpecification spec) {
        return given(spec)
                .when().post();
    }

    public Response sendPUTRequest(RequestSpecification spec) {
        return given(spec)
                .when().put();
    }

    public Response sendDELETERequest(RequestSpecification spec) {
        return given(spec)
                .when().delete();
    }

}
