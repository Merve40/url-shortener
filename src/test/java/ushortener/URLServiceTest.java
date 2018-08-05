package ushortener;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class URLServiceTest {

    @Mock
    private URLIndexRepository repository;
    @Mock
    private URLShortener shortener;
    private URLService service;
    private String url;
    private String hash;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new URLService(shortener, repository);
        service.setMapping("x");
        service.setName("");
        url = "www.netflix.com/watch/80205260?trackId=155573560";
        hash = "x9FGt";
    }

    @Test
    public void testAddUrl() {
        when(shortener.shorten(url)).thenReturn(hash);
        when(repository.findByUrl(url)).thenReturn(Optional.<URLEntity>empty());
        when(repository.save(any())).thenReturn(new URLEntity(hash, url));

        String actualHash = service.getMapping() + "/" + hash;
        String expectedHash = service.addUrl(url);

        assertEquals("Generated Hash doesn not equal: " + actualHash, expectedHash, actualHash);

        verify(shortener).shorten(url);
        verify(repository).findByUrl(url);
        verify(repository).save(any());
    }

    @Test
    public void testGetUrl() {
        when(repository.findById(hash)).thenReturn(Optional.<URLEntity>of(new URLEntity(hash, url)));

        String expectedUrl = service.getUrl(hash);
        assertEquals("URL does not equal: " + url, expectedUrl, url);

        verify(repository).findById(hash);
    }
}
