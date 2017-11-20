package card.district;

import java.util.List;

public class Wonder extends District {

	private WonderDistrict dist;
	protected int supplement; // At the final count the two wonder cards : DRACOPART and UNIVERSITE get a +2

	public Wonder(final WonderDistrict dist, final int cost, int supplement) {
		super(Color.PURPLE, cost);
		this.dist = dist;
		this .supplement = supplement;
	}
	
	public static void addCards(final List<District> cards) {
		cards.add(new Wonder(WonderDistrict.COURSDESMIRACLES, 2, 0));
		
		cards.add(new Wonder(WonderDistrict.DONJON, 3, 0));
		cards.add(new Wonder(WonderDistrict.DONJON, 3, 0));
		
		cards.add(new Wonder(WonderDistrict.LABORATOIRE, 5, 0));
		
		cards.add(new Wonder(WonderDistrict.MANUFACTURE, 5, 0));
		
		cards.add(new Wonder(WonderDistrict.OBSERVATOIRE, 5, 0));
		
		cards.add(new Wonder(WonderDistrict.CIMETIERE, 5, 0));
		
		cards.add(new Wonder(WonderDistrict.BIBLIOTHEQUE, 6, 0));
		
		cards.add(new Wonder(WonderDistrict.ECOLEDEDEMAGIE, 6, 0));
		
		cards.add(new Wonder(WonderDistrict.UNIVERSITE, 6, 2));
		
		cards.add(new Wonder(WonderDistrict.DRACOPART, 6, 2));
		
	}
	
	@Override
	public final String toString() {
		return "Carte Merveille" 
				+ ", couleur : " + this.color
				+ ", prix :" + this.cost
				+ ", nom : " + this.dist;
	}

}
