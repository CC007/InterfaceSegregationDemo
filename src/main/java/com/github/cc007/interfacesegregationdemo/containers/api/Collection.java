package com.github.cc007.interfacesegregationdemo.containers.api;

import com.github.cc007.interfacesegregationdemo.containers.api.features.mapping.ArrayMappable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.bulk.BulkAddable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.bulk.BulkRemovable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.iteration.Spliterable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.stream.ParallelStreamable;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<E> extends Container<E>,
        BulkAddable<E>, BulkRemovable<E>,
        Spliterable<E>, ParallelStreamable<E>,
        ArrayMappable {

    // Compatibility Operation
    java.util.Collection<E> toJavaUtil();

    // Query Operations
    int size();

    // Bulk Operations
    boolean containsAll(Collection<?> c);
    boolean retainAll(Collection<?> c);

    @Override
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    // Traversing Operations
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this.toJavaUtil(), 0);
    }

    @Override
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
