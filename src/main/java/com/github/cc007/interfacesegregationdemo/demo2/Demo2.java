package com.github.cc007.interfacesegregationdemo.demo2;

import com.github.cc007.interfacesegregationdemo.demo2.interfaces.ParallelStreamable;
import com.github.cc007.interfacesegregationdemo.demo2.interfaces.ParallelTraversables;
import com.github.cc007.interfacesegregationdemo.demo2.interfaces.Streamable;
import com.github.cc007.interfacesegregationdemo.demo2.interfaces.Traversable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Demo2 {
    public static void main(String[] args) {
        Streamable<String> streamableString = getStreamableString();
        System.out.println(streamableString.stream().collect(Collectors.joining(" ")));

        Traversable<String> traversableString = getTraversableString();
        printElements(traversableString);

        traversableString.stream().forEach(System.out::println);

        ParallelStreamable<Integer> parallelStreamableInt = getParallelTraversableInt();
        System.out.println(parallelStreamableInt.parallelStream().reduce(Integer::max).orElseThrow());

        Iterable<Integer> iterableInt = getParallelTraversableInt();
        int sum = 0;
        for (int i : iterableInt) {
            sum += i;
        }
        System.out.println(sum);
    }

    private static Streamable<String> getStreamableString() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Hello");
        stringArrayList.add("World");
        return Streamable.ofCollection(stringArrayList);
    }

    private static Traversable<String> getTraversableString() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.addAll(List.of("Lorum", "Ipsum", "Dolor"));
        stringArrayList.addAll(List.of("Sit", "Amet"));
        return Traversable.ofCollection(stringArrayList);
    }

    @SuppressWarnings("unchecked")
    private static <T extends ParallelStreamable<Integer> & Streamable<Integer> & Iterable<Integer>> T getParallelTraversableInt() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerArrayList.add(i);
        }
        return (T) ParallelTraversables.ofCollection(integerArrayList);
    }

    private static void printElements(Iterable<String> stringBulkAddableIterable) {
        boolean first = true;
        StringBuilder builder = new StringBuilder();
        for (String element : stringBulkAddableIterable) {
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
