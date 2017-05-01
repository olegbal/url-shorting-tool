package com.github.olegbal.urlshortingtool.utils.encrypters;

public class UrlShortener {

    public String shortUrl(String text) {

        long csumm = new Crc32CheckSum().calculate(text);

        return Base62.encode(csumm);
    }
}
