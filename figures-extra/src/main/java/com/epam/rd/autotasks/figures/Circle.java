package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private Point c;
    private double r;

    public Circle(Point c, double r) {
        if (c != null && r > 0) {
            this.c = c;
            this.r = r;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Point centroid() {
        return c;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return (this.getClass() == figure.getClass()) &&
                this.c.samePoints(((Circle) figure).c) &&
                (Math.abs(this.r - (((Circle) figure).r)) < 0.000001);
    }
}
