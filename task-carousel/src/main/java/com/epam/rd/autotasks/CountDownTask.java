package com.epam.rd.autotasks;

public class CountDownTask implements Task {

    private int value;

    public CountDownTask(int value) {
//        throw new UnsupportedOperationException();
        this.value = Math.max(value, 0);
    }

    public int getValue() {
//        throw new UnsupportedOperationException();
        return this.value;
    }


    @Override
    public void execute() {
//        throw new UnsupportedOperationException();
        if (value > 0) value--;
    }

    @Override
    public boolean isFinished() {
//        throw new UnsupportedOperationException();
        return value == 0;
    }
}
