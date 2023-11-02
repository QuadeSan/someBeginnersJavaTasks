package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] result = new int[rows][columns];
        int top = 0, bottom = rows - 1, left = 0, right = columns - 1;

        int dir = 1;
        int current = 0;

        while (top <= bottom && left <= right) {

            if (dir == 1) {
                for (int i = left; i <= right; ++i) {
                   result[top][i] = ++current;
                }

                ++top;
                dir = 2;
            }
            else if (dir == 2) {
                for (int i = top; i <= bottom; ++i) {
                    result[i][right] = ++current;
                }

                --right;
                dir = 3;
            }
            else if (dir == 3) {
                for (int i = right; i >= left; --i) {
                    result[bottom][i] = ++current;
                }

                --bottom;
                dir = 4;
            }
            else if (dir == 4) {
                for (int i = bottom; i >= top; --i) {
                    result[i][left]= ++current;
                }

                ++left;
                dir = 1;
            }
        }

        return result;
    }
}
