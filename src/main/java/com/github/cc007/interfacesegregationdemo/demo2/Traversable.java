package com.github.cc007.interfacesegregationdemo.demo2;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * An interface that specifies that its content can be traversed and acted upon,
 * either through iteration or using streams
 */
public interface Traversable<T> extends Streamable<T>, Iterable<T> {

    static <T> Traversable<T> ofIterable(Iterable<T> iterable) {
        return new Traversable<T>() {
            @Override
            public Stream<T> stream() {
                return StreamSupport.stream(iterable.spliterator(), false);
            }

            @Override
            public Iterator<T> iterator() {
                return iterable.iterator();
            }
        };
    }

    static <T> Traversable<T> ofStreamable(Streamable<T> streamable) {
        return new Traversable<T>() {
            @Override
            public Stream<T> stream() {
                return streamable.stream();
            }

            @Override
            public Iterator<T> iterator() {
                return streamable.stream().iterator();
            }
        };
    }

    static <T> Traversable<T> ofCollection(Collection<T> collection) {
        return new Traversable<T>() {
            @Override
            public Stream<T> stream() {
                return collection.stream();
            }

            @Override
            public Iterator<T> iterator() {
                return collection.iterator();
            }
        };
    }
}
