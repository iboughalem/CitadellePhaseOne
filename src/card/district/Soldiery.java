package card.district;

import java.util.List;

public class Soldiery extends District {

	private SoldieryDistrict dist;

	public Soldiery(final SoldieryDistrict dist, final int cost) {
		super(Color.RED, cost);
		this.dist = dist;
	}
	
	public static void addCards(final List<District> cards) {
		cards.add(new Soldiery(SoldieryDistrict.WATCHTOWER, 1));
		cards.add(new Soldiery(SoldieryDistrict.WATCHTOWER, 1));
		cards.add(new Soldiery(SoldieryDistrict.WATCHTOWER, 1));
		
		cards.add(new Soldiery(SoldieryDistrict.PRISON, 2));
		cards.add(new Soldiery(SoldieryDistrict.PRISON, 2));
		cards.add(new Soldiery(SoldieryDistrict.PRISON, 2));
		
		cards.add(new Soldiery(SoldieryDistrict.BARRACK, 3));
		cards.add(new Soldiery(SoldieryDistrict.BARRACK, 3));
		cards.add(new Soldiery(SoldieryDistrict.BARRACK, 3));
		
		cards.add(new Soldiery(SoldieryDistrict.FORTRESS, 5));
		cards.add(new Soldiery(SoldieryDistrict.FORTRESS, 5));
	
	}
	
	@Override
	public final String toString() {
		return "Carte Soldatesque" 
				+ ", couleur : " + this.color
				+ ", prix :" + this.cost
				+ ", nom : " + this.dist;
	}

}
