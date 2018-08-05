package ushortener;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for generating random alpha-numerical sequences.
 *
 * @author Merve
 */
public class RandomString {

    private final static String ALPHABET_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String NUMBERS = "0123456789";

    private final char[] symbols;
    private final int min;
    private final int max;

    public RandomString(int min, int max) {
        this.min = min;
        this.max = max;

        symbols = new StringBuilder(ALPHABET_UPPERCASE)
                .append(ALPHABET_UPPERCASE.toLowerCase())
                .append(NUMBERS)
                .toString()
                .toCharArray();
    }

    /**
     * Generates a random new string.
     *
     * @return random string.
     */
    public String nextString() {
        int range = ThreadLocalRandom.current().nextInt(min, max + 1);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < range; i++) {
            int random = ThreadLocalRandom.current().nextInt(symbols.length);
            char c = symbols[random];
            sb.append(c);
        }

        return sb.toString();
    }

}
