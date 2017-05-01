package com.github.olegbal.urlshortingtool.utils.encrypters;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Crc32CheckSum implements IChecksum {

    public Long calculate(String text) {

        Checksum checksum = new CRC32();

        checksum.update(text.getBytes(), 0, text.length());

        return checksum.getValue();
    }
}