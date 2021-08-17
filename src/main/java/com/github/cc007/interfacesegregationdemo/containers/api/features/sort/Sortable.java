package com.github.cc007.interfacesegregationdemo.containers.api.features.sort;

import com.github.cc007.interfacesegregationdemo.containers.api.features.Ordered;

import java.util.Comparator;

public interface Sortable<T> {
    <C extends Ordered<T> & Sortable<T>> C sorted(Comparator<? super T> comparator);
}
