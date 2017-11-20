package card.district;

import java.util.List;

public class Nobility extends District {

	private NobilityDistrict dist;

	public Nobility(final NobilityDistrict dist, final int cost) {
		super(Color.YELLOW, cost);
		this.dist = dist;
	}
	
	public static void addCards(final List<District> cards) {
		cards.add(new Nobility(NobilityDistrict.MANOR, 3));
		cards.add(new Nobility(NobilityDistrict.MANOR, 3));
		cards.add(new Nobility(NobilityDistrict.MANOR, 3));
		cards.add(new Nobility(NobilityDistrict.MANOR, 3));
		cards.add(new Nobility(NobilityDistrict.MANOR, 3));
	
		cards.add(new Nobility(NobilityDistrict.CASTLE, 4));
		cards.add(new Nobility(NobilityDistrict.CASTLE, 4));
		cards.add(new Nobility(NobilityDistrict.CASTLE, 4));
		cards.add(new Nobility(NobilityDistrict.CASTLE, 4));
		cards.add(new Nobility(NobilityDistrict.CASTLE, 4));
		
		cards.add(new Nobility(NobilityDistrict.PALACE, 5));
		cards.add(new Nobility(NobilityDistrict.PALACE, 5));
	}
	
	@Override
	public final String toString() {
		return "Carte Noblesse" 
				+ ", couleur : " + this.color
				+ ", prix :" + this.cost
				+ ", nom : " + this.dist;
	}


}
