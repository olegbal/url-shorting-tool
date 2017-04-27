package com.github.olegbal.urlshortingtool.converters;

import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

public abstract class SetConverter<T1, T2> implements Converter<T1, T2> {

    public Set<T2> convertSet(Set<T1> t1) {
        Set<T2> t2 = new HashSet<T2>();

        for (T1 o : t1) {
            t2.add(convert(o));
        }
        return t2;
    }
}
