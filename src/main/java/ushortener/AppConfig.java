package ushortener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for Spring specific operations.
 *
 * @author Merve
 */
@Configuration
@EnableJpaRepositories
public class AppConfig {

    /**
     * Injects an instance to an autowired {@link URLShortener}.
     *
     * @return implemented instance of URLShortener ({@link URLShortenerImpl})
     */
    @Bean
    public URLShortener urlShortener() {
        return new URLShortenerImpl();
    }
}
