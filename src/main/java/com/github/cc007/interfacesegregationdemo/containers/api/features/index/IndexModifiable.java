package com.github.cc007.interfacesegregationdemo.containers.api.features.index;

public interface IndexModifiable<T> extends Indexed<T>{
    T set(int index, T element);
}
