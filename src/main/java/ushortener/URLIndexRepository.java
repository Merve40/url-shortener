package ushortener;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface, which handles calls to the database.
 *
 * @author Merve
 */
@Transactional
public interface URLIndexRepository extends JpaRepository<URLEntity, String> {

    /**
     * Retrieves a URL-Index by the full URL.
     *
     * @param url
     * @return optional of {@link URLEntity}
     */
    Optional<URLEntity> findByUrl(String url);
}
