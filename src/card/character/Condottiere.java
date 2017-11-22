package card.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.district.*;
import game.*;
import util.StringUtils;


public class Condottiere extends Character {

	public Condottiere() {
		super(8);
	}
	
	public final String toString() {
        return "Condottiere";
    }

	@Override
	public void power() {
		// TODO Auto-generated method stub

	}

    /**
     * Specific condottiere power.
     *
     * @param player        player
     * @param tray          tray
     * @param isFirstPlayer is the first player
     */
    public final void power(final Player player, final Tray tray, final boolean isFirstPlayer) {
        int total = 0;

        if (isFirstPlayer) {
            for (District card : tray.firstPlayerDistrictCards()) {
                if (card.getColor() == Color.RED) {
                    total++;
                }
            }
        } else {
            for (District card : tray.secondPlayerDistrictCards()) {
                if (card.getColor() == Color.RED) {
                    total++;
                }
            }
        }

        player.setCoins(player.getCoins() + Bank.getInstance().giveCoins(total));
        System.out.println("Vous avez recu " + total + " piece(s).");
    }

    /**
     * Specific condottiere power.
     *
     * @param tray          tray
     * @param isFirstPlayer is the first player
     * @param player        player
     */
    public final void power(final Tray tray, final boolean isFirstPlayer, final Player player) {
        if (!tray.isFinish()) {
            System.out.println("Voulez-vous detruire un quartier ? (o/n) : ");

            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine();

            if (StringUtils.isAlphabet(response)) {
                if (response.equals("o")) {
                    List<Integer> authorisedString = new ArrayList<>();

                    System.out.println("Quel quartier detruire ? :");

                    int number = 0;

                    if (isFirstPlayer) {
                        System.out.println("---Vos quartiers---");
                        for (int i = 0; i < tray.firstPlayerDistrictCards().size(); i++) {
                            if (tray.firstPlayerDistrictCards().get(i).getColor() != Color.BLUE) {
                                number++;
                                authorisedString.add(number);
                                System.out.println(number + ")" + tray.firstPlayerDistrictCards().get(i));
                            }
                        }

                        System.out.println("\n---Les quartiers des adversaires---");
                        for (int i = 0; i < tray.secondPlayerDistrictCards().size(); i++) {
                            if (tray.secondPlayerDistrictCards().get(i).getColor() != Color.BLUE) {
                                number++;
                                authorisedString.add(number);
                                System.out.println(number + ")" + tray.secondPlayerDistrictCards().get(i));
                            }
                        }
                    } else {
                        System.out.println("\n---Les quartiers des adversaires---");
                        for (int i = 0; i < tray.firstPlayerDistrictCards().size(); i++) {
                            if (tray.firstPlayerDistrictCards().get(i).getColor() != Color.BLUE) {
                                number++;
                                authorisedString.add(number);
                                System.out.println(number + ")" + tray.firstPlayerDistrictCards().get(i));
                            }
                        }

                        System.out.println("---Vos quartiers---");
                        for (int i = 0; i < tray.secondPlayerDistrictCards().size(); i++) {
                            if (tray.secondPlayerDistrictCards().get(i).getColor() != Color.BLUE) {
                                number++;
                                authorisedString.add(number);
                                System.out.println(number + ")" + tray.secondPlayerDistrictCards().get(i));
                            }
                        }
                    }

                    if (number >= 1) {
                        String choice = scanner.nextLine();

                        if (StringUtils.isNumeric(choice)) {
                            int cardNumber = Integer.parseInt(choice);
                            if (authorisedString.contains(cardNumber)) {
                                if (!tray.discardDistrictCard(cardNumber - 1, player)) {
                                    this.power(tray, isFirstPlayer, player);
                                }
                            } else {
                                System.out.println("Vous avez saisi un mauvais caractere.");
                                this.power(tray, isFirstPlayer, player);
                            }
                        } else {
                            System.out.println("Vous avez saisi un mauvais caractere.");
                            this.power(tray, isFirstPlayer, player);
                        }
                    } else {
                        System.out.println("Il n'y a de quartiers a detruire");
                    }
                } else if (response.equals("n")) {
                    System.out.println("Vous ne detruisez pas de quartier.");
                } else {
                    System.out.println("Vous avez saisi un mauvais caractere.");
                    this.power(tray, isFirstPlayer, player);
                }
            } else {
                System.out.println("Vous avez saisi un mauvais caractere.");
                this.power(tray, isFirstPlayer, player);
            }
        } else {
            System.out.println("L'adversaire a  deja  " + Tray.NUMBER_CARD_TO_WIN + " quartiers, vous ne pouvez pas detruire sa cite.");
        }
    }
}
