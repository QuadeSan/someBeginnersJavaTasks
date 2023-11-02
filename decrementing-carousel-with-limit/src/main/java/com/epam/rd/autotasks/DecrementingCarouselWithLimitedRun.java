package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel{

    private final int CAPACITY;
    private int count = 0;
    private final int[] array;
    private boolean isRunning = false;
    private final int actionLimit;


    public DecrementingCarouselWithLimitedRun(final int capacity, final int actionLimit) {
        super(capacity);
        this.CAPACITY = capacity;
        this.array = new int[CAPACITY];
        this.actionLimit = actionLimit;
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
            return new DecrementingCarouselWithLimitedRunRun(this.CAPACITY, this.array,actionLimit);
        } else {
            return null;
        }

    }
}
