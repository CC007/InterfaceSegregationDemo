package com.github.cc007.interfacesegregationdemo.containers;

public class LinkedList<E> extends DelegateList<E, java.util.LinkedList<E>> {
    public LinkedList() {
        super(new java.util.LinkedList<>());
    }
}
