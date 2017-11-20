package card.district;

import java.util.List;

public class TradeAndCraft extends District {

	private TradeAndCraftDistrict dist;

	public TradeAndCraft(final TradeAndCraftDistrict dist, final int cost) {
		super(Color.BLUE, cost);
		this.dist = dist;
	}
	
	public static void addCards(final List<District> cards) {
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.TAVERN, 1));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.TAVERN, 1));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.TAVERN, 1));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.TAVERN, 1));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.TAVERN, 1));
		
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.SHOP, 2));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.SHOP, 2));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.SHOP, 2));
		
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.MARKET, 2));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.MARKET, 2));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.MARKET, 2));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.MARKET, 2));
		
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.COUNTER, 3));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.COUNTER, 3));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.COUNTER, 3));
		
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.HARBOR, 4));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.HARBOR, 4));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.HARBOR, 4));
		
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.CITYHALL, 5));
		cards.add(new TradeAndCraft(TradeAndCraftDistrict.CITYHALL, 5));
		
	}
	
	@Override
	public final String toString() {
		return "Carte Commerce et Artisants" 
				+ ", couleur : " + this.color
				+ ", prix :" + this.cost
				+ ", nom : " + this.dist;
	}

}
