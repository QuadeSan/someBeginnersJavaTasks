package com.epam.autotasks.collections;

import java.util.*;

class IntStringCappedMap extends AbstractMap<Integer, String> {
    private final static int INITIAL_CAPACITY = 30;
    public int totalLength = 0;
    private Node[] myMap = new Node[INITIAL_CAPACITY];
    private int size = 0;
    private final long capacity;

    public IntStringCappedMap(final long capacity) {
        this.capacity = capacity;
    }

    public long getCapacity() {
        return capacity;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return new AbstractSet<>() {
            @Override
            public Iterator<Entry<Integer, String>> iterator() {
                return new Iterator<>() {
                    int current = 0;

                    @Override
                    public boolean hasNext() {
                        //implement this method
//                        throw new UnsupportedOperationException();
                        return current < size;
                    }

                    @Override
                    public Entry<Integer, String> next() {
                        //implement this method
//                        throw new UnsupportedOperationException();
                        return myMap[current++];
                    }
                };
            }

            @Override
            public int size() {
                return IntStringCappedMap.this.size();
            }
        };
    }

    @Override
    public String get(final Object key) {
        //implement this method
//        throw new UnsupportedOperationException();
        return myMap[findElement(key)].value;
    }

    @Override
    public String put(final Integer key, final String value) {
        //implement this method
//        throw new UnsupportedOperationException();
        if (value.length() > capacity) {
            throw new IllegalArgumentException();
        }
        if (findElement(key) < 0) {
            totalLength += value.length();
            if (capacity >= totalLength) {
                myMap[size] = new Node(key, value);
                size++;
                return null;
            }
            while (capacity < totalLength) {
                removeAt(0);
            }
            myMap[size] = new Node(key, value);
            size++;
            return null;
        } else {
            String result = myMap[findElement(key)].value;
            removeAt(findElement(key));
            myMap[size] = new Node(key, value);
            size++;
            totalLength += value.length();
            if (capacity >= totalLength) {
                return result;
            }
            while (capacity < totalLength) {
                removeAt(0);
            }
            return result;
        }
    }

    @Override
    public String remove(final Object key) {
        //implement this method
//        throw new UnsupportedOperationException();
        int current = findElement(key);
        if (current >= 0) {
            String result = myMap[current].value;
            removeAt(current);
            return result;
        } else return null;
    }

    private void removeAt(int index) {
        totalLength -= myMap[index].value.length();
        for (int i = index; i < myMap.length - 1; i++) {
            myMap[i] = myMap[i + 1];
        }
        size--;
    }

    @Override
    public int size() {
        //implement this method
//        throw new UnsupportedOperationException();
        return this.size;
    }

    public int findElement(Object key) {
        for (int i = 0; i < size; i++) {
            if (myMap[i].key.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    static class Node implements Entry<Integer, String> {
        public Integer key;
        public String value;

        public Node(Integer key, String value) {
            this.key = key;
            this.value = value;

        }

        @Override
        public Integer getKey() {
            return this.key;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String setValue(String value) {
            String oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

}
