package org.example.service;

import org.example.model.Card;
import org.example.model.Deck;
import org.example.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameService {

    boolean isReverse;
    Deck deck;
    List<Player> players;
    int playerTurn;
    List<Card> userCards;
    List<Card> playableCards;

    public GameService(List<String> playerNames){
        this.deck = new Deck();
        this.deck.shuffle();
        this.deck.addFirstCardToDiscardPile();
        this.players = new ArrayList<>();
        for(String playerName:playerNames) {
            this.players.add(new Player(playerName, deck.dealHand()));
        }
        this.isReverse = false;
        this.playerTurn = 0;
    }

    private void listCards(List<Card> cards){
        for(int i=0;i<cards.size();i++){
            System.out.print((i+1)+". "+cards.get(i).getDescription()+" ");
        }
        System.out.println();
    }

    private boolean isActionCard(Card card){
        return (card.getValue()>10 || card.getValue()==1);
    }
    private void reverse(){
        this.isReverse = !this.isReverse;
    }

    private void drawACard(){
        if(deck.getCardsLeftInDrawPile()<=0){
            System.out.println("The game ends in a Draw as the draw pile is empty.");
            System.exit(0);
        }
        Card drawnCard = deck.dealCard();
        players.get(playerTurn).getHand().drawCard(drawnCard);
    }

    private void drawCards(int count){
        for(int i=0;i<count;i++){
            drawACard();
        }
    }
    private boolean checkPlayerWin(){
        for(Player player:players){
            if(player.getHand().getCards().size()==0){
                return true;
            }
        }
        return false;
    }

    private Player getWinner(){
        for(Player player:players){
            if(player.getHand().getCards().size()==0){
                return player;
            }
        }
        return null;
    }
    public void play(Scanner sc){
        while(true){
            // Here status 0 means current player is unable to play (ie does not have playable cards for the turn.)
            // status 1 means current player can play and is able to choose from the list of playable cards.
            // status 2 is to show that the game has ended in a draw.
            // status 3 is to show a player has won the game
            int gameStatus = showGameStatus();
            if(gameStatus == 0){

            }
            else if(gameStatus == 1){
                int playedCardPosition = sc.nextInt();
                playTurn(playedCardPosition);
            }else{
                break;
            }
        }
    }
    public int showGameStatus(){
        int gameStatus = 1;
        if(deck.getCardsLeftInDrawPile() == 0){
            System.out.println("The game ends in a Draw as the draw pile is empty.");
            gameStatus = 2;
            return gameStatus;
        }
        if(checkPlayerWin()){
            gameStatus = 3;
            Player winner = getWinner();
            System.out.println("The winner of the game is " + winner.getName());
            return gameStatus;
        }
        Card currentTopCard = deck.getTopCardOnDiscardPile();
        System.out.println(players.get(playerTurn).getName() + " Player " + (playerTurn+1) + " turn");
        System.out.println("Cards left in the draw pile : " + deck.getCardsLeftInDrawPile());
        System.out.println("Current card on top : " + currentTopCard.getDescription());
        Player currentPlayer = players.get(playerTurn);
        userCards = currentPlayer.getHand().getCards();
        playableCards = currentPlayer.getHand().getPlayableCards(currentTopCard);
        if(playableCards.size() == 0){
            System.out.println("Card has been added to hand from Draw Pile as there were no playable cards.");
            Card drawnCard = deck.dealCard();
            currentPlayer.getHand().drawCard(drawnCard);
            playableCards = currentPlayer.getHand().getPlayableCards(currentTopCard);
            if(playableCards.size()==0){
                System.out.println("No playable cards even after draw. Next player turn.");
                System.out.println();
                nextTurn();
                gameStatus = 0;
                return gameStatus;
            }
        }
        listCards(userCards);
        System.out.println("Choose playable card between [1 - " + playableCards.size() +"] :");
        listCards(playableCards);
        gameStatus = 1;
        return gameStatus;
    }
    public void playTurn(int playedCardPosition){
        Player currentPlayer = players.get(playerTurn);
        if(playedCardPosition <= 0 && playedCardPosition > playableCards.size()){
            System.out.println("Invalid Position.");
            return;
        }
        Card playedCard = playableCards.get(playedCardPosition-1);
        currentPlayer.getHand().dropCard(playedCard);
        deck.addToDiscardPile(playedCard);
        if(isActionCard(playedCard)){
            actionNextTurn(playedCard);
        }else{
            nextTurn();
        }
    }


    public void actionNextTurn(Card playedCard){
        int value = playedCard.getValue();
        if(value == 1){
            nextTurn();
            nextTurn();
        }else if(value == 11){
            nextTurn();
            drawCards(4);
        }else if(value == 12){
            nextTurn();
            drawCards(2);
        }else{
            reverse();
            nextTurn();
        }
    }
    public void nextTurn(){
        if(!isReverse){
            ++playerTurn;
            if(playerTurn>=players.size()){
                playerTurn = 0;
            }
        }else {
            --playerTurn;
            if(playerTurn<0){
                playerTurn = players.size()-1;
            }
        }
    }

}
