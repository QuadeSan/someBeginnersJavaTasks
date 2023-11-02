package com.epam.rd.autotasks;


public class TaskCarousel {

    private final int capacity;
    private int taskLeft;
    private Task[] array;
    private int currentTask = 0;


    public TaskCarousel(int capacity) {
//        throw new UnsupportedOperationException();
        this.capacity = capacity;
        this.array = new Task[capacity];
    }


    public boolean addTask(Task task) {
//        throw new UnsupportedOperationException();
        if (task == null || task.isFinished() || isFull()) {
            return false;
        }
        taskLeft++;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = task;
                return true;
            }
        }
        return true;
    }

    public boolean execute() {
//        throw new UnsupportedOperationException();
        if (isEmpty()) {
            return false;
        }
        if (currentTask == capacity) {
            currentTask = 0;
        }
        while (array[currentTask] == null) {
            currentTask++;
            currentTask = currentTask % capacity;
        }
        if (array[currentTask] != null) {
            array[currentTask].execute();
            if (array[currentTask].isFinished()) {
                array[currentTask] = null;
                taskLeft--;
            }
            currentTask++;
        }
        return true;
    }

    public boolean isFull() {
//        throw new UnsupportedOperationException();
        return taskLeft == capacity;
    }

    public boolean isEmpty() {
//        throw new UnsupportedOperationException();
        return taskLeft == 0;
    }

}
