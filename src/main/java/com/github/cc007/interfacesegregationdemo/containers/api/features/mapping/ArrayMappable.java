package com.github.cc007.interfacesegregationdemo.containers.api.features.mapping;

import java.util.function.IntFunction;

public interface ArrayMappable {
    Object[] toArray();
    <T> T[] toArray(T[] a);
    default <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }
}
