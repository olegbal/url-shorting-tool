package com.github.olegbal.urlshortingtool.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.olegbal.urlshortingtool.dto.LinkDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_links")
public class Link {

    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long linkId;

    @Column(name = "original_link",columnDefinition = "VARCHAR(255) CHARACTER SET binary")
    private String originalLink;

    @Column(name = "short_link",columnDefinition = "VARCHAR(255) CHARACTER SET binary")
    private String shortLink;

    @Column(name = "clicks_count")
    private int clicksCount;

    @Column(name = "tags")
    private String tags;

    @Column(name = "summary")
    private String summary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Link() {
    }

    public Link(String originalLink, String shortLink, int clicksCount, String tags, String summary, Date creationDate, User user) {
        this.originalLink = originalLink;
        this.shortLink = shortLink;
        this.clicksCount = clicksCount;
        this.tags = tags;
        this.summary = summary;
        this.creationDate = creationDate;
        this.user = user;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
