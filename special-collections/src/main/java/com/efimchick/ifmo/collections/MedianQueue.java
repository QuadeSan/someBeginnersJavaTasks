package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue extends AbstractQueue<Integer> {
    ArrayDeque<Integer> myQue = new ArrayDeque<>();
    Set<Integer> mySet = new TreeSet<>();

    @Override
    public Iterator<Integer> iterator() {
        return myQue.iterator();
    }

    @Override
    public int size() {
        return mySet.size();
    }

    @Override
    public boolean offer(Integer integer) {
        mySet.add(integer);
        return myQue.offer(integer);
    }

    @Override
    public Integer poll() {
        int result = findFirst(mySet);
        mySet.remove(result);
        myQue.offerFirst(result);
        myQue.poll();
        return result;
    }

    @Override
    public Integer peek() {
        return findFirst(mySet);
    }

    private Integer findFirst(Set<Integer> set) {
        Object[] myArray = set.toArray();
        if (set.size() <= 2) {
            return (Integer) myArray[0];
        }
        if (set.size() % 2 == 1) {
            int middlePosition = (myArray.length / 2);
            Integer result = (Integer) myArray[middlePosition];
            return result;
        }
        int middlePosition = (myArray.length / 2) - 1;
        Integer result = (Integer) myArray[middlePosition];
        return result;
    }
}
