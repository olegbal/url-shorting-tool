package com.github.olegbal.urlshortingtool.services.link;

import com.github.olegbal.urlshortingtool.encrypters.Base62;
import com.github.olegbal.urlshortingtool.encrypters.ChecksumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortener {

    private final ChecksumCalculator<Long> checksumCalculator;

    @Autowired
    public UrlShortener(final ChecksumCalculator<Long> checksumCalculator) {
        this.checksumCalculator = checksumCalculator;
    }

    public String shortUrl(String text) {
        long csumm = checksumCalculator.calculate(text);

        return Base62.encode(csumm);
    }
}
