package com.github.olegbal.urlshortingtool.encrypters;


public interface ChecksumCalculator<T1> {

    T1 calculate(String text);

}
