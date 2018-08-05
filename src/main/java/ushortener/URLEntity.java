package ushortener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Entity class representing a relational Table for persisting data.
 *
 * @author Merve
 */
@Entity
@Table(name = "urlindex")
public class URLEntity {

    public URLEntity() {
    }

    public URLEntity(String index, String url) {
        this.index = index;
        this.url = url;
    }

    @Id
    @Column(name = "index")
    private String index;

    @Lob
    @Column(name = "url")
    private String url;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlEntity{" + "index=" + index + ", url=" + url + '}';
    }
}
