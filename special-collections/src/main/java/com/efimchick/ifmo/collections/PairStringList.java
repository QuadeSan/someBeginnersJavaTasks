package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList implements List<String> {
    List<String> myList = new ArrayList<>();

    @Override
    public boolean add(String value) {
        boolean firstAdd = myList.add(value);
        boolean secondAdd = myList.add(value);
        return firstAdd && secondAdd;
    }

    @Override
    public boolean remove(Object o) {
        boolean firstRemove = myList.remove(o);
        boolean secondRemove = myList.remove(o);
        return firstRemove && secondRemove;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return myList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        for (int i = 0; i < c.size(); i++) {
            myList.add((String) c.toArray()[i]);
            myList.add((String) c.toArray()[i]);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        if (index % 2 == 1) {
            for (int i = 0; i < c.size(); i++) {
                myList.add(index + 1 + 2 * i, (String) c.toArray()[i]);
                myList.add(index + 1 + 2 * i, (String) c.toArray()[i]);
            }
        }
        if (index % 2 == 0) {
            for (int i = 0; i < c.size(); i++) {
                myList.add(index + 2 * i, (String) c.toArray()[i]);
                myList.add(index + 2 * i, (String) c.toArray()[i]);
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return myList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return myList.retainAll(c);
    }

    @Override
    public void clear() {
        myList.clear();
    }

    public void add(int index, String value) {
        if (index % 2 == 0) {
            myList.add(index, value);
            myList.add(index, value);
        }
        if (index % 2 == 1) {
            myList.add(index + 1, value);
            myList.add(index + 1, value);
        }
    }

    @Override
    public String remove(int index) {
        String result = myList.remove(index);
        if (index % 2 == 0) {
            myList.remove(index);
        }
        if (index % 2 == 1) {
            myList.remove(index - 1);
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        return myList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return myList.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return myList.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return myList.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return myList.subList(fromIndex, toIndex);
    }


    @Override
    public String get(int index) {
        return myList.get(index);
    }

    @Override
    public String set(int index, String element) {
        String result = myList.set(index, element);
        if (index % 2 == 0) {
            myList.set(index + 1, element);
        }
        if (index % 2 == 1) {
            myList.set(index - 1, element);
        }
        return result;
    }

    @Override
    public int size() {
        return myList.size();
    }

    @Override
    public boolean isEmpty() {
        return myList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return myList.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return myList.iterator();
    }

    @Override
    public Object[] toArray() {
        return myList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return myList.toArray(a);
    }
}
