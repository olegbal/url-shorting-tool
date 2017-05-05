package com.github.olegbal.urlshortingtool.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LinkDto {

    private long linkId;

    private String originalLink;

    private String shortLink;

    private int clicksCount;

    private String tags;

    private String summary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date creationDate;

    public LinkDto() {
    }

    public LinkDto(long linkId, String originalLink, String shortLink, int clicksCount, String tags, String summary, Date creationDate) {
        this.linkId = linkId;
        this.originalLink = originalLink;
        this.shortLink = shortLink;
        this.clicksCount = clicksCount;
        this.tags = tags;
        this.summary = summary;
        this.creationDate = creationDate;
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
}
