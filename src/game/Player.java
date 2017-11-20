package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.character.Character;
import card.district.*;
import card.CardManager;
import util.StringUtils;

public class Player {

	/**
     * if the character is crowned or not.
     */
    private boolean isCrowned;

    /**
     * Coins of the character.
     */
    private int coins;

    /**
     * Players cards.
     */
    private List<District> districtCards;

    /**
     * First character.
     */
    private Character firstCharacter;

    /**
     * Second character.
     */
    private Character secondCharacter;

    /**
     * Scanner to read user input.
     */
    private Scanner scanner;

    /**
     * Player points.
     */
    private int points;

    /**
     * Constructor.
     */
    public Player() {
        this.isCrowned = false;
        this.coins = 0;
        this.districtCards = new ArrayList<>();
        this.firstCharacter = null;
        this.secondCharacter = null;
        this.scanner = new Scanner(System.in);
        this.points = 0;
    }

    /**
     * @return if the character is crowned
     */
    public final boolean isCrowned() {
        return this.isCrowned;
    }

    /**
     * Crown the character or not.
     *
     * @param crowned true is we want to crowned the character
     */
    public final void setCrowned(final boolean crowned) {
        this.isCrowned = crowned;
    }

    /**
     * @return coins of the character.
     */
    public final int getCoins() {
        return this.coins;
    }

    /**
     * Set coins of the character.
     *
     * @param coins that we want
     */
    public final void setCoins(final int coins) {
        this.coins = coins;
    }

    /**
     * Give district card to character.
     *
     * @param cardManager to give him cards
     * @param number      of cards to give him
     * @return cards.
     */
    public final List<District> getDistrictCard(final CardManager cardManager, final int number) {
        List<District> cards = cardManager.getDistrictCard(number);
        if (cards != null) {
            this.districtCards.addAll(cards);
        }
        return cards;
    }

    /**
     * @return first character
     */
    public final Character getFirstCharacter() {
        return this.firstCharacter;
    }

    /**
     * Set the fist character.
     *
     * @param firstCharacter to set
     */
    public final void setFirstCharacter(final Character firstCharacter) {
        this.firstCharacter = firstCharacter;
    }

    /**
     * @return second character
     */
    public final Character getSecondCharacter() {
        return this.secondCharacter;
    }

    /**
     * Set the second character.
     *
     * @param secondCharacter to set
     */
    public final void setSecondCharacter(final Character secondCharacter) {
        this.secondCharacter = secondCharacter;
    }

    /**
     * Choose one character at start of the turn.
     *
     * @param cardManager     card manager
     * @param characterNumber the number of the character, if it's 1 it's the first character else the second character
     */
    public final void chooseCharacterCard(final CardManager cardManager, final int characterNumber) {

        List<Integer> authorisedString = new ArrayList<>();

        for (Character character : cardManager.getCharacterCards()) {
            authorisedString.add(character.getNumber());
        }

        System.out.println("Liste des cartes disponible : ");
        System.out.println(cardManager.toStringCharacterCards());
        System.out.println("Choisir une carte : ");

        String choice = scanner.nextLine();

        if (StringUtils.isNumeric(choice)) {
            int numberOfTheCard = Integer.parseInt(choice);
            if (authorisedString.contains(numberOfTheCard)) {

                if (characterNumber == 1) {
                    this.firstCharacter = cardManager.getCharacterCard(numberOfTheCard);
                } else {
                    this.secondCharacter = cardManager.getCharacterCard(numberOfTheCard);
                }
                cardManager.shuffleCharacterCards();
            } else {
                System.out.println("Vous avez tapé un mauvais caractère.");
                this.chooseCharacterCard(cardManager, characterNumber);
            }
        } else {
            System.out.println("Vous avez tapé un mauvais caractère.");
            this.chooseCharacterCard(cardManager, characterNumber);
        }
    }

    /**
     * Discard one character at start of the turn.
     *
     * @param cardManager card manager
     */
    public final void discardCharacterCard(final CardManager cardManager) {
        List<Integer> authorisedString = new ArrayList<>();

        for (Character character : cardManager.getCharacterCards()) {
            authorisedString.add(character.getNumber());
        }

        System.out.println("Choisir une carte à défausser : ");
        System.out.println(cardManager.toStringCharacterCards());
        String numberOfTheCard = scanner.nextLine();

        if (StringUtils.isNumeric(numberOfTheCard)) {
            if (authorisedString.contains(Integer.parseInt(numberOfTheCard))) {
                cardManager.discardCharacterCard(Integer.parseInt(numberOfTheCard));
                cardManager.shuffleCharacterCards();
            } else {
                System.out.println("Vous avez tapé un mauvais caractère.");
                this.discardCharacterCard(cardManager);
            }
        } else {
            System.out.println("Vous avez tapé un mauvais caractère.");
            this.discardCharacterCard(cardManager);
        }

    }

    /**
     * Know if the player has the character with a specific number.
     *
     * @param number of the character
     * @return if the player has the character with a specific number
     */
    public final boolean hasCharacterWithNumber(final int number) {
        return this.firstCharacter.getNumber() == number || this.secondCharacter.getNumber() == number;
    }

    /**
     * Choose one district card and discard one.
     *
     * @param cardManager card manager
     */
    public final void chooseDistrictCard(final CardManager cardManager) {
        System.out.println("Liste des cartes disponible : ");

        List<District> districtCards = cardManager.getDistrictCard(2);

        assert districtCards != null;
        for (int i = 1; i <= districtCards.size(); i++) {
            System.out.println(i + ") " + districtCards.get(i - 1));
        }

        System.out.println("Choisir une carte : ");

        String choice = scanner.nextLine();

        if (StringUtils.isNumeric(choice)) {
            int options = Integer.parseInt(choice);
            if (options == 1) {
                System.out.println("Vous avez choisi cette carte : " + districtCards.get(0));
                this.districtCards.add(districtCards.get(0));
                cardManager.putDistrictCardToTheEnd(districtCards.get(1));
            } else if (options == 2) {
                System.out.println("Vous avez choisi cette carte : " + districtCards.get(1));
                this.districtCards.add(districtCards.get(1));
                cardManager.putDistrictCardToTheEnd(districtCards.get(0));
            } else {
                System.out.println("Vous avez tapé un mauvais caractère.");
                this.chooseDistrictCard(cardManager);
            }
        } else {
            System.out.println("Vous avez tapé un mauvais caractère.");
            this.chooseDistrictCard(cardManager);
        }
    }

    /**
     * Take two coins or one card.
     *
     * @param cardManager card manager
     */
    public final void takeCoinsOrCards(final CardManager cardManager) {
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1) Prendre deux pièces d'or");
        System.out.println("2) Piocher deux cartes et en défausser une");

        String choice = scanner.nextLine();

        if (StringUtils.isNumeric(choice)) {
            int options = Integer.parseInt(choice);

            if (options == 1) { //get coins
                this.setCoins(this.getCoins() + Bank.getInstance().giveCoins(2));
                System.out.println("Vous avez pris deux pièces d'or");
            } else if (options == 2) { //get cards
                this.chooseDistrictCard(cardManager);
            } else {
                System.out.println("Vous avez tapé un mauvais caractère.");
                this.takeCoinsOrCards(cardManager);
            }
        } else {
            System.out.println("Vous avez tapé un mauvais caractère.");
            this.takeCoinsOrCards(cardManager);
        }
    }

    /**
     * Place a district card to the tray.
     *
     * @param tray          to place cards
     * @param isFirstPlayer is the first player
     * @param isArchitect   the player is an architect
     */
    public final void placeDisctrictCard(final Tray tray, final boolean isFirstPlayer, final boolean isArchitect) {

        List<Integer> authorisedString = new ArrayList<>();

        int index = 0;
        String options = "";
        do {
            System.out.println("Vous disposez de " + this.coins + " piece d'or");
            System.out.println("Vous avez les cartes suivantes :");

            for (int i = 1; i <= this.districtCards.size(); i++) {
                System.out.println(i + ") " + this.districtCards.get(i - 1));
                authorisedString.add(i);
            }
            System.out.println("Si vous voulez poser une carte, tapez son numero sinon tapez \"n\".");
            options = scanner.nextLine();

            if (options.equals("n")) {
                System.out.println("Vous ne posez aucune carte, au joueur suivant");
            } else {

                if (StringUtils.isNumeric(options)) {

                    if (authorisedString.contains(Integer.parseInt(options))) {
                        index++;
                        int cardNumber = Integer.parseInt(options) - 1;
                        District card = this.districtCards.get(cardNumber);

                        if (this.coins >= card.getCost()) {
                            boolean alreadyOnTray = false;

                            if (isFirstPlayer) {

                                for (District firstPlayerCard : tray.firstPlayerDistrictCards()) {
                                    if (card.getColor() == firstPlayerCard.getColor() && card.getCost() == firstPlayerCard.getCost() && card.getClass() == firstPlayerCard.getClass()) {
                                        alreadyOnTray = true;
                                    }
                                }

                                tray.firstPlayerPlaceCard(card);
                            } else {
                                for (District secondPlayerCard : tray.secondPlayerDistrictCards()) {
                                    if (card.getColor() == secondPlayerCard.getColor() && card.getCost() == secondPlayerCard.getCost() && card.getClass() == secondPlayerCard.getClass()) {
                                        alreadyOnTray = true;
                                    }
                                }

                                tray.secondPlayerPlaceCard(card);
                            }

                            if (!alreadyOnTray) {
                                this.coins -= card.getCost();
                                Bank.getInstance().takeCoins(card.getCost());
                                this.districtCards.remove(cardNumber);

                                System.out.println("Vous avez pose cette carte : " + card);
                                System.out.println("Il vous reste " + this.coins + " pièce(s) d'or");
                            } else {
                                System.out.println("Vous avez deja�cette carte dans votre cite");
                                this.placeDisctrictCard(tray, isFirstPlayer, isArchitect);
                            }

                        } else {
                            System.out.println("Vous n'avez pas assez de piece d'or, faite une autre action");
                            this.placeDisctrictCard(tray, isFirstPlayer, isArchitect);
                        }
                    } else {
                        System.out.println("Vous n'avez pas assez de piece d'or, faite une autre action");
                        this.placeDisctrictCard(tray, isFirstPlayer, isArchitect);
                    }
                } else {
                    System.out.println("Vous avez saisi un mauvais caractere.");
                    this.placeDisctrictCard(tray, isFirstPlayer, isArchitect);
                }
            }
        } while (!options.equals("n") && isArchitect && index < 3);
    }

    /**
     * Getter.
     *
     * @return points
     */
    public final int getPoints() {
        return this.points;
    }

    /**
     * Setter.
     *
     * @param points to set
     */
    public final void setPoints(final int points) {
        this.points = points;
    }

    /**
     * Getter.
     *
     * @return district cards
     */
    public final List<District> getDistrictCards() {
        return this.districtCards;
    }

    /**
     * Setter.
     *
     * @param districtCards districtCards
     */
    public final void setDistrictCards(final List<District> districtCards) {
        this.districtCards = districtCards;
    }

    /**
     * To string.
     *
     * @return character string
     */
    @Override
    public final String toString() {
        return "Possede la couronne: " + this.isCrowned
                + "\npiece d'or: " + this.coins
                + "\nnombre de carte quatiers: " + this.districtCards.size()
                + "\npremier personnage: " + this.firstCharacter
                + "\nsecond personnage: " + this.secondCharacter;
    }

}
