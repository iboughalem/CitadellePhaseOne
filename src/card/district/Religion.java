package card.district;

import java.util.List;

public class Religion extends District {
	
	private ReligionDistrict dist;

	public Religion(final ReligionDistrict dist, final int cost) {
		super(Color.BLUE, cost);
		this.dist = dist;
	}
	
	public static void addCards(final List<District> cards) {
		cards.add(new Religion(ReligionDistrict.TEMPLE, 1));
		cards.add(new Religion(ReligionDistrict.TEMPLE, 1));
		cards.add(new Religion(ReligionDistrict.TEMPLE, 1));
		
		cards.add(new Religion(ReligionDistrict.CHURCH, 2));
		cards.add(new Religion(ReligionDistrict.CHURCH, 2));
		cards.add(new Religion(ReligionDistrict.CHURCH, 2));
		cards.add(new Religion(ReligionDistrict.CHURCH, 2));
		
		cards.add(new Religion(ReligionDistrict.MONASTERY, 3));
		cards.add(new Religion(ReligionDistrict.MONASTERY, 3));
		cards.add(new Religion(ReligionDistrict.MONASTERY, 3));
		cards.add(new Religion(ReligionDistrict.MONASTERY, 3));
		
		cards.add(new Religion(ReligionDistrict.CATHEDRAL, 5));
		cards.add(new Religion(ReligionDistrict.CATHEDRAL, 5));
	}
	
	@Override
	public final String toString() {
		return "Carte Religion" 
				+ ", couleur : " + this.color
				+ ", prix :" + this.cost
				+ ", nom : " + this.dist;
	}

}
