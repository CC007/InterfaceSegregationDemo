package com.github.cc007.interfacesegregationdemo.containers;

import com.github.cc007.interfacesegregationdemo.containers.api.Collection;
import com.github.cc007.interfacesegregationdemo.containers.api.List;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Ordered;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class DelegateList<E, L extends java.util.List<E>> implements List<E> {

    private final L delegate;

    @Override
    public L toJavaUtil() {
        return delegate;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return this.delegate.addAll(index, c.toJavaUtil());
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        this.delegate.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        this.delegate.sort(c);
    }

    @Override
    public E get(int index) {
        return this.delegate.get(index);
    }

    @Override
    public E set(int index, E element) {
        return this.delegate.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        this.delegate.add(index, element);
    }

    @Override
    public E remove(int index) {
        return this.delegate.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.delegate.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.delegate.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.delegate.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return this.delegate.listIterator(index);
    }

    @Override
    public List<E> subdivide(int fromIndex, int toIndex) {
        return new DelegateList<>(this.delegate.subList(fromIndex, toIndex));
    }

    @Override
    public Spliterator<E> spliterator() {
        return this.delegate.spliterator();
    }

    @Override
    public int size() {
        return this.delegate.size();
    }

    @Override
    public Object[] toArray() {
        return this.delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.delegate.toArray(a);
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return this.delegate.toArray(generator);
    }

    @Override
    public boolean add(E e) {
        return this.delegate.add(e);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.delegate.containsAll(c.toJavaUtil());
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return this.delegate.addAll(c.toJavaUtil());
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.delegate.removeAll(c.toJavaUtil());
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.delegate.retainAll(c.toJavaUtil());
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return this.delegate.removeIf(filter);
    }

    @Override
    public Stream<E> stream() {
        return this.delegate.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return this.delegate.parallelStream();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.delegate.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return this.delegate.remove(o);
    }

    @Override
    public void clear() {
        this.delegate.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return this.delegate.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        this.delegate.forEach(action);
    }

    @Override
    public E getFirst() {
        return this.delegate.get(0);
    }

    @Override
    public Ordered<E> skipFirst() {
        return this.subdivide(1, size() - 1);
    }
}
