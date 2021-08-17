package com.github.cc007.interfacesegregationdemo.containers;

import com.github.cc007.interfacesegregationdemo.containers.api.Container;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Addable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Gettable;

import lombok.AllArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@AllArgsConstructor
public class Optional<T> implements Container<T>, Addable<T>, Gettable<T> {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private java.util.Optional<T> delegate;

    public Optional(T value) {
        this.delegate = java.util.Optional.ofNullable(value);
    }

    java.util.Optional<T> toJavaUtil() {
        return delegate;
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public boolean isPresent() {
        return delegate.isPresent();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.isPresent() && delegate.get().equals(o);
    }

    @Override
    public boolean add(T e) {
        if (delegate.isPresent()) {
            if (delegate.get().equals(e)) {
                return false;
            }
            throw new IllegalStateException("Object already present");
        }
        delegate = java.util.Optional.ofNullable(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o)) {
            return false;
        }
        delegate = java.util.Optional.empty();
        return true;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        boolean wasPresent = delegate.isPresent();
        delegate = delegate.filter(filter);
        return wasPresent && delegate.isEmpty();
    }

    @Override
    public void clear() {
        delegate = java.util.Optional.empty();
    }

    @Override
    public Stream<T> stream() {
        return delegate.stream();
    }

    @Override
    public T get() {
        //noinspection OptionalGetWithoutIsPresent
        return delegate.get();
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        return new Optional<>(this.delegate.filter(predicate));
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        return new Optional<>(delegate.map(mapper));
    }

    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) {
        return new Optional<>(delegate.flatMap(value -> mapper.apply(value).toJavaUtil()));
    }

    public void ifPresent(Consumer<? super T> action) {
        this.delegate.ifPresent(action);
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        this.delegate.ifPresentOrElse(action, emptyAction);
    }

    public java.util.Optional<T> or(Supplier<? extends java.util.Optional<? extends T>> supplier) {
        return this.delegate.or(supplier);
    }

    public T orElse(T other) {
        return this.delegate.orElse(other);
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return this.delegate.orElseGet(supplier);
    }

    public T orElseThrow() {
        return this.delegate.orElseThrow();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return this.delegate.orElseThrow(exceptionSupplier);
    }
}
