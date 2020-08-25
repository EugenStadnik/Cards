package com.deckofcards.api.pojo.deck;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.MalformedURLException;
import java.net.URL;

import static com.deckofcards.api.utils.Constants.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Images {

    @JsonProperty("svg")
    private URL svg;
    @JsonProperty("png")
    private URL png;

    public Images() {
    }

    public Images(String cardCode) throws MalformedURLException {
        this.svg = new URL(BASE_IMAGES_URL + cardCode + SVG_EXTENSION);
        this.png = new URL(BASE_IMAGES_URL + cardCode + PNG_EXTENSION);
    }

    @JsonProperty("svg")
    public URL getSvg() {
        return svg;
    }

    @JsonProperty("svg")
    public void setSvg(URL svg) {
        this.svg = svg;
    }

    @JsonProperty("png")
    public URL getPng() {
        return png;
    }

    @JsonProperty("png")
    public void setPng(URL png) {
        this.png = png;
    }
}
