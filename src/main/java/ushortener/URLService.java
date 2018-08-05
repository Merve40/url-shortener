package ushortener;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Service class for processing URL input and the generation of shortened URL.
 *
 * @author Merve
 */
@Service
@PropertySource("classpath:application.properties")
@ConfigurationProperties("domain")
public class URLService {

    private String name;
    private String mapping;

    private final URLShortener shortener;
    private final URLIndexRepository repository;

    @Autowired
    public URLService(URLShortener shortener, URLIndexRepository repository) {
        this.shortener = shortener;
        this.repository = repository;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    /**
     * Adds a new URL to the database, if it does not exist yet.
     *
     * @param url to shorten
     * @return short URL
     */
    public String addUrl(String url) {
        String shortUrl = shortener.shorten(url);

        if (url.startsWith("https")) {
            url = url.split("https://")[1];
        } else if (url.startsWith("http")) {
            url = url.split("http://")[1];
        }

        Optional<URLEntity> opUrl = repository.findByUrl(url);
        if (opUrl.isPresent()) {
            return name + mapping + "/" + opUrl.get().getIndex();
        }
        URLEntity uEntity = new URLEntity(shortUrl, url);
        URLEntity saved = repository.save(uEntity);
        Logger.getLogger(URLService.class.getName()).log(Level.INFO, "Saved {0}", saved.toString());

        return name + mapping + "/" + shortUrl;
    }

    /**
     * Retrieves the full URL by the shortened one.
     *
     * @param index short URL
     * @return full URL
     */
    public String getUrl(String index) {
        Optional<URLEntity> opUrl = repository.findById(index);
        if (!opUrl.isPresent()) {
            return null;
        }
        return opUrl.get().getUrl();
    }

}
