package card.character;

public abstract class Character {
	
	protected final int number;

	public Character(final int number) {
		this.number = number;
	}
	
	public abstract void power();

	public int getNumber() {
		return number;
	}
}
