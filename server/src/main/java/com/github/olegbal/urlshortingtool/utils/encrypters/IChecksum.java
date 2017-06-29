package com.github.olegbal.urlshortingtool.utils.encrypters;


//FIXME name
public interface IChecksum<T1> {

    T1 calculate(String text);

}
