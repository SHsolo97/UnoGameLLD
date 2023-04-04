package org.example.model;


import org.example.constants.Suit;

public class Card {
    int value;
    Suit suit;
    public Card(Integer value, Suit suit){
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    String getIdentity(int value){
        String cardIdentity = "";
        switch (value) {
            case 1:
                cardIdentity = "Ace";
                break;
            case 10:
                cardIdentity = "10";
                break;
            case 11:
                cardIdentity = "Jack";
                break;
            case 12:
                cardIdentity = "Queen";
                break;
            case 13:
                cardIdentity = "King";
                break;
            default:
                cardIdentity = cardIdentity + (char)('0'+value);
                break;
        }
        return cardIdentity;
    }
    public String getDescription(){
        String cardDescription = getIdentity(value) +" of "+ suit;
        return cardDescription;
    }
}
