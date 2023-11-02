package com.epam.rd.autotasks.triangle;

class Triangle {
    Point a;
    Point b;
    Point c;

    public Triangle(Point a, Point b, Point c) {

        if (degenerative(a,b,c)) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
        double cx = this.c.getX();
        double cy = this.c.getY();
        return Math.abs((ax*(by-cy)+bx*(cy-ay)+cx*(ay-by))/2) ;

    }

    public Point centroid(){
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
        double cx = this.c.getX();
        double cy = this.c.getY();
        double xTotal = ax+bx+cx;
        double yTotal = ay+by+cy;
        return new Point(xTotal/3,yTotal/3);
    }

    boolean degenerative (Point a, Point b, Point c){
        double r = a.getX()*(b.getY()-c.getY()) + b.getX() * (c.getY()-a.getY()) +
                c.getX()*(a.getY()-b.getY());
        return r == 0;
    }


}
