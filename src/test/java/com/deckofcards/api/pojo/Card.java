package com.deckofcards.api.pojo;

import com.deckofcards.api.utils.deserializers.SuitDeserialiser;
import com.deckofcards.api.utils.deserializers.ValueDeserialiser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.net.URL;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card extends Pojo {

    @JsonProperty("image")
    private URL image;
    @JsonDeserialize(using = ValueDeserialiser.class)
    @JsonProperty(value = "value", required = true)
    private Value value;
    @JsonDeserialize(using = SuitDeserialiser.class)
    @JsonProperty(value = "suit", required = true)
    private Suit suit;
    @JsonProperty("images")
    private Images images;
    @JsonProperty(value = "code", required = true)
    private String code;

    public Card() {}

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
        this.code = value.getShortName() + suit.getShortName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Card) {
            Card other = (Card)o;
            if (this.code.equals(other.code)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @JsonProperty("image")
    public URL getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(URL image) {
        this.image = image;
    }

    @JsonProperty(value = "value", required = true)
    public Value getValue() {
        return value;
    }

    @JsonProperty(value = "value", required = true)
    public void setValue(Value value) {
        this.value = value;
    }

    @JsonProperty(value = "suit", required = true)
    public Suit getSuit() {
        return suit;
    }

    @JsonProperty(value = "suit", required = true)
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @JsonProperty("images")
    public Images getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(Images images) {
        this.images = images;
    }

    @JsonProperty(value = "code", required = true)
    public String getCode() {
        return code;
    }

    @JsonProperty(value = "code", required = true)
    public void setCode(String code) {
        this.code = code;
    }

}
