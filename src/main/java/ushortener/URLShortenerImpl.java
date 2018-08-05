package ushortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Custom implementation for shortening URLs randomly with given ranges of
 * digits.
 *
 * @author Merve
 */
@PropertySource("classpath:application.properties")
@ConfigurationProperties("url.length")
public class URLShortenerImpl implements URLShortener {

    private URLIndexRepository repository;
    private int max;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Generates a shortened URL.
     *
     * @param url absolute URL
     * @return shortened URL
     */
    @Override
    public String shorten(String url) {
        RandomString randomString = new RandomString(min, max);
        String hash = randomString.nextString();
        while (repository.findById(hash).isPresent()) {
            hash = randomString.nextString();
        }
        return hash;
    }

    @Autowired
    public void setRepository(URLIndexRepository repository) {
        this.repository = repository;
    }
}
