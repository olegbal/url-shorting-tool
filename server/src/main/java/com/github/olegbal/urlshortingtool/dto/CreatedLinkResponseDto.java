package com.github.olegbal.urlshortingtool.dto;

public class CreatedLinkResponseDto {

    private long LinkId;

    private String shortedLink;

    public CreatedLinkResponseDto() {
    }

    public CreatedLinkResponseDto(long linkId, String shortedLink) {
        LinkId = linkId;
        this.shortedLink = shortedLink;
    }

    public long getLinkId() {
        return LinkId;
    }

    public void setLinkId(long linkId) {
        LinkId = linkId;
    }

    public String getShortedLink() {
        return shortedLink;
    }

    public void setShortedLink(String shortedLink) {
        this.shortedLink = shortedLink;
    }
}
