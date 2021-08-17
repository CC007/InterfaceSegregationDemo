package com.github.cc007.interfacesegregationdemo.demo2;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Streamable<T> {

    Stream<T> stream();

    static <T> Streamable<T> ofIterable(Iterable<T> iterable) {
        return () -> StreamSupport.stream(iterable.spliterator(), false);
    }

    static <T> Streamable<T> ofCollection(Collection<T> collection) {
        return collection::stream;
    }
}
