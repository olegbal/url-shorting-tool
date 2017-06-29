package com.github.olegbal.urlshortingtool.utils;

import com.github.olegbal.urlshortingtool.utils.encrypters.Base62;
import com.github.olegbal.urlshortingtool.utils.encrypters.IChecksum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//FIXME find right place for dat
@Service
public class UrlShortener {

    private final IChecksum<Long> iChecksum;

    //FIXME finals in method's signatures
    @Autowired
    public UrlShortener(final IChecksum<Long> iChecksum) {
        this.iChecksum = iChecksum;
    }

    public String shortUrl(String text) {
        long csumm = iChecksum.calculate(text);

        return Base62.encode(csumm);
    }
}
