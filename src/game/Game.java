package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.CardManager;
import game.Bank;
import game.Player;
import game.PowerManager;
import game.Tray;
import card.character.*;
import card.district.Color;
import card.district.District;
import util.Random;

public class Game implements Random{
	
    /**
     * First character.
     */
    private final Player firstPlayer;

    /**
     * Second character.
     */
    private final Player secondPlayer;

    /**
     * Bank.
     */
    private final Bank bank;

    /**
     * Card manager.
     */
    private final CardManager cardManager;

    /**
     * PowerManager manager.
     */
    private final PowerManager powerManager;

    /**
     * If the game is finish or not.
     */
    private boolean isFinish;

    /**
     * If the first player put the last card.
     */
    private boolean isFirstPlayerPutTheLastCard;

    /**
     * If the second player put the last card.
     */
    private boolean isSecondPlayerPutTheLastCard;

    /**
     * Tray.
     */
    private Tray tray;

    /**
     * Constructor.
     */
    
    public Game() {
        this.firstPlayer = new Player();
        this.secondPlayer = new Player();
        this.bank = Bank.getInstance();
        this.cardManager = CardManager.getInstance();
        this.powerManager = PowerManager.getInstance();
        this.isFinish = false;
        this.tray = new Tray();
        this.isFirstPlayerPutTheLastCard = false;
        this.isSecondPlayerPutTheLastCard = false;
    }
    
    /**
     * Display information about the characters 
     */
    private void characterePowerDisplay() {
    	System.out.println("Rappel des personnages et leur caracteristiques afficher dans l'ordre d'appel");
    	System.out.println("\nL'assassin: il choisit un personnage à assassiner. Le personnage assassiné ne sera pas appelé, le joueur qui l'a choisi ne pourra donc pas jouer ce tour."
    			+ "\nLe voleur: il choisit un personnage à voler (à l'exception de l'assassin et du personnage assassiné). Lorsque son personnage sera appelé, le joueur volé devra donner toutes ses pièces d'or au voleur."
    			+ "\nLe magicien: il peut soit remplacer des quartiers de sa main avec la pioche, soit échanger toute sa main avec celle d'un joueur de son choix."
    			+ "\nLe roi: il gagne une pièce d'or par quartier de prestige construit. Il prend la couronne du roi qui lui donnera le privilège de choisir son personnage en premier au prochain tour."
    			+ "\nL'evêque: il gagne une pièce d'or par quartier religieux. Il ne peut pas être attaqué par le condottiere."
    			+ "\nLe marchand: il gagne une pièce d'or, plus une pièce d'or par quartier marchand."
    			+ "\nL'architecte: il pioche deux quartiers supplémentaires, et peut construire jusqu'à trois quartiers sur le tour."
    			+ "\nLe condottiere: il gagne une pièce d'or par quartier militaire. Il peut également détruire un quartier dans une cité adverse ou dans sa propre cité en payant une pièce d'or de moins que la valeur du quartier."
    			+ "\nIl ne peut détruire un quartier du prêtre, ni un quartier dans une cité qui en contient au moins huit.\n");
    }

    /**
     * Manage the game until the game isn't finish.
     */
    private void manageGame() {
        while (!isFinish) {
            this.powerManager.setCharacterKilled(null);
            this.powerManager.setCharacterStolen(null);

            this.startTurn();

            System.out.println("\n-----Premier joueur-----");
            System.out.println(this.firstPlayer);

            System.out.println("\n-----Deuxieme joueur-----");
            System.out.println(this.secondPlayer);

            System.out.println("\n----------Debut du tour----------");
            for (int characterNumber = 1; characterNumber <= 9; characterNumber++) {
                if (this.firstPlayer.hasCharacterWithNumber(characterNumber) || this.secondPlayer.hasCharacterWithNumber(characterNumber)) {
                    if (this.powerManager.getCharacterKilled() == null || (this.powerManager.getCharacterKilled() != null && this.powerManager.getCharacterKilled().getNumber() != characterNumber)) {
                        switch (characterNumber) {
                            case 1:
                                System.out.println("\n----- Au tour de l'assassin -----");
                                break;
                            case 2:
                                System.out.println("\n----- Au tour du voleur -----");
                                break;
                            case 3:
                                System.out.println("\n----- Au tour du magicien -----");
                                break;
                            case 4:
                                System.out.println("\n----- Au tour du roi -----");
                                break;
                            case 5:
                                System.out.println("\n----- Au tour de l'eveque -----");
                                break;
                            case 6:
                                System.out.println("\n----- Au tour du marchand -----");
                                break;
                            case 7:
                                System.out.println("\n----- Au tour de l'architecte -----");
                                break;
                            case 8:
                                System.out.println("\n----- Au tour du condottiere -----");
                                break;
                            default:
                                break;
                        }

                        if (this.firstPlayer.hasCharacterWithNumber(characterNumber)) {
                            this.stolenCharacter(characterNumber, true);

                            boolean activeNow = this.playPowerBeforePlaceDistrict(this.firstPlayer, characterNumber);

                            System.out.println("-----Joueur-----");
                            System.out.println(this.firstPlayer + "\n\n");

                            this.firstPlayer.takeCoinsOrCards(this.cardManager);

                            if ((this.firstPlayer.getFirstCharacter() instanceof Architect || this.firstPlayer.getSecondCharacter() instanceof Architect) && characterNumber == 7) {
                                this.firstPlayer.placeDisctrictCard(tray, true, true);
                            } else {
                                this.firstPlayer.placeDisctrictCard(tray, true, false);
                            }

                            this.playPowerAfterPlaceDistrict(this.firstPlayer, characterNumber, activeNow);

                            if (tray.firstPlayerDistrictCards().size() >= Tray.NUMBER_CARD_TO_WIN && !this.isSecondPlayerPutTheLastCard) {
                                this.isFirstPlayerPutTheLastCard = true;
                                this.isSecondPlayerPutTheLastCard = false;
                            }
                        } else {
                            this.stolenCharacter(characterNumber, false);

                            boolean activeNow = this.playPowerBeforePlaceDistrict(this.secondPlayer, characterNumber);

                            System.out.println("-----Joueur-----");
                            System.out.println(this.secondPlayer + "\n\n");

                            this.secondPlayer.takeCoinsOrCards(this.cardManager);

                            if ((this.secondPlayer.getFirstCharacter() instanceof Architect || this.secondPlayer.getSecondCharacter() instanceof Architect) && characterNumber == 7) {
                                this.secondPlayer.placeDisctrictCard(tray, false, true);
                            } else {
                                this.secondPlayer.placeDisctrictCard(tray, false, false);
                            }

                            this.playPowerAfterPlaceDistrict(this.secondPlayer, characterNumber, activeNow);

                            if (tray.secondPlayerDistrictCards().size() >= Tray.NUMBER_CARD_TO_WIN && !this.isFirstPlayerPutTheLastCard) {
                                this.isFirstPlayerPutTheLastCard = false;
                                this.isSecondPlayerPutTheLastCard = true;
                            }
                        }
                    }
                }
            }

            this.isFinish = tray.isFinish();

            if (this.isFinish) {
                System.out.println("\n\n-----La partie est terminee-----");

                this.getTotalPoints(this.firstPlayer);
                this.getTotalPoints(this.secondPlayer);

                if (this.isFirstPlayerPutTheLastCard) {
                    this.firstPlayer.setPoints(this.firstPlayer.getPoints() + 4);
                } else if (!this.isFirstPlayerPutTheLastCard && tray.firstPlayerDistrictCards().size() >= Tray.NUMBER_CARD_TO_WIN) {
                    this.firstPlayer.setPoints(this.firstPlayer.getPoints() + 2);
                }

                if (this.isSecondPlayerPutTheLastCard) {
                    this.secondPlayer.setPoints(this.secondPlayer.getPoints() + 4);
                } else if (!this.isSecondPlayerPutTheLastCard && tray.secondPlayerDistrictCards().size() >= Tray.NUMBER_CARD_TO_WIN) {
                    this.secondPlayer.setPoints(this.secondPlayer.getPoints() + 2);
                }

                if (this.isFirstPlayerWin()) {
                    System.out.println("Le premier joueur gagne avec un total de " + this.firstPlayer.getPoints() + " point(s) contre " + this.secondPlayer.getPoints() + " point(s)");
                } else if (this.isSecondPlayerWin()) {
                    System.out.println("Le second joueur gagne avec un total de " + this.secondPlayer.getPoints() + " point(s) contre " + this.firstPlayer.getPoints() + " point(s)");
                } else {
                    System.out.println("Egalite: \nPremier joueur : " + this.firstPlayer.getPoints() + " point(s)\n DeuxiÃ¨me joueur " + this.secondPlayer.getPoints() + " point(s)");
                }
            }
        }
    }
	
    /**
     * Used to init the game.
     */
    public final void init() {
    	System.out.println("Si c'est la prmeière fois que vous jouez à Citedelle prenez le temps de chercher les règles du jeu et les lire avant de commencer.");
    	this.initGame();
        this.manageGame();
    }

    /**
     * Init the game once.
     */
    private void initGame() {
        //set random crown
        this.randomCrowned();

        //Give 2 coins to each players
        this.firstPlayer.setCoins(this.bank.giveCoins(2));
        this.secondPlayer.setCoins(this.bank.giveCoins(2));

        this.cardManager.shuffleDistrictCards();

        //Give 4 district cards to each players
        this.firstPlayer.getDistrictCard(this.cardManager, 4);
        this.secondPlayer.getDistrictCard(this.cardManager, 4);
    }

    /**
     * Set the random crowned at the start of the game.
     */
    private void randomCrowned() {
        int random = this.random(1, 2);

        if (random == 1) {
            this.firstPlayer.setCrowned(true);
            this.secondPlayer.setCrowned(false);
        } else if (random == 2) {
            this.firstPlayer.setCrowned(false);
            this.secondPlayer.setCrowned(true);
        }
    }

    /**
     * Start turn, so the player take one character card and discard one ...
     */
    private void startTurn() {
        System.out.println("\n\n-----Initialisation-----");

        this.cardManager.addCharacterCards();
        this.cardManager.shuffleCharacterCards();

        if (this.firstPlayer.isCrowned()) {
            System.out.println("Le premier joueur possede la couronne, a  lui de choisir en premier.");
            this.initPlayerTurn(this.firstPlayer, 1);
            System.out.println("Au tour du deuxieme joueur.");
            this.initPlayerTurn(this.secondPlayer, 1);
            System.out.println("Au tour du premier joueur.");
            this.initPlayerTurn(this.firstPlayer, 2);
            System.out.println("Au tour du deuxieme joueur.");
            this.initPlayerTurn(this.secondPlayer, 2);
        } else {
            System.out.println("Le deuxieme joueur possede la couronne, a  lui de choisir en premier.");
            this.initPlayerTurn(this.secondPlayer, 1);
            System.out.println("Au tour du premier joueur.");
            this.initPlayerTurn(this.firstPlayer, 1);
            System.out.println("Au tour du deuxieme joueur.");
            this.initPlayerTurn(this.secondPlayer, 2);
            System.out.println("Au tour du premier joueur.");
            this.initPlayerTurn(this.firstPlayer, 2);
        }
    }

    /**
     * Play the turn of the selected player.
     *
     * @param player          turn
     * @param characterNumber the number of the character, if it's 1 it's the first character else the second character
     */
    private void initPlayerTurn(final Player player, final int characterNumber) {
    	this.characterePowerDisplay();
        player.chooseCharacterCard(this.cardManager, characterNumber);

        if (this.cardManager.getCharacterCards().size() != 1) {
            player.discardCharacterCard(this.cardManager);
        } else {
            this.cardManager.discardAllCharacterCard();
        }
    }

    /**
     * Count player points.
     *
     * @param player to count points
     */
    private void getTotalPoints(final Player player) {
        int totalPoints = 0;
        List<Color> districtColors = new ArrayList<>();
        List<District> cards = new ArrayList<>();

        if (this.firstPlayer == player) {
            cards = tray.firstPlayerDistrictCards();
        } else if (this.secondPlayer == player) {
            cards = tray.secondPlayerDistrictCards();
        }

        for (District card : cards) {
            totalPoints += card.getCost();
            if (!districtColors.contains(card.getColor())) {
                districtColors.add(card.getColor());
            }
        }

        if (districtColors.size() == 5) {
            totalPoints += 3;
        }

        for (int i = 0; i < player.getCoins(); i++) {
            totalPoints++;
        }

        player.setPoints(player.getPoints() + totalPoints);
    }

    /**
     * Play the power of the character.
     *
     * @param player          player
     * @param characterNumber character number
     */
    private void characterPower(final Player player, final int characterNumber) {
        if (player.getFirstCharacter().getNumber() == characterNumber) {
            player.getFirstCharacter().power();
            if (player.getFirstCharacter() instanceof Architect) {
                ((Architect) player.getFirstCharacter()).power(player);
            } else if (player.getFirstCharacter() instanceof Bishop) {
                ((Bishop) player.getFirstCharacter()).power(player, this.tray, true);
            } else if (player.getFirstCharacter() instanceof King) {
                ((King) player.getFirstCharacter()).power(player, this.tray, true);
                this.firstPlayer.setCrowned(true);
                this.secondPlayer.setCrowned(false);
            } else if (player.getFirstCharacter() instanceof Trader) {
                ((Trader) player.getFirstCharacter()).power(player, this.tray, true);
            } else if (player.getFirstCharacter() instanceof Condottiere) {
                ((Condottiere) player.getFirstCharacter()).power(player, this.tray, true);
            } else if (player.getFirstCharacter() instanceof Magician) {
                ((Magician) player.getFirstCharacter()).power(player, player);
            }
        } else if (player.getSecondCharacter().getNumber() == characterNumber) {
            player.getSecondCharacter().power();
            if (player.getSecondCharacter() instanceof Architect) {
                ((Architect) player.getSecondCharacter()).power(player);
            } else if (player.getSecondCharacter() instanceof Bishop) {
                ((Bishop) player.getSecondCharacter()).power(player, this.tray, false);
            } else if (player.getSecondCharacter() instanceof King) {
                ((King) player.getSecondCharacter()).power(player, this.tray, false);
                this.firstPlayer.setCrowned(false);
                this.secondPlayer.setCrowned(true);
            } else if (player.getSecondCharacter() instanceof Trader) {
                ((Trader) player.getSecondCharacter()).power(player, this.tray, false);
            } else if (player.getSecondCharacter() instanceof Condottiere) {
                ((Condottiere) player.getSecondCharacter()).power(player, this.tray, false);
            } else if (player.getSecondCharacter() instanceof Magician) {
                ((Magician) player.getSecondCharacter()).power(player, player);
            }
        }
    }

    /**
     * Play power before to place district cards.
     *
     * @param player          player
     * @param characterNumber character number
     * @return if the player active or not is power
     */
    private boolean playPowerBeforePlaceDistrict(final Player player, final int characterNumber) {
        boolean activeNow = false;
        if (player.getFirstCharacter() instanceof Trader || player.getSecondCharacter() instanceof Trader) {
            player.setCoins(player.getCoins() + this.bank.giveCoins(1));
        }
        if (player.getFirstCharacter() instanceof Bishop || player.getSecondCharacter() instanceof Bishop
                || player.getFirstCharacter() instanceof King || player.getSecondCharacter() instanceof King
                || player.getFirstCharacter() instanceof Trader || player.getSecondCharacter() instanceof Trader
                || player.getFirstCharacter() instanceof Condottiere || player.getSecondCharacter() instanceof Condottiere
                || player.getFirstCharacter() instanceof Magician || player.getSecondCharacter() instanceof Magician) {
            System.out.println("Voulez ou activer votre pouvoir maintenant ? (o/n) : ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.equals("o")) {
                activeNow = true;
                this.characterPower(player, characterNumber);
            } else if (!choice.equals("o") && !choice.equals("n")) {
                System.out.println("Vous avez saisi un mauvais caractere.");
                this.playPowerBeforePlaceDistrict(player, characterNumber);
            }
        } else {
            this.characterPower(player, characterNumber);
        }

        return activeNow;
    }

    /**
     * Play power after to place district cards.
     *
     * @param player          player
     * @param characterNumber character number
     * @param activeNow       if the power isn't played
     */
    private void playPowerAfterPlaceDistrict(final Player player, final int characterNumber, final boolean activeNow) {
        if (player.getFirstCharacter() instanceof Bishop || player.getSecondCharacter() instanceof Bishop
                || player.getFirstCharacter() instanceof King || player.getSecondCharacter() instanceof King
                || player.getFirstCharacter() instanceof Trader || player.getSecondCharacter() instanceof Trader
                || player.getFirstCharacter() instanceof Condottiere || player.getSecondCharacter() instanceof Condottiere
                || player.getFirstCharacter() instanceof Magician || player.getSecondCharacter() instanceof Magician) {
            if (!activeNow) {
                System.out.println("Derniere chance d'activer votre pouvoir ? (o/n) : ");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine();

                if (choice.equals("o")) {
                    this.characterPower(player, characterNumber);
                } else if (!choice.equals("o") && !choice.equals("n")) {
                    System.out.println("Vous avez saisi un mauvais caractere.");
                    this.playPowerAfterPlaceDistrict(player, characterNumber, activeNow);
                }
            }
        }

        if (player.getFirstCharacter() instanceof Condottiere) {
            ((Condottiere) player.getFirstCharacter()).power(this.tray, true, this.firstPlayer);
        } else if (player.getSecondCharacter() instanceof Condottiere) {
            ((Condottiere) player.getSecondCharacter()).power(this.tray, false, this.secondPlayer);
        }
    }

    /**
     * If there is a stolen character.
     *
     * @param characterNumber              character number
     * @param stolenCharacterIsFirstPlayer is the stolen player is the first one
     */
    private void stolenCharacter(final int characterNumber, final boolean stolenCharacterIsFirstPlayer) {
        if (this.powerManager.getCharacterStolen() != null && this.powerManager.getCharacterStolen().getNumber() == characterNumber) {
            System.out.println("On vous a pris votre argent !");
            if (stolenCharacterIsFirstPlayer) {
                this.secondPlayer.setCoins(this.secondPlayer.getCoins() + this.firstPlayer.getCoins());
                this.firstPlayer.setCoins(0);
            } else {
                this.firstPlayer.setCoins(this.firstPlayer.getCoins() + this.secondPlayer.getCoins());
                this.secondPlayer.setCoins(0);
            }
        }
    }

    /**
     * Check if the first player win.
     *
     * @return if the first player win
     */
    public final boolean isFirstPlayerWin() {
        return tray.isFinish() && this.firstPlayer.getPoints() > this.secondPlayer.getPoints();
    }

    /**
     * Check if the second player win.
     *
     * @return if the second player win
     */
    public final boolean isSecondPlayerWin() {
        return tray.isFinish() && this.secondPlayer.getPoints() > this.firstPlayer.getPoints();
    }
}
