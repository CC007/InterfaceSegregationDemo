package com.github.cc007.interfacesegregationdemo.containers.api.features.index;

public interface IndexRemovable<T> extends Indexed<T> {
    T remove(int index);
}
