package com.github.cc007.interfacesegregationdemo.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Demo2 {
    public static void main(String[] args) {
        Streamable<String> streamableString = getStreamableString();
        System.out.println(streamableString.stream().collect(Collectors.joining(" ")));

        Traversable<String> traversableString = getTraversableString();
        printElements(traversableString);

        traversableString.stream().forEach(System.out::println);
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
