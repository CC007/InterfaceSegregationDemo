package com.github.cc007.interfacesegregationdemo.containers;

import com.github.cc007.interfacesegregationdemo.containers.api.features.RandomAccess;

public class ArrayList<E> extends DelegateList<E, java.util.ArrayList<E>> implements RandomAccess {
    public ArrayList() {
        super(new java.util.ArrayList<>());
    }
}
