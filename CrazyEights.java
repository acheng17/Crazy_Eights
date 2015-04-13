import java.util.ArrayList;
import java.util.Scanner;

public class CrazyEights
{
  //Instance variables
  ArrayList<Card> deck = new ArrayList<Card>();
  ArrayList<Card> shuffled = new ArrayList<Card>();
  ArrayList<Card> pile = new ArrayList<Card>();
  ArrayList<Card> playerHand = new ArrayList<Card>();
  ArrayList<Card> computerHand = new ArrayList<Card>();
  boolean gameOver = false;
  Scanner kb = new Scanner(System.in);
  Scanner kb2 = new Scanner(System.in);
  
  //Constructor
  public CrazyEights()
  {
    // fill the deck with cards with the value and suit. 
    
    for (int i = 1; i <= 13; i++)
    {
      deck.add(new Card(i,"Club"));
      deck.add(new Card(i,"Diamond"));
      deck.add(new Card(i,"Heart"));
      deck.add(new Card(i,"Spade"));
    }
  }
  
  //Methods
  
  public void play()
  {
    //actual gameplay 
    shuffle();
    display();
    deal();
    System.out.println();
    System.out.println();
    System.out.println("To play Crazy Eights, you put down a card from your hand that matches the face-up card either by suit or by value.");
    while (gameOver == false)
    { //only keeps going if the game isn't over
      System.out.println();
      System.out.println();
      System.out.println("Your hand is:");
      displayPlayerHand(); 
      System.out.println(); //blanks are there to give space in between lines of words
      System.out.println("The face-up card is: " + deck.get(deck.size()-1));
      System.out.println();
      System.out.println("Would you like to: (P)lay a card, or (T)ake a card?");
      String response = kb.nextLine();
      //prints out deck, player's hand, and the face-up card. 
      
      while (verifyString(response) == false)
      { //bounces question back if player inputs something not a variation of play or take
         System.out.println("Please enter a valid response. Would you like to: (P)lay a card, or (T)ake a card?");
         response = kb.nextLine();
      }
      if (response.equals("p") || response.equals("P") || response.equals("Play") || response.equals("play"))
      { //player turn 
        int choose = 0;
        //Asks what the player wants to put down and takes out of their deck and adds the card to the deck.
        System.out.println("What would you like to put down? Put down the corresponding number of the card.");
        choose = kb2.nextInt();
        Card temp = playerHand.get(choose-1);
        while (isValid(temp) == false)
         {  //bounces back the question if the player tries to put down a card that is not valid according to the game
          System.out.println("That is not a valid card. What would you like to put down? Put down the corresponding number of the card.");
          choose = kb2.nextInt();
          temp = playerHand.get(choose-1);
        }
          
          System.out.println("Card to be removed is " + temp);
        deck.add(playerHand.remove(choose-1));
      }
      
      else 
      { //takes a card from the deck and adds it to the player's hand
        System.out.println("Deck.size() = " + deck.size());
        System.out.println("The card you got is a " + deck.get(0)); //prints out which card you gained
        playerHand.add(deck.remove(0));
      }
      
      // Computer turn

      if (checkForMatch() == false) 
      {
        //if false, it adds a deck to computer hand and removes the corresponding card from the deck 
        System.out.println("The card the computer got is a " + deck.get(0));
        computerHand.add(deck.remove(0)); //prints out which card the computer gained 
      }
    }
    
    if (playerHand.size() == 0)
    { //once te game is done, it prints out who won 
        System.out.println("You win!");
        System.out.println("You get a million fireworks! ??? You get Godiva chocolate! Yay!"); //you get some nice stuff
    }  
    else System.out.println("You lose...");
  }
  
  private boolean verifyString(String str) 
  { //makes sure what the player inputs when asked if they want to play or take, is a variant of play or take.
    String valid = "pPplayPlaytTtakeTake";
    return (valid.indexOf(str) != -1);
  }
  private boolean checkForMatch()
  { //ititerates through the whole hand of the computer to check if there's a card that matches 
    //the top card either by suit or by value. if there is, takes the card out of the hand and adds it to the deck
    for (int i = 0; i < computerHand.size(); i++)
    {
      if (    (computerHand.get(i). getValue() == deck.get(deck.size()-1).getValue())
            || (computerHand.get(i). getSuit().equals(deck.get(deck.size()-1).getSuit()))  )
      {
        deck.add(computerHand.remove(i));
        System.out.println("The card the computer put down is" + deck.get(deck.size()-1));
        return true;
      }
    }
    return false; //if false, it removes a card instead 
  }
  
  private void shuffle()
  { // shuffles so the order of the cards are not in order. adds the cards in diff. order to another deck,
    //then swaps the whole content after finished to the original, now empty deck. 
    while (deck.size() > 0)
    {
      int rand = (int)(Math.random()*deck.size());
      shuffled.add(deck.remove(rand));
    }
    deck = shuffled;
  }
  
  public void deal()
  {
    // remove 8 cards from deck, and add it to humanHand
    //remove 8 more cards and add it to computerHand
    
    for (int i = 0; i < 8; i++)
    {
      playerHand.add(deck.remove(0));
      computerHand.add(deck.remove(0));
    }
  }
  
  public void display()
  { //prints out the deck on the interactions pane 
    for (Card c : deck)
    {
      System.out.print(c + " ");
    }
  }  
  
  public void displayPlayerHand()
  { //displays the contents of player's hand on the interactions pane
    int i = 0; 
    for (Card c : playerHand)
    { 
      i++; 
      System.out.print(i + ". " + c + " ");
    }
  }
  
  private boolean isValid(Card chosen)
   { // return true if suit or values match
    Card faceUp = deck.get(deck.size()-1);
    return (faceUp.getValue() == chosen.getValue() || faceUp.getSuit() == chosen.getSuit());
  }

  
  public boolean gameOver()
  { //ends the game if no more cards are left in either hand
    if ((playerHand.size() == 0) || computerHand.size() == 0)
    {
    gameOver = true;
    return gameOver;
    }
    
    else 
    {
      gameOver = false;
      return gameOver;
    }
  }
}