package card.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import card.CardManager;
import game.PowerManager;
import util.StringUtils;
import game.Player;
import card.district.*;

public class Magician extends Character {

	public Magician() {
		super(3);
	}
	
	public final String toString() {
        return "Magicien";
    }

	@Override
	public void power() {
		// TODO Auto-generated method stub

	}
	
	/**
     * Specific power.
     *
     * @param player   player
     * @param opponent opponent
     */
    public final void power(final Player player, final Player opponent) {
        List<Integer> authorisedString = new ArrayList<>();
        authorisedString.add(1);
        authorisedString.add(2);

        System.out.println("1) Echanger toutes mes cartes contre celles de l'adversaire ?");
        System.out.println("2) Defausser x cartes et piocher x cartes ?");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (StringUtils.isNumeric(choice)) {
            int response = Integer.parseInt(choice);

            if (authorisedString.contains(response)) {
                if (response == 1) {
                    List<District> tmp = player.getDistrictCards();
                    player.setDistrictCards(opponent.getDistrictCards());
                    opponent.setDistrictCards(tmp);

                    System.out.println("Liste de vos nouvelles cartes : ");
                    for (District card : player.getDistrictCards()) {
                        System.out.println(card);
                    }

                } else if (response == 2) {
                    System.out.println("Liste de vos cartes : ");
                    for (int i = 0; i < player.getDistrictCards().size(); i++) {
                        System.out.println((i + 1) + ")" + player.getDistrictCards().get(i));
                    }

                    int index = 1;
                    boolean finish = false;

                    do {
                        List<Integer> authorisedString2 = new ArrayList<>();

                        for (int i = 1; i <= player.getDistrictCards().size(); i++) {
                            authorisedString2.add(i);
                        }

                        index++;
                        System.out.println("Tapez le numero de la carte pour la defausser ou \"n\" pour arreter.");
                        Scanner scanner2 = new Scanner(System.in);
                        String choice2 = scanner2.nextLine();

                        if (StringUtils.isNumeric(choice2)) {
                            if (authorisedString2.contains(Integer.parseInt(choice2))) {
                                int cardNumber = Integer.parseInt(choice2) - 1;
                                District cardToRemove = player.getDistrictCards().remove(cardNumber);
                                CardManager.getInstance().putDistrictCardToTheEnd(cardToRemove);
                                District newCard = player.getDistrictCard(CardManager.getInstance(), 1).get(0);

                                System.out.println("Vous avez defausser " + cardToRemove + " mais vous avez eu " + newCard);

                                System.out.println("Liste de vos cartes : ");
                                for (int i = 0; i < player.getDistrictCards().size(); i++) {
                                    System.out.println((i + 1) + ")" + player.getDistrictCards().get(i));
                                }
                            } else {
                                System.out.println("Vous avez saisi un mauvais caractere.");
                                this.power(player, opponent);
                            }
                        } else if (choice2.equals("n")) {
                            finish = true;
                            System.out.println("Vous ne defausser plus de cartes.");
                        } else {
                            System.out.println("Vous avez saisi un mauvais caractere.");
                            this.power(player, opponent);
                        }
                    } while (index <= player.getDistrictCards().size() && !finish);
                }
            } else {
                System.out.println("Vous avez saisi un mauvais caractere.");
                this.power(player, opponent);
            }
        } else {
            System.out.println("Vous avez saisi un mauvais caractere.");
            this.power(player, opponent);
        }
    }

}
