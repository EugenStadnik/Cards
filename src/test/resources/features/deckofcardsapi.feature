Feature: Verification of deckofcards API

  As a user
  I want to draw a deck of card in many ways
  So I could play different card games

  Scenario Outline: Check if card count in deck is correct after drawing X cards from it
    Given the 1 sets of 'ALL' 'SHUFFLED' cards in the deck
    When '<drawnAmount>' cards are drawn from the 'top' of the deck
    Then <remainingCards> cards remains in deck

    Examples:
      | drawnAmount | remainingCards |
      | 0           | 52             |
      | 1           | 51             |
      | 52          | 0              |

  Scenario: Create a new deck containing only cards of specific names
  and validate that player can only get these cards from it
    Given the 1 sets of 'ACE' 'UNSHUFFLED' cards in the deck
    When 'ALL' cards are drawn from the 'top' of the deck
    Then the player have gotten only available cards from the deck

  Scenario: Draw 5 specific cards from a bottom of the deck
  and check that card amount changed and drawn cards are no longer in the deck
    Given the 1 sets of '11' 'SHUFFLED' cards in the deck
    When '5' cards are drawn from the 'bottom' of the deck
    Then the player have gotten only available cards from the deck
    And 6 cards remains in deck
    And drawn cards are no longer in the deck
