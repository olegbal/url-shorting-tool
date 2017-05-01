package com.github.olegbal.urlshortingtool.utils;

import com.github.olegbal.urlshortingtool.utils.encrypters.Base62;
import com.github.olegbal.urlshortingtool.utils.encrypters.Crc32CheckSum;

public class UrlShortener {

    public String shortUrl(String text) {

        long csumm = new Crc32CheckSum().calculate(text);

        return Base62.encode(csumm);
    }
}
