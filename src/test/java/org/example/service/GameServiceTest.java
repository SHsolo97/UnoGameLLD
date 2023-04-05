package org.example.service;

import org.example.constants.Suit;
import org.example.model.Card;
import org.example.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    GameService gameService;
    List<String> playerNames;

    void inputPlayerNames(){
        this.playerNames = new ArrayList<>();
        playerNames.add("Suhail");
        playerNames.add("Sahiba");
        playerNames.add("Ramesh");
        playerNames.add("Sahil");
    }
    public GameServiceTest(){
        inputPlayerNames();
        this.gameService = new GameService(this.playerNames);
    }
    @Test
    void checkConstructorIntegrity() {
        assertEquals(gameService.deck.getCardsLeftInDrawPile(), 52-(4*5+1));
    }

    @Test
    void testSkip() {
        int currentTurn = gameService.playerTurn;
        Card aceCard = new Card(1, Suit.Club);
        // Testing Skip functionality
        gameService.actionNextTurn(aceCard);
        assertEquals(currentTurn+2, gameService.playerTurn);
    }

    @Test
    void testDrawTwo(){
        int currentTurn = gameService.playerTurn;
        int nextPlayerCardCountBefore = gameService.players.get(currentTurn+1).getHand().getCards().size();
        Card queenCard = new Card(12, Suit.Club);
        // Testing Draw 2 functionality
        gameService.actionNextTurn(queenCard);
        int nextPlayerCardCountAfter = gameService.players.get(currentTurn+1).getHand().getCards().size();
        assertEquals(nextPlayerCardCountBefore+2, nextPlayerCardCountAfter );
    }

    @Test
    void testDrawFour(){
        int currentTurn = gameService.playerTurn;
        int nextPlayerCardCountBefore = gameService.players.get(currentTurn+1).getHand().getCards().size();
        Card jackCard = new Card(11, Suit.Club);
        // Testing Draw 4 functionality
        gameService.actionNextTurn(jackCard);
        int nextPlayerCardCountAfter = gameService.players.get(currentTurn+1).getHand().getCards().size();
        assertEquals(nextPlayerCardCountBefore+4, nextPlayerCardCountAfter );
    }

    @Test
    void testReverse(){

        Card jackCard = new Card(13, Suit.Club);
        // Testing Reverse functionality
        Player nextPlayer = gameService.players.get(playerNames.size()-1);
        gameService.actionNextTurn(jackCard);
        int currentTurn = gameService.playerTurn;
        Player currentPlayer = gameService.players.get(currentTurn);
        assertEquals(nextPlayer, currentPlayer );
    }

    @Test
    void nextTurn() {
        int currentTurn = gameService.playerTurn;
        gameService.nextTurn();
        assertEquals(currentTurn+1, gameService.playerTurn);
    }
}