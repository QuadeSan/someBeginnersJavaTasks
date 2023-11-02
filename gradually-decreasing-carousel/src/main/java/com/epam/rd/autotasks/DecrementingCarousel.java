package com.epam.rd.autotasks;

public class DecrementingCarousel {
    private final int CAPACITY;
    private int count = 0;
    private final int[] array;
    private boolean isRunning = false;

    public DecrementingCarousel(int capacity) {
        this.CAPACITY = capacity;
        this.array = new int[CAPACITY];
    }

    public boolean addElement(int element) {
//        throw new UnsupportedOperationException();
        if (count < CAPACITY && !isRunning && element > 0) {
            array[count++] = element;
            return true;
        }
        return false;
    }

    public CarouselRun run() {
//       throw new UnsupportedOperationException();
        if (!isRunning) {
            isRunning = true;
            return new CarouselRun(this.CAPACITY, this.array);
        } else {
            return null;
        }

    }
}
