package com.epam.rd.autotasks.intersection;

public class Line {
    int k;
    int b;

    public Line(int k, int b) {
        this.k = k;
        this.b = b;
    }

    public Point intersection(Line other) {
//        throw new UnsupportedOperationException();
        if (this.k == other.k) {
            return null;
        } else {
            int pointX = (other.b - this.b) / (this.k - other.k);
            int pointY = this.k * pointX + this.b;
            return new Point(pointX, pointY);
        }
    }

}
