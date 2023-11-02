package com.epam.rd.autocode.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int j : array) {
            result.add(j);
            result.add(j);
        }
        return result.iterator();
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int j : array) {
            result.add(j);
            result.add(j);
            result.add(j);
        }
        return result.iterator();
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int j : array) {
            result.add(j);
            result.add(j);
            result.add(j);
            result.add(j);
            result.add(j);
        }
        return result.iterator();
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        List<String> res = new ArrayList<>();
        for (String column : columns) {
            for (int row : rows) {
                System.out.print(res.add(column + row));
            }
        }
        return res;
    }


}
