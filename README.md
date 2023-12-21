# BlackJackV2 - OOP Project-International University-VNU-HCM
# Blackjack

This is a client-server multiplayer blackjack card game. Players can join new tables created by the server through the client, which serves as the house. Each player has a starting bankroll and is required to wager a minimum amount each round. After every round, players have the option to leave, or they will be eliminated if they do not have enough money to put the minimum wager. Dealer strikes with a soft 17. Standard Blackjack rules apply to all other regulations.

## How to Play

Here are the guidelines for playing blackjack. It can be played either way: multiplayer by using the server address option below to run the server on one computer and clients on other computers, or singleplayer by running the server and client on the same machine.

## Goal

Have a hand value closer to 21 than the dealer's hand without exceeding 21.

# CARD VALUES
Face cards (King, Queen, Jack) are each worth 10 points.

Aces can be worth either 1 or 11 points, depending on which value benefits the hand more.

## THE DEAL

You are dealt two cards each, and the dealer receives one card face up and one face down (hole card).

## TURN

You can choose to "hit" (take another card) or "stand" (keep your current hand).

You can continue to hit until you decide to stand, or until your hand exceeds 21, resulting in a bust.

## WINNING & LOSING:
You win if your hand is closer to 21 than the dealer's hand without busting.

If you bust (exceeds 21), the dealer wins regardless of the dealer's hand.

If the dealer busts, you win.

If both the player (you) and dealer have the same hand value, it's a push (a tie), and your bet is returned.

## BLACKJACK

If the player (you) or dealer is dealt 21 from the start (Ace & a 10-value card), the player/dealer gets a blackjack.

## DIRECTORIES

```src```: All of the Java source files, or classes, listed below are contained in this directory.


```images```: The photos in this directory will be shown to the player to improve gameplay.

```sounds```: The sounds in this directory will improve user experience and provide a feeling of a casino.

### Prerequisites

* Apache NetBeans 19

### Running

Navigate to the directory where the JAR files are located and type the following commands to launch the server and client. Before the client, the server needs to be started.

```
java -jar BlackjackServer.jar

java -jar BlackjackClient.jar
```
### Options

The following is a list of options that can be used when running the server and client. To operate the server or client, none of the choices are necessary.

## CLASSES AND DESCRIPTIONS

```Deck.java```->Deck Class: is a Java implementation that represents a standard deck of playing cards.

It contains a private ArrayList named deck, which stores instances of a Card class. 

The constructor initializes the deck by populating it with 52 cards, organized into four suits and thirteen ranks.

Special considerations are made for aces, assigned a value of 11, and face cards (Jack, Queen, King), each assigned a value of 10. 

The class provides methods for shuffling the deck (shuffle deck), retrieving a specific card at an index (getCard), and removing a card from the deck at a specified index (removeCard). 





