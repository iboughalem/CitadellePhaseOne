package game;

import java.util.ArrayList;
import java.util.List;


import card.district.*;
import game.Player;

public class Tray {

    /**
     * District cards of the first player.
     */
    private List<District> firstPlayerDistrictCards;

    /**
     * District cards of the second player.
     */
    private List<District> secondPlayerDistrictCards;

    /**
     * Number of card to stop the game.
     */
    public static final int NUMBER_CARD_TO_WIN = 8;

    /**
     * Constructor.
     */
    public Tray() {
        this.firstPlayerDistrictCards = new ArrayList<>();
        this.secondPlayerDistrictCards = new ArrayList<>();
    }

    /**
     * Place card to the tray.
     *
     * @param card to place
     */
    public final void firstPlayerPlaceCard(final District card) {
        this.firstPlayerDistrictCards.add(card);
    }

    /**
     * Place card to the tray.
     *
     * @param card to place
     */
    public final void secondPlayerPlaceCard(final District card) {
        this.secondPlayerDistrictCards.add(card);
    }

    /**
     * Check if a player have 8 cards.
     *
     * @return if there is a winner
     */
    public final boolean isFinish() {
        return this.firstPlayerDistrictCards.size() >= NUMBER_CARD_TO_WIN || this.secondPlayerDistrictCards.size() >= NUMBER_CARD_TO_WIN;
    }

    /**
     * Getter.
     *
     * @return the first player district cards
     */
    public final List<District> firstPlayerDistrictCards() {
        return this.firstPlayerDistrictCards;
    }

    /**
     * Getter.
     *
     * @return the second player district cards
     */
    public final List<District> secondPlayerDistrictCards() {
        return this.secondPlayerDistrictCards;
    }

    /**
     * Discard card.
     *
     * @param cardNumber card number
     * @param player     player
     * @return if successful or not
     */
    public final boolean discardDistrictCard(final int cardNumber, final Player player) {

        District card = null;
        boolean successful = false;

        if (cardNumber >= 0 && cardNumber < firstPlayerDistrictCards.size()) {
            card = firstPlayerDistrictCards.get(cardNumber);

            if (player.getCoins() >= card.getCost() - 1) {
                firstPlayerDistrictCards.remove(cardNumber);
                successful = true;
            }

        } else if (cardNumber >= firstPlayerDistrictCards.size() && cardNumber < firstPlayerDistrictCards.size() + secondPlayerDistrictCards.size()) {
            card = secondPlayerDistrictCards.get(firstPlayerDistrictCards.size() - cardNumber);

            if (player.getCoins() >= card.getCost() - 1) {
                secondPlayerDistrictCards.remove(cardNumber);
                successful = true;
            }
        }

        if (successful) {
            player.setCoins(player.getCoins() - card.getCost() - 1);
            Bank.getInstance().takeCoins(card.getCost() - 1);
            System.out.println("Vous avez detruit le quartier : " + card);
        } else {
            System.out.println("Vous n'avez pas assez de pieces!");
        }

        return successful;
    }
}
