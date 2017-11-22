package com.github.olegbal.urlshortingtool.encrypters;

import org.springframework.stereotype.Component;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Component
public class Crc32CheckSum implements ChecksumCalculator {

    public Long calculate(String text) {
        Checksum checksum = new CRC32();

        checksum.update(text.getBytes(), 0, text.length());

        return checksum.getValue();
    }
}
