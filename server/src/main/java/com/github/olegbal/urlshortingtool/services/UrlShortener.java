package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.utils.encrypters.Base62;
import com.github.olegbal.urlshortingtool.utils.encrypters.ChecksumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//FIXME find right place for dat
@Service
public class UrlShortener {

    private final ChecksumCalculator<Long> checksumCalculator;

    //FIXME finals in method's signatures
    @Autowired
    public UrlShortener(final ChecksumCalculator<Long> checksumCalculator) {
        this.checksumCalculator = checksumCalculator;
    }

    public String shortUrl(String text) {
        long csumm = checksumCalculator.calculate(text);

        return Base62.encode(csumm);
    }
}
