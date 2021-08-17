package com.github.cc007.interfacesegregationdemo.demo2.interfaces;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface ParallelStreamable<T> {

    Stream<T> parallelStream();

    static <T> ParallelStreamable<T> ofIterable(Iterable<T> iterable) {
        return () -> StreamSupport.stream(iterable.spliterator(), true);
    }

    static <T> ParallelStreamable<T> ofCollection(Collection<T> collection) {
        return collection::parallelStream;
    }
}
