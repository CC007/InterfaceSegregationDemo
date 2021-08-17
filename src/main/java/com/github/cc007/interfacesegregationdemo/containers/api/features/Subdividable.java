package com.github.cc007.interfacesegregationdemo.containers.api.features;

import com.github.cc007.interfacesegregationdemo.containers.api.Container;

public interface Subdividable<E, C extends Container<E>> {
    C subdivide(int fromIndex, int toIndex);
}
