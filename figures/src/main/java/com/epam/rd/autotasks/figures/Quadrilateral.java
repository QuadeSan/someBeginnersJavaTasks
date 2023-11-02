package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure{
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
        double cx = this.c.getX();
        double cy = this.c.getY();
        double dx = this.d.getX();
        double dy = this.d.getY();
        return Math.abs(0.5 * ((ax*by+bx*cy+cx*dy+dx*ay)-(bx*ay+cx*by+dx*cy+ax*dy)));
    }

    @Override
    public String pointsToString() {
        return this.a.toString() + this.b.toString() + this.c.toString()+this.d.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point[] array = new Point[4];
        array[0] = this.a;
        array[1] = this.b;
        array[2] = this.c;
        array[3] = this.d;
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
        return "Quadrilateral" +
                "[" + a.toString() + b.toString() + c.toString() + d.toString() + "]";
    }
}
