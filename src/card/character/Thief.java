package card.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import card.CardManager;
import card.character.Character;
import game.PowerManager;
import util.StringUtils;

public class Thief extends Character {

	public Thief() {
		super(2);
	}
	
	public final String toString() {
        return "Voleur";
    }

	@Override
	public void power() {
		List<Integer> authorisedString = new ArrayList<>();

        CardManager.getInstance().getCharacterCards().clear();
        CardManager.getInstance().addCharacterCards();
        CardManager.getInstance().discardCharacterCard(1);
        CardManager.getInstance().discardCharacterCard(2);

        for (Character character : CardManager.getInstance().getCharacterCards()) {
            authorisedString.add(character.getNumber());
        }

        System.out.println("Qui voulez-vous voler ? : ");
        System.out.println(CardManager.getInstance().toStringCharacterCards());

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (StringUtils.isNumeric(choice)) {
            int numberOfTheCard = Integer.parseInt(choice);

            if (authorisedString.contains(numberOfTheCard)) {
                Character character = CardManager.getInstance().getCharacterCard(numberOfTheCard);

                System.out.println("Vous avez voler : " + character);
                PowerManager.getInstance().setCharacterStolen(character);
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
