package game;


public class Bank {

    /**
     * Unique instance.
     */
    private static final Bank INSTANCE = new Bank();

    /**
     * Default coins of the bank.
     */
    public static final int DEFAULT_COINS = 30;

    /**
     * Coins of the bank.
     */
    private int coins;

    /**
     * Private constructor.
     */
    private Bank() {
        this.coins = Bank.DEFAULT_COINS;
    }

    /**
     * Get our instance.
     *
     * @return instance
     */
    public static Bank getInstance() {
        return INSTANCE;
    }

    /**
     * Get the amount of coins.
     *
     * @param number of coins that we want
     * @return coins available
     */
    public int giveCoins(final int number) {
        if (number < 0) {
            this.coins = 0;
            return 0;
        } else if (this.coins - number >= 0) {
            this.coins -= number;
            return number;
        } else if (number >= this.coins) {
            final int result = this.coins;
            this.coins = 0;
            return result;
        }

        return this.coins;
    }

    /**
     * Give x coins to bank.
     *
     * @param number of coins want to give
     */
    public void takeCoins(final int number) {
        if (this.coins + number <= DEFAULT_COINS) {
            this.coins += number;
        } else {
            this.coins = DEFAULT_COINS;
        }
    }

    /**
     * Set coins amount to number.
     *
     * @param number of coins
     */
    public void setCoinsAmount(final int number) {
        this.coins = number;
    }

    /**
     * Get the amount of coins available.
     *
     * @return coins available
     */
    public int getTotalCoins() {
        return this.coins;
    }

}
