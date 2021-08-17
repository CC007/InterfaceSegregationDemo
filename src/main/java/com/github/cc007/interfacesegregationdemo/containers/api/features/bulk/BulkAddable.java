package com.github.cc007.interfacesegregationdemo.containers.api.features.bulk;

import com.github.cc007.interfacesegregationdemo.containers.api.Collection;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Addable;

public interface BulkAddable<T> extends Addable<T> {
    boolean addAll(Collection<? extends T> c);
}
