package ushortener;

import java.util.Optional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class URLShortenerImplTest {

    private URLShortenerImpl shortener;
    @Mock
    private URLIndexRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shortener = new URLShortenerImpl();
        shortener.setMin(4);
        shortener.setMax(6);
        shortener.setRepository(repository);
    }

    @Test
    public void testShorten() {
        when(repository.findById(anyString())).thenReturn(Optional.<URLEntity>empty());

        String hash = shortener.shorten(null);

        assertFalse(hash.isEmpty());
        assertTrue(hash.length() >= 4 && hash.length() <= 6);

        verify(repository).findById(anyString());
    }

}
