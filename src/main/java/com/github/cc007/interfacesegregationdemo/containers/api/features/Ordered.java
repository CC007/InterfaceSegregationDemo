package com.github.cc007.interfacesegregationdemo.containers.api.features;

import com.github.cc007.interfacesegregationdemo.containers.api.features.iteration.Iterable;

public interface Ordered<T> extends Iterable<T> {
    T getFirst();
    Ordered<T> skipFirst();
}
