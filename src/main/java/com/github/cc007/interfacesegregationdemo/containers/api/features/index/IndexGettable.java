package com.github.cc007.interfacesegregationdemo.containers.api.features.index;

public interface IndexGettable<T> extends Indexed<T> {
    T get(int index);
}
