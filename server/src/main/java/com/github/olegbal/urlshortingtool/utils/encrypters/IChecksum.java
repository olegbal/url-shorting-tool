package com.github.olegbal.urlshortingtool.utils.encrypters;

public interface IChecksum<T1> {

    T1 calculate(String text);

}
