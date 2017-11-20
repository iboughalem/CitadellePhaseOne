package card.character;

import card.district.*;
import game.*;

public class Trader extends Character {

	public Trader() {
		super(6);
	}
	
	public final String toString() {
        return "Marchand";
    }

	@Override
	public void power() {
		// TODO Auto-generated method stub

	}
	
	/**
     * Specific trader power.
     *
     * @param player        player
     * @param tray          tray
     * @param isFirstPlayer is the first player
     */
    public final void power(final Player player, final Tray tray, final boolean isFirstPlayer) {
        int total = 0;

        if (isFirstPlayer) {
            for (District card : tray.firstPlayerDistrictCards()) {
                if (card.getColor() == Color.GREEN) {
                    total++;
                }
            }
        } else {
            for (District card : tray.secondPlayerDistrictCards()) {
                if (card.getColor() == Color.GREEN) {
                    total++;
                }
            }
        }

        player.setCoins(player.getCoins() + Bank.getInstance().giveCoins(total));
        System.out.println("Vous avez recu " + total + " piece(s).");
    }

}
