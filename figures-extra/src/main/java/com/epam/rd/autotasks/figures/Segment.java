package com.epam.rd.autotasks.figures;

class Segment {
    Point start;
    Point end;

    public Segment(Point start, Point end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }

    Point intersection(Segment another) {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double x3 = another.start.getX();
        double x4 = another.end.getX();
        double y3 = another.start.getY();
        double y4 = another.end.getY();//
        double denominator = ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
        double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;
        if ((x1 >= x && x2 <= x) || (x1 <= x && x2 >= x)) {
            if ((x3 >= x && x4 <= x) || (x3 <= x && x4 >= x)) {
                return new Point(x, y);
            }
        }
//        double dx1 = x2 - x1;
//        double dy1 = y2 - y1;
//        double dx2 = x4 - x3;
//        double dy2 = y4 - y3;
//
//        double dxx = x1 - x3;
//        double dyy = y1 - y3;
//        double div, t, s;
//        div = dy2 * dx1 - dx2 * dy1;
//        if (Math.abs(div) < 1.0e-10) // лучше сравнивать abs(div) с маленьким Eps
//            return null;  // случай коллинеарности
//
//        t = (dx1*dyy-dy1*dxx) / div;
//        if (t < 0 || t > 1.0)
//            return null; // пересечение снаружи первого отрезка
//
//        s = (dx2 * dyy - dy2 * dxx) / div;
//        if (s < 0 || s > 1.0)
//            return null;  // пересечение снаружи второго отрезка
//        return new Point(x1  + s * dx1, y1 + s * dy1);
        return null;
    }
}
