package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }


    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getPlayableCards(Card topCard){
        List<Card> optionCards = new ArrayList<>();
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getValue()>10 || cards.get(i).getValue()==1) {
                if (cards.get(i).getSuit() == topCard.getSuit()) {
                    optionCards.add(cards.get(i));
                }
            }else if(cards.get(i).getValue() == topCard.getValue() || cards.get(i).getSuit() == topCard.getSuit()) {
                optionCards.add(cards.get(i));
            }
        }
        return optionCards;
    }

    public void dropCard(Card chosenCard){
        cards.remove(chosenCard);
    }

    public void drawCard(Card card){
        cards.add(card);
    }
}
