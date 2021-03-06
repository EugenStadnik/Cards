package com.deckofcards.api.features;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"}
        , glue = {"com.deckofcards.api"}
        , plugin = {"html:src/test/resources/reports/report.html"}
        )
public class DeckOfCardsApiTests { }
