package com.github.cc007.interfacesegregationdemo.containers.api.features.iteration;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public interface Iterable<T> {

    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext()) {
            T t = iterator.next();
            action.accept(t);
        }
    }
}
