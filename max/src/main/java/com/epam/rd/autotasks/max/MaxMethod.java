package com.epam.rd.autotasks.max;

public class MaxMethod {
    public static int max(int[] values) {
//        throw new UnsupportedOperationException();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            int curr = values[i];
            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }
}
