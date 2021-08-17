package com.github.cc007.interfacesegregationdemo.containers.api;

import com.github.cc007.interfacesegregationdemo.containers.api.features.Subdividable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexAddable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexGettable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexModifiable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.index.IndexRemovable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.sort.SelfSortable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.UnaryOperator;

public interface List<E> extends Collection<E>,
        SelfSortable<E>, Subdividable<E, List<E>>,
        IndexGettable<E>, IndexAddable<E>, IndexModifiable<E>, IndexRemovable<E> {

    // Compatibility Operation
    java.util.List<E> toJavaUtil();


    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }


    // List Iterators
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);

    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this.toJavaUtil(), Spliterator.ORDERED);
    }

}
