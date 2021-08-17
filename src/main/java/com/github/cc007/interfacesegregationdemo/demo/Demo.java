package com.github.cc007.interfacesegregationdemo.demo;

import com.github.cc007.interfacesegregationdemo.containers.DelegateList;
import com.github.cc007.interfacesegregationdemo.containers.api.features.Addable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.RandomAccess;
import com.github.cc007.interfacesegregationdemo.containers.api.features.bulk.BulkAddable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.iteration.Iterable;
import com.github.cc007.interfacesegregationdemo.containers.api.features.stream.Streamable;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo {

    public static <SA extends Streamable<String> & Addable<String>,
            IA extends Iterable<String> & BulkAddable<String>,
            TS extends ThreadSafe & Streamable<String>>
    void main(String[] args) {
        FeatureFactory featureFactory = new FeatureFactory();
        SA stringAddableStreamable = getStringRandomAccessBulkAddableStreamableIterable(featureFactory);
        stringAddableStreamable.add("Hello");
        stringAddableStreamable.add("World");
        System.out.println(stringAddableStreamable.stream().collect(Collectors.joining(" ")));

        IA stringBulkAddableIterable = getStringNoRandomAccessBulkAddableStreamableIterable(featureFactory);
        stringBulkAddableIterable.addAll(new DelegateList<>(List.of("Lorum", "Ipsum", "Dolor", "Sit", "Amet")));
        printElements(stringBulkAddableIterable);

        CopyOnWriteList<String> stringCopyOnWriteList = new CopyOnWriteList<>();
        stringCopyOnWriteList.addAll(new DelegateList<>(List.of("Foo", "Bar")));
        featureFactory.addSupportedFeature(ThreadSafe.class);
        featureFactory.putSupportedImpl(CopyOnWriteList.class, () -> stringCopyOnWriteList);
        TS stringThreadsafeStreamable = getStringThreadsafeStreamable(featureFactory);
        stringThreadsafeStreamable.stream().forEach(System.out::println);
    }

    private static <T extends Streamable<String> & BulkAddable<String> & Iterable<String> & RandomAccess>
    T getStringRandomAccessBulkAddableStreamableIterable(FeatureFactory featureFactory) {
        Optional<T> container = featureFactory.getContainer(
                Iterable.class, Streamable.class, BulkAddable.class, RandomAccess.class
        );
        if (container.isPresent()) {
            System.out.println("Found type: " + container.get().getClass().getName());
            return container.get();
        } else {
            throw new NoSuchElementException("There is no container type that supports those features");
        }
    }

    private static <T extends Iterable<String> & Streamable<String> & BulkAddable<String>>
    T getStringNoRandomAccessBulkAddableStreamableIterable(FeatureFactory featureFactory) {
        Optional<T> container = featureFactory.getContainer(new DelegateList<>(List.of(
                Iterable.class, Streamable.class, BulkAddable.class
        )), new DelegateList<>(List.of(
                RandomAccess.class
        )));
        if (container.isPresent()) {
            System.out.println("Found type: " + container.get().getClass().getName());
            return container.get();
        } else {
            throw new NoSuchElementException("There is no container type that supports those features");
        }
    }

    private static <T extends ThreadSafe & Streamable<String>>
    T getStringThreadsafeStreamable(FeatureFactory featureFactory) {
        Optional<T> container = featureFactory.getContainer(
                Streamable.class, ThreadSafe.class
        );
        if (container.isPresent()) {
            System.out.println("Found type: " + container.get().getClass().getName());
            return container.get();
        } else {
            throw new NoSuchElementException("There is no container type that supports those features");
        }
    }

    private static void printElements(Iterable<String> stringBulkAddableIterable) {
        boolean first = true;
        Iterator<String> iterator = stringBulkAddableIterable.iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (first) {
                builder.append(element);
            } else {
                builder.append(" ")
                        .append(element.toLowerCase(Locale.ENGLISH));
            }
            first = false;
        }
        System.out.println(builder);
    }
}
