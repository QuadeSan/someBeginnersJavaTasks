package com.epam.rd.autotasks;

import java.util.Arrays;

public class DecrementingCarouselWithLimitedRunRun extends CarouselRun {
    private final int capacity;
    private int[] array;
    private boolean hasNext = true;
    private int position = 0;
    private int actionLimit;
    private boolean finished = false;


    public DecrementingCarouselWithLimitedRunRun (int capacity, int[] array,int actionLimit) {
        super(capacity, array);
        this.capacity = capacity;
        this.array = array;
        this.actionLimit = actionLimit;
    }

    @Override
    public int next() {
//       throw new UnsupportedOperationException();
        actionLimit--;
        if (Arrays.equals(array, new int[array.length])) {
            hasNext = false;
            return -1;
        }
        if (hasNext) {
            while (array[position] == 0) {
                position++;
                if (position == capacity) {
                    break;
                }
            }
            if (position == capacity) {
                position = 0;
            }
            int[] arrayNew = Arrays.copyOf(array, capacity);
            for (int i = position, j = position; i < array.length; i++, j++) {
                if (array[i] > 0) {
                    int result = array[i];
                    arrayNew[j] = array[i] - 1;
                    array = arrayNew;
                    position++;
                    if (position == capacity) {
                        position = 0;
                    }
                    if (actionLimit == 0){
                        hasNext = false;
                    }
                    return result;

                } else {
                    position++;
                }
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
