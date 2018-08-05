package ushortener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class URLControllerTest {

    private MockMvc mvc;
    @Mock
    private URLService service;
    private URLController controller;

    private String testURL;
    private String hash;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new URLController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).addPlaceholderValue("domain.mapping", "x").build();

        testURL = "https://www.netflix.com/watch/80205260?trackId=155573560";
        hash = "x9FGt";
    }

    @Test
    public void testIndex() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void testShortenURL() throws Exception {

        when(service.addUrl(testURL)).thenReturn(hash);

        mvc.perform(post("/add")
                .content(testURL)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(hash));

        verify(service).addUrl(testURL);
    }

    @Test
    public void testGetFullUrl() throws Exception {
        when(service.getUrl(hash)).thenReturn(testURL);

        mvc.perform(get("/x/{shortUrl}", hash))
                .andExpect(status().is3xxRedirection());

        verify(service).getUrl(hash);
    }

}
