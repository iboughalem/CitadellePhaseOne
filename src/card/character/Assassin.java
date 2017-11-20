package card.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import card.CardManager;
import game.PowerManager;
import util.StringUtils;


public class Assassin extends Character {

	public Assassin() {
		super(1);
	}
	
    public final String toString() {
        return "Assassin";
    }

	@Override
	public void power() {
		List<Integer> authorisedString = new ArrayList<>();

        CardManager.getInstance().getCharacterCards().clear();
        CardManager.getInstance().addCharacterCards();
        CardManager.getInstance().discardCharacterCard(1);

        for (Character character : CardManager.getInstance().getCharacterCards()) {
            authorisedString.add(character.getNumber());
        }

        System.out.println("Qui voulez-vous tuer ? : ");
        System.out.println(CardManager.getInstance().toStringCharacterCards());

        Scanner scanner = new Scanner(System.in);

        String choice = scanner.nextLine();
        if (StringUtils.isNumeric(choice)) {
            int numberOfTheCard = Integer.parseInt(choice);

            if (authorisedString.contains(numberOfTheCard)) {
                Character character = CardManager.getInstance().getCharacterCard(numberOfTheCard);

                System.out.println("Vous venez de tuer : " + character);
                PowerManager.getInstance().setCharacterKilled(character);
                CardManager.getInstance().discardAllCharacterCard();
            } else {
                System.out.println("Vous avez saisi un mauvais caractere.");
                this.power();
            }
        } else {
            System.out.println("Vous avez saisi un mauvais caractere.");
            this.power();
        }
    }

	}
