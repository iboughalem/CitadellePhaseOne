package game;

import card.character.Character;

public class PowerManager {

    /**
     * Instance of our singleton.
     */
    private static final PowerManager INSTANCE = new PowerManager();

    /**
     * Character killed.
     */
    private Character characterKilled;

    /**
     * Stolen character.
     */
    private Character characterStolen;

    /**
     * Constructor.
     */
    private PowerManager() {
        this.characterKilled = null;
        this.characterStolen = null;
    }

    /**
     * @return our singleton
     */
    public static PowerManager getInstance() {
        return INSTANCE;
    }

    /**
     * Getter.
     *
     * @return the character killed
     */
    public Character getCharacterKilled() {
        return this.characterKilled;
    }

    /**
     * Getter.
     *
     * @return stolen character
     */
    public Character getCharacterStolen() {
        return this.characterStolen;
    }

    /**
     * Setter.
     *
     * @param characterStolen character stolen
     */
    public void setCharacterStolen(final Character characterStolen) {
        this.characterStolen = characterStolen;
    }

    /**
     * Setter.
     *
     * @param characterKilled character to kill
     */
    public void setCharacterKilled(final Character characterKilled) {
        this.characterKilled = characterKilled;
    }

}
