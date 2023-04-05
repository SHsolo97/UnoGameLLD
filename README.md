# Uno Game using playing cards LLD in Java

## Problem Statement:

Design a multiplayer card game that supports multiple players (up to 4) and different types of cards (e.g. number cards, action cards, etc.). The game should follow the following rules:

Each player starts with a hand of 5 cards.

The game starts with a deck of 52 cards ( a standard deck of playing cards).

Players take turns playing cards from their hand, following a set of rules that define what cards can be played when.

A player can only play a card if it matches either the suit or the rank of the top card on the discard pile.

If a player cannot play a card, they must draw a card from the draw pile. If the draw pile is empty, the game ends in a draw and no player is declared a winner..

The game ends when one player runs out of cardswho is declared the winner.

BONUS: Aces, Kings, Queens and Jack are action cards. When one of these is played the following actions occur:

1. Ace(A): Skip the next player in turn

2. Kings(K): Reverse the sequence of who plays next

3. Queens(Q): +2

4. Jacks(J): +4

NOTE: actions are not stackable i.e. if Q is played by player 1 then player two draws two cards and cannot play a Q from his hand on that turn even if available

## Features

All features in the problem statement have been implemented including the BONUS functionality.

## Assumptions

1. If a player does not have any playable cards during his turns he/she draws a card. If he/she still does not have playable card the turn is passed to the next player.I 

2. Action cards are not stackable (ie we cannot play the same action card of a different suit).

3. A card is placed in the discard pile at the start of the game before the first player gets a turn.

## How to run the game

1. Clone the repository.

2. Download the dependencies and run the app.

