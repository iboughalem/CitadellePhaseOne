package util;

public interface Random {

	/**
     * Create random int between min and max.
     *
     * @param min min number
     * @param max max number
     * @return the random number
     */
    default int random(final int min, final int max) {
        java.util.Random random = new java.util.Random();
        return random.nextInt(max - min + 1) + min;
    }

}
