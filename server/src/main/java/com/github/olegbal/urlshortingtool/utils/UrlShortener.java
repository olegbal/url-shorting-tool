package com.github.olegbal.urlshortingtool.utils;

import com.github.olegbal.urlshortingtool.utils.encrypters.Base62;
import com.github.olegbal.urlshortingtool.utils.encrypters.Crc32CheckSum;
import com.github.olegbal.urlshortingtool.utils.encrypters.IChecksum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortener {

    private final IChecksum<Long> iChecksum;

    @Autowired
    public UrlShortener(IChecksum<Long> iChecksum) {
        this.iChecksum = iChecksum;
    }

    public String shortUrl(String text) {

        long csumm = iChecksum.calculate(text);

        return Base62.encode(csumm);
    }
}
