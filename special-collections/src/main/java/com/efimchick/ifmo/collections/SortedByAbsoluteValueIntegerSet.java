package com.efimchick.ifmo.collections;


import java.util.*;

class SortedByAbsoluteValueIntegerSet implements Set<Integer> {

    Set<Integer> mySet = new TreeSet<>(Comparator.comparingInt(Math::abs));


    @Override
    public int size() {
        return mySet.size();
    }

    @Override
    public boolean isEmpty() {
        return mySet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return mySet.contains(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return mySet.iterator();
    }

    @Override
    public Object[] toArray() {
        return mySet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return mySet.toArray(a);
    }

    @Override
    public boolean add(Integer integer) {
        return mySet.add(integer);
    }

    @Override
    public boolean remove(Object o) {
        return mySet.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return mySet.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return mySet.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return mySet.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return mySet.removeAll(c);
    }

    @Override
    public void clear() {
        mySet.clear();
    }
}
