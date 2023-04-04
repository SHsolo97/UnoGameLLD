package org.example.model;

import org.example.constants.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
    List<Card> drawPile;
    List<Card> discardPile;

    public Deck(){
        this.drawPile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        for(int index=1;index<=13;index++){
            for(Suit suit:Suit.values()){
                drawPile.add(new Card(index, suit));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(drawPile);
    }
    public void addFirstCardToDiscardPile(){

        this.discardPile.add(drawPile.remove(drawPile.size()-1));
    }

    public int getCardsLeftInDrawPile(){
        return drawPile.size();
    }
    public Card getTopCardOnDiscardPile(){
        return discardPile.get(discardPile.size()-1);
    }

    public void addToDiscardPile(Card card){
        discardPile.add(card);
    }

    public Card dealCard(){
        int topIndex = drawPile.size()-1;
        Card removedCard = drawPile.remove(topIndex);
        return removedCard;
    }

    public Hand dealHand(){
        Hand hand = new Hand();
        for(int i=0;i<5;i++){
            Card drawnCard = dealCard();
            hand.cards.add(drawnCard);
        }
        return hand;
    }


}
