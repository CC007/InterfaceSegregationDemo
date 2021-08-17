package com.github.cc007.interfacesegregationdemo.containers.api.features;

import java.util.function.Predicate;

public interface Removable<T> {
    boolean remove(Object o);
    boolean removeIf(Predicate<? super T> filter);
    void clear();
}
