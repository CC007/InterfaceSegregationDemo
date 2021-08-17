package com.github.cc007.interfacesegregationdemo.containers.api.features.iteration;

import java.util.Spliterator;
import java.util.Spliterators;

public interface Spliterable<T> extends Iterable<T> {

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
