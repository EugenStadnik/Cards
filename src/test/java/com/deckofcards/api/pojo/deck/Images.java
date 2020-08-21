package com.deckofcards.api.pojo.deck;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Images {

    @JsonProperty("svg")
    private URL svg;
    @JsonProperty("png")
    private URL png;

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
