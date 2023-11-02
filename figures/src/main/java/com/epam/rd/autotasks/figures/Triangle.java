package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
        double cx = this.c.getX();
        double cy = this.c.getY();
        return Math.abs((ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2);
    }

    @Override
    public String pointsToString() {
        return this.a.toString() + this.b.toString() + this.c.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point[] array = new Point[3];
        array[0] = this.a;
        array[1] = this.b;
        array[2] = this.c;
        Point lefX = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].getX() < lefX.getX()) {
                lefX = array[i];
            }
        }
        return lefX;
    }

    @Override
    public String toString() {
        return "Triangle" + "[" + a.toString() + b.toString() + c.toString() + "]";
    }

}
