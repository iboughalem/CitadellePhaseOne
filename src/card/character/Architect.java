package card.character;

import java.util.List;

import card.district.*;
import card.CardManager;
import game.Player;

public class Architect extends Character {

	public Architect() {
		super(7);
	}
	
	public final String toString() {
        return "Architecte";
    }
	
	@Override
	public void power() {
		// TODO Auto-generated method stub

	}
	
	/**
    * Power of the character.
    * @param player character
    */
   public final void power(final Player player) {
       System.out.println("Vous prenez 2 cartes quatiers.");
       List<District> districtCards = CardManager.getInstance().getDistrictCard(2);

       assert districtCards != null;
       for (District card : districtCards) {
           player.getDistrictCards().add(card);
       }
   }

}
