package com.epam.rd.autotasks.figures;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean samePoints(Point another) {
        return Math.abs((this.getX() -
                (another.getX()))) < 0.000001
                && Math.abs(this.getY() -
                (another.getY())) < 0.000001;
    }

}
