package com.github.cc007.interfacesegregationdemo.containers.api.features.stream;

import java.util.stream.Stream;

public interface ParallelStreamable<T> {
    Stream<T> parallelStream();
}
