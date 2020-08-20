package com.deckofcards.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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
    @JsonProperty("error")
    private String error;

    public Deck() {}

    public Deck(Deck other) {
        this.success = other.getSuccess();
        this.cards = new ArrayList<>(other.getCards());
        this.deck_id = other.getDeck_id();
        this.shuffled = other.getShuffled();
        this.remaining = other.getRemaining();
        this.error = other.getError();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deck) {
            Deck other = (Deck)o;
            if (this.deck_id.equals(other.deck_id)
                    && this.shuffled.equals(other.shuffled)
                    && this.success.equals(other.success)) {
                if(this.cards == null && other.cards == null) {
                    return true;
                }
                if(this.cards == null || other.cards == null
                        || this.cards.isEmpty() && !other.cards.isEmpty()
                        || !this.cards.isEmpty() && other.cards.isEmpty()
                        || this.cards.size() != other.cards.size()
                        || !this.cards.containsAll(other.cards)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.deck_id.hashCode();
    }

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

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }
}
