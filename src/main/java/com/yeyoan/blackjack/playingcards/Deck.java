package com.yeyoan.blackjack.playingcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A deck of cards.
 * @author Joshua Abad
 */
public class Deck implements CardContainer {
    
    /**
     * Creates a new {@code Deck} with the standard 52 playing cards.
     * 
     * <p>It includes thirteen ranks in each of the four suits: clubs, diamonds,
     * hearts and spades. Each suit includes an ace, a king, queen and jack, and
     * ranks two through ten.
     */
    public Deck() {
        deck = new ArrayList<>(); 
        Stream.of(Suit.values()).forEach(suit -> {
            IntStream.range(2, 11).forEach(rank -> deck.add(new PipCard(rank, suit)));
            Stream.of(Face.values()).forEach(face -> deck.add(new FaceCard(face, suit)));
            deck.add(new Ace(suit));
        });
    }

    /**
     * Determines if there are no more cards left in this deck.
     * @return true if there are no more cards left in this deck
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public List<Card> getCards() {
        return deck;
    }

    /**
     * Returns a card drawn from this deck.
     * @return a card
     */
    @Override
    public Card drawCard() {
        int index = deck.size() - 1;
        if (!deck.isEmpty()) {
            Card pickedCard = deck.get(index);
            deck.remove(index);
            return pickedCard;
        }
        return null;
    }

    /**
     * Resets this deck with cards that were discarded.
     * @param discarded hand that will be added
     */
    @Override
    public void reset(List<Card> discarded) {
        discarded.forEach(deck::add);
        shuffle();
    }

    /**
     * Shuffles this deck.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public int size() {
        return deck.size();
    }

    private final List<Card> deck;
}