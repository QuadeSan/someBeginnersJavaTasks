package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    private Point c;
    private double r;

    public Circle(Point c, double r) {
        this.c = c;
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * (r*r);
    }

    @Override
    public String pointsToString() {
        return c.toString();
    }

    @Override
    public Point leftmostPoint() {
        double leftX = (c.getX()-r);
        return new Point(leftX,c.getY());
    }

    @Override
    public String toString() {
        return  "Circle" + "[" + c.toString() + r + "]";
    }
}
