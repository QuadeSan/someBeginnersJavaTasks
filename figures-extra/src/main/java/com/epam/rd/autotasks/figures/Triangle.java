package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a != null && b != null && c != null
                && !degenerative(a, b, c) && area(a, b, c) != 0) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean degenerative(Point a, Point b, Point c) {
        double r = a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY()) +
                c.getX() * (a.getY() - b.getY());
        return r == 0;
    }

    public static double area(Point a, Point b, Point c) {
        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();
        double cx = c.getX();
        double cy = c.getY();
        return Math.abs((ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2);
    }

    @Override
    public Point centroid() {
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
        double cx = this.c.getX();
        double cy = this.c.getY();
        double xTotal = ax + bx + cx;
        double yTotal = ay + by + cy;
        return new Point(xTotal / 3, yTotal / 3);
    }

    private Point[] getPoints(Triangle fig) {
        return new Point[]{fig.a, fig.b, fig.c};
    }

    @Override
    public boolean isTheSame(Figure figure) {
        int count = 0;
        if (this.getClass() == figure.getClass()) {
            Point[] array = getPoints(this);
            Point[] array2 = getPoints((Triangle) figure);
            for (Point point : array) {
                for (int i = 0; i < array.length; i++) {
                    if (point.samePoints(array2[i])) {
                        count++;
                    }
                }
            }
        }
        return count == 3;
    }
}
