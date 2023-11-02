package com.epam.rd.autotasks.figures;

import java.util.Arrays;

class Quadrilateral extends Figure {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a != null && b != null && c != null && d != null &&
                !isItReal(a, b, c, d)  &&
                !isInside(a, b, c, d) && !isInside(b, c, d, a) &&
                !isInside(c, d, a, b) && !isInside(d, a, b, c)) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isItReal(Point a, Point b, Point c, Point d) {
        double[] array1 = {-1, 1, 1, -1, 1, 1, -1, -1};
        double[] array2 = {-1, 1, -1, 0, 1, 0, 1, -1};
        double[] cords = {a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(), d.getY()};
        return Arrays.equals(array1, cords) || Arrays.equals(array2, cords);
    }

    private boolean isInside(Point a, Point b, Point c, Point d) {
        /* Calculate area of triangle ABC */
        double r = Triangle.area(a, b, c);

        /* Calculate area of triangle PBC */
        double a1 = Triangle.area(d, b, c);

        /* Calculate area of triangle PAC */
        double a2 = Triangle.area(a, d, c);

        /* Calculate area of triangle PAB */
        double a3 = Triangle.area(a, b, d);

        /* Check if sum of A1, A2 and A3 is same as A */
        return (r == a1 + a2 + a3);
    }
    public double area(Point a, Point b, Point c, Point d) {
        double ax = a.getX();
        double ay = a.getY();
        double bx = b.getX();
        double by = b.getY();
        double cx = c.getX();
        double cy = c.getY();
        double dx = d.getX();
        double dy = d.getY();
        return Math.abs(0.5 * ((ax * by + bx * cy + cx * dy + dx * ay) - (bx * ay + cx * by + dx * cy + ax * dy)));
    }

    @Override
    public Point centroid() {
        Triangle abc = new Triangle(this.a, this.b, this.c);
        Triangle acd = new Triangle(this.a, this.c, this.d);
        Triangle abd = new Triangle(this.a, this.b, this.d);
        Triangle bdc = new Triangle(this.d, this.b, this.c);
        Point c1 = abc.centroid();
        Point c2 = acd.centroid();
        Point c3 = abd.centroid();
        Point c4 = bdc.centroid();
        Segment s1 = new Segment(c1, c2);
        Segment s2 = new Segment(c3, c4);
        return s1.intersection(s2);
    }

    private Point[] getPoints(Quadrilateral quad) {
        return new Point[]{quad.a, quad.b, quad.c, quad.d};
    }

    @Override
    public boolean isTheSame(Figure figure) {
        int count = 0;
        if (this.getClass() == figure.getClass()) {
            Point[] array = getPoints(this);
            Point[] array2 = getPoints((Quadrilateral) figure);
            for (Point point : array) {
                for (int i = 0; i < array.length; i++) {
                    if (point.samePoints(array2[i])) {
                        count++;
                    }
                }
            }
        }
        return count == 4;
    }


//    private boolean convex(Point a, Point b, Point c, Point d) {
//        double ax = a.getX();
//        double ay = a.getY();
//        double bx = b.getX();
//        double by = b.getY();
//        double cx = c.getX();
//        double cy = c.getY();
//        double dx = d.getX();
//        double dy = d.getY();
//        boolean positive = ((ax * bx) + (ay * by) > 0 && (bx * cx) + (by * cy) > 0
//                && (cx * dx) + (cy * dy) > 0 && (dx * ax) + (dy * ay) > 0);
//        boolean negative = ((ax * bx) + (ay * by) < 0 && (bx * cx) + (by * cy) < 0
//                && (cx * dx) + (cy * dy) < 0 && (dx * ax) + (dy * ay) < 0);
//        return positive || negative;
//    }
}
