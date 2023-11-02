package com.epam.rd.autotasks;

class CycleSwap {
    static void cycleSwap(int[] array) {
        cycleSwap(array,1);
    }

    static void cycleSwap(int[] array, int shift) {
        if (array.length == 0) {
            return;
        }
        int[] result = new int[array.length];
        System.arraycopy(array,0,result,shift,array.length-shift);
        System.arraycopy(array,array.length-shift,result,0,shift);
        System.arraycopy(result,0,array,0,result.length);
    }
}
