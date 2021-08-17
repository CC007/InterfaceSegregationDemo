package com.github.cc007.interfacesegregationdemo.containers.api.features.bulk;

import com.github.cc007.interfacesegregationdemo.containers.api.Collection;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Removable;

public interface BulkRemovable<T> extends Removable<T> {
    boolean removeAll(Collection<?> c);
}
