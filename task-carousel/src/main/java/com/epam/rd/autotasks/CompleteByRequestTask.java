package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {

    private boolean finished = false;
    private int lastSpin = 0;

    @Override
    public void execute() {
//        throw new UnsupportedOperationException();
        if (lastSpin == 1) {
            lastSpin--;
            finished = true;
        }
    }

    @Override
    public boolean isFinished() {
//        throw new UnsupportedOperationException();
        return finished;
    }

    public void complete() {
//        throw new UnsupportedOperationException();
        lastSpin ++;
    }
}
