package com.epam.autotasks.collections;

import java.util.*;

class RangedOpsIntegerSet extends AbstractSet<Integer> {
    Set<Integer> mySet = new TreeSet<>();

    public boolean add(int fromInclusive, int toExclusive) {
//        throw new UnsupportedOperationException();
        int added = 0;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (mySet.add(i)) {
                added++;
            }
        }
        return added > 0;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
//        throw new UnsupportedOperationException();
        int deleted = 0;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (mySet.remove(i)) {
                deleted++;
            }
        }
        return deleted > 0;
    }

    @Override
    public boolean add(final Integer integer) {
//        throw new UnsupportedOperationException();
        return mySet.add(integer);
    }

    @Override
    public boolean remove(final Object o) {
//        throw new UnsupportedOperationException();
        return mySet.remove(o);
    }

    @Override
    public Iterator<Integer> iterator() {
//        throw new UnsupportedOperationException();
        return mySet.iterator();
    }

    @Override
    public int size() {
//        throw new UnsupportedOperationException();
        return mySet.size();
    }
}
