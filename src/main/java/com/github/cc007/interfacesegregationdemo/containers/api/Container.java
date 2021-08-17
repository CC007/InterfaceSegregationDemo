package com.github.cc007.interfacesegregationdemo.containers.api;

import com.github.cc007.interfacesegregationdemo.containers.api.features.Removable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.stream.Streamable;

public interface Container<E> extends Removable<E>, Streamable<E> {

    // Query Operations
    boolean isEmpty();
    boolean contains(Object o);
}
