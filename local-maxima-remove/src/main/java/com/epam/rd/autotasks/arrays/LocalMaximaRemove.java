package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{-3, 2, 4, 3, 5, 12, 8};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array) {

        //put your code here
        int[] array2 = new int[array.length];
        int l = 0;
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == 0) {
                if (array[i] <= array[i + 1]) {
                    array2[j] = array[i];
                    l++;
                    j++;
                }
            } else if (i == array.length - 1) {
                if (array[i] <= array[i - 1]) {
                    array2[j] = array[i];
                    l++;
                    j++;
                }
            } else {
                if (array[i] <= array[i - 1] || array[i] <= array[i + 1]) {
                    array2[j] = array[i];
                    l++;
                    j++;
                }
            }
        }

        return Arrays.copyOf(array2, l);

//        throw new UnsupportedOperationException();
    }
}
