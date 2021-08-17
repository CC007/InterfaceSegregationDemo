package com.github.cc007.interfacesegregationdemo.containers.api.features.index;

import com.github.cc007.interfacesegregationdemo.containers.api.features.Ordered;

public interface Indexed<T> extends Ordered<T> {
    int indexOf(T o);
    int lastIndexOf(T o);
}
