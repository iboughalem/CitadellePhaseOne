package card.character;

import card.district.*;
import game.*;


public class King extends Character {

	public King() {
		super(4);
	}
	
	public final String toString() {
        return "Roi";
    }

	@Override
	public void power() {
		// TODO Auto-generated method stub

	}
	
	/**
     * Specific king power.
     *
     * @param player        player
     * @param tray          tray
     * @param isFirstPlayer is the first player
     */
    public final void power(final Player player, final Tray tray, final boolean isFirstPlayer) {
        int total = 0;

        if (isFirstPlayer) {
            for (District card : tray.firstPlayerDistrictCards()) {
                if (card.getColor() == Color.YELLOW) {
                    total++;
                }
            }
        } else {
            for (District card : tray.secondPlayerDistrictCards()) {
                if (card.getColor() == Color.YELLOW) {
                    total++;
                }
            }
        }

        player.setCoins(player.getCoins() + Bank.getInstance().giveCoins(total));
        System.out.println("Vous avez recu " + total + " piece(s).");
    }

}
