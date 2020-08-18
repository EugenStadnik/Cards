package com.deckofcards.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Pojo {

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
