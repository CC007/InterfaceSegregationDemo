package com.github.cc007.interfacesegregationdemo.demo2.interfaces;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

public class ParallelTraversables {

    /**
     * Interface to provide iterability and streamability, both sequential and possibly parallel
     *
     * If this interface is public, then the user can use the methods of all three interfaces in the returned value
     * If this interface is not public, then the user will have to choose one of the three interfaces to use its methods
     * (or the user will have to use generics with an unchecked typecast).
     *
     * @param <T> the type of the traversable elements
     */
    interface ParallelTraversable<T> extends Iterable<T>, Streamable<T>, ParallelStreamable<T> {
    }

    public static <T> ParallelTraversable<T> ofIterable(Iterable<T> iterable) {
        return new ParallelTraversable<T>() {
            @Override
            public Stream<T> stream() {
                return Streamable.ofIterable(iterable).stream();
            }

            @Override
            public Stream<T> parallelStream() {
                return ParallelStreamable.ofIterable(iterable).parallelStream();
            }

            @Override
            public Iterator<T> iterator() {
                return iterable.iterator();
            }
        };
    }

    public static <T> ParallelTraversable<T> ofStreamable(Streamable<T> streamable) {
        return new ParallelTraversable<T>() {
            @Override
            public Stream<T> stream() {
                return streamable.stream();
            }

            @Override
            public Stream<T> parallelStream() {
                return streamable.stream().parallel();
            }

            @Override
            public Iterator<T> iterator() {
                return streamable.stream().iterator();
            }
        };
    }

    public static <T> ParallelTraversable<T> ofCollection(Collection<T> collection) {
        return new ParallelTraversable<T>() {
            @Override
            public Stream<T> stream() {
                return collection.stream();
            }

            @Override
            public Stream<T> parallelStream() {
                return collection.parallelStream();
            }

            @Override
            public Iterator<T> iterator() {
                return collection.iterator();
            }
        };
    }
}
