package com.github.cc007.interfacesegregationdemo.containers.api.features.index;

import com.github.cc007.interfacesegregationdemo.containers.api.Collection;

public interface IndexAddable<T> extends Indexed<T>{
    void add(int index, T element);
    boolean addAll(int index, Collection<? extends T> c);
}
