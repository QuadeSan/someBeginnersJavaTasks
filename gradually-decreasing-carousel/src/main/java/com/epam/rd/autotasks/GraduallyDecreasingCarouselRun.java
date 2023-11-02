package com.epam.rd.autotasks;

import java.util.Arrays;

public class GraduallyDecreasingCarouselRun extends CarouselRun {
    private final int capacity;
    private int[] array;
    private boolean hasNext = true;
    private int position = 0;
    private int decreasing = 1;

    public GraduallyDecreasingCarouselRun(int capacity, int[] array) {
        super(capacity, array);
        this.capacity = capacity;
        this.array = array;
    }

    @Override
    public int next() {
//       throw new UnsupportedOperationException();
        if (Arrays.equals(array, new int[array.length])) {
            hasNext = false;
            return -1;
        }
        if (hasNext) {
            if (position == capacity) {
                decreasing++;
                position = 0;
            }
            while (array[position] == 0) {
                position++;
                if (position % capacity == 0) {
                    position = 0;
                    decreasing++;
                }
            }
            if (array[position] > 0) {
                int result = array[position];
                array[position] = array[position] - decreasing;
                if (array[position] < 0) {
                    array[position] = 0;
                }
                position++;
                return result;
            } else {
                position++;
            }
        }
        return -1;
    }

    @Override
    public boolean isFinished() {
        if (Arrays.equals(array, new int[capacity])) {
            hasNext = false;
        }
        return !hasNext;
    }
}
