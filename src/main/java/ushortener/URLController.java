package ushortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class for handling requests for generating short URLs or
 * redirecting to absolute URLs.
 *
 * @author Merve
 */
@Controller
@RequestMapping("/")
public class URLController {

    private final URLService service;

    @Autowired
    public URLController(URLService service) {
        this.service = service;
    }

    /**
     * Default landing page.
     *
     * @return index.html
     */
    @GetMapping
    public String index() {
        return "index.html";
    }

    /**
     * Creates an index for the given url and saves it into the database.
     *
     * @param u URL
     * @return {@link ResponseEntity} containing the HTTP Status code and the
     * shortened url
     */
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<String> shortenURL(@RequestBody String u) {
        String url = service.addUrl(u);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    /**
     * Retrieves the absolute URL provided by the given index/short URL.
     *
     * @param shortUrl
     * @return response containing a redirect to the full URL
     */
    @GetMapping(value = "${domain.mapping}/{shortUrl}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getFullUrl(@PathVariable("shortUrl") String shortUrl) {
        String url = service.getUrl(shortUrl);

        if (url == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://" + url);
        ResponseEntity<String> response = new ResponseEntity<>(headers, HttpStatus.FOUND);
        return response;
    }
}
