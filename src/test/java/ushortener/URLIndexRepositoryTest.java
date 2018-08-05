package ushortener;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class URLIndexRepositoryTest {

    @Mock
    private URLIndexRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByUrl() {
        when(repository.findByUrl("www.youtube.com/watch?v=WPvGqX-TXP0")).thenReturn(Optional.<URLEntity>of(new URLEntity("8iKt", "www.youtube.com/watch?v=WPvGqX-TXP0")));

        Optional<URLEntity> opUe = repository.findByUrl("www.youtube.com/watch?v=WPvGqX-TXP0");
        assertTrue(opUe.isPresent());
        assertEquals(opUe.get().getIndex(), "8iKt");

        verify(repository).findByUrl("www.youtube.com/watch?v=WPvGqX-TXP0");
    }

    @Test
    public void testFindByUrlResultIsEmpty() {
        when(repository.findByUrl("www.google.de/")).thenReturn(Optional.<URLEntity>empty());

        Optional<URLEntity> opUe = repository.findByUrl("www.google.de/");
        assertTrue(!opUe.isPresent());

        verify(repository).findByUrl("www.google.de/");
    }
}
