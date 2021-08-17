package com.github.cc007.interfacesegregationdemo.containers.api.features.sort;

import java.util.Comparator;

public interface SelfSortable<T> {
    void sort(Comparator<? super T> c);
}
