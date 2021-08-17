package com.github.cc007.interfacesegregationdemo.demo;

import com.github.cc007.interfacesegregationdemo.containers.api.Collection;
import com.github.cc007.interfacesegregationdemo.containers.api.Container;
import com.github.cc007.interfacesegregationdemo.containers.api.features.bulk.BulkAddable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.stream.Streamable;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * Notice that this is not a {@link Container} or a {@link Collection},
 * but it can still be used in the {@link FeatureFactory}
 * @param <E>
 */
public class CopyOnWriteList<E> implements ThreadSafe, BulkAddable<E>, Streamable<E> {

    private final CopyOnWriteArrayList<E> tooLazyToCreateMyOwn;
    public CopyOnWriteList() {
        tooLazyToCreateMyOwn = new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean add(E e) {
        return tooLazyToCreateMyOwn.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return tooLazyToCreateMyOwn.addAll(c.toJavaUtil());
    }

    @Override
    public Stream<E> stream() {
        return tooLazyToCreateMyOwn.stream();
    }
}
