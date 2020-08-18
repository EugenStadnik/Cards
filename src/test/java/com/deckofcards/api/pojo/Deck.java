package com.deckofcards.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Deck extends Pojo {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("cards")
    private List<Card> cards;
    @JsonProperty(value = "deck_id", required = true)
    private String deck_id;
    @JsonProperty("shuffled")
    private Boolean shuffled;
    @JsonProperty("remaining")
    private Integer remaining;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("cards")
    public List<Card> getCards() {
        return cards;
    }

    @JsonProperty("cards")
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @JsonProperty(value = "deck_id", required = true)
    public String getDeck_id() {
        return deck_id;
    }

    @JsonProperty(value = "deck_id", required = true)
    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    @JsonProperty("shuffled")
    public Boolean getShuffled() {
        return shuffled;
    }

    @JsonProperty("shuffled")
    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }

    @JsonProperty("remaining")
    public Integer getRemaining() {
        return remaining;
    }

    @JsonProperty("remaining")
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
