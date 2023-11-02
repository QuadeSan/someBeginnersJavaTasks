package com.epam.rd.autotasks;


public class GraduallyDecreasingCarousel extends DecrementingCarousel{
    private final int CAPACITY;
    private int count = 0;
    private final int[] array;
    private boolean isRunning = false;

    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
        this.CAPACITY = capacity;
        this.array = new int[CAPACITY];
    }

    @Override
    public boolean addElement(int element) {
//        throw new UnsupportedOperationException();
        if (count < CAPACITY && !isRunning && element > 0) {
            array[count++] = element;
            return true;
        }
        return false;
    }

    @Override
    public CarouselRun run() {
//       throw new UnsupportedOperationException();
        if (!isRunning) {
            isRunning = true;
            return new GraduallyDecreasingCarouselRun(this.CAPACITY, this.array);
        } else {
            return null;
        }

    }
}
