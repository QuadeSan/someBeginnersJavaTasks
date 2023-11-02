package com.epam.rd.autotasks;

import org.junit.Test;

import static org.junit.Assert.*;


public class SortingTest {

    Sorting sorting = new Sorting();

    @Test(expected = IllegalArgumentException.class)
    public void testNullCase() {
        sorting.sort(null);
    }

    @Test
    public void testEmptyCase() {
        int[] empty = {};
        int[] expected = {};
        sorting.sort(empty);
        assertArrayEquals(expected, empty);
    }

    @Test
    public void testSingleElementArrayCase() {
        int[] array = {5};
        int[] expected = {5};
        sorting.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortedArraysCase() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        sorting.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testOtherCases() {
        int[] array = {3, 5, 2, 1, 4};
        int[] expected = {1, 2, 3, 4, 5};
        sorting.sort(array);
        assertArrayEquals(expected, array);
    }

}