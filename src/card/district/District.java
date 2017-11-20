package card.district;


public class District {
	
	/**
	 * Color of the District
	 * @see 
	 */
	protected final Color color;

	/**
	 * cost to build the card
	 */
	protected final int cost;

	/**
	 * @param color of the district
	 * @param cost to build the card
	 */
	public District(Color color, int cost) {
		this.color = color;
		this.cost = cost;
	}
	
	/**
	 * Getter of color
	 * @return the color of the district
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Getter of cost
	 * @return the cost to build the district
	 */
	public int getCost() {
		return this.cost;
	}
}
