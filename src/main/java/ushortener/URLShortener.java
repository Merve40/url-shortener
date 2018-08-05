package ushortener;

/**
 * Interface for implementing a URL shortener algorithm.
 *
 * @author Merve
 */
public interface URLShortener {

    /**
     * Shortens the given URL.
     *
     * @param url
     * @return index/short URL
     */
    String shorten(String url);
}
