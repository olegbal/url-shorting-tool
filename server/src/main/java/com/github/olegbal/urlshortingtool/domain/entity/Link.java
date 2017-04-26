package com.github.olegbal.urlshortingtool.domain.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_links")
public class Link {

    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long linkId;

    @Column(name = "original_link")
    private String originalLink;

    @Column(name = "short_link")
    private String shortLink;

    @Column(name = "clicks_count")
    private int clicksCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Link() {
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public int getClicksCount() {
        return clicksCount;
    }

    public void setClicksCount(int clicksCount) {
        this.clicksCount = clicksCount;
    }
}
