package com.epam.rd.autotasks;

import java.util.Optional;

public enum Direction {
    N(0), NE(45),
    E(90), SE(135),
    S(180), SW(225),
    W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    public int getDegrees() {
        return degrees;
    }

    public static Direction ofDegrees(int degrees) {
//        throw new UnsupportedOperationException();
        if (degrees == 360) {
            return Direction.N;
        }
        if (degrees >= 0 && degrees < 360) {
            return Direction.getDir(degrees);
        }
        if (degrees > 360) {
            degrees = degrees % 360;
            return Direction.getDir(degrees);
        }
        degrees = degrees + 360;
        return Direction.getDir(degrees);
    }

    public static Direction getDir(int degrees) {
        for (Direction dir : Direction.values()) {
            if (dir.getDegrees() == degrees) {
                return dir;
            }
        }
        return null;
    }

    public static Direction closestToDegrees(int degrees) {
//        throw new UnsupportedOperationException();
        if (degrees > 360) {
            degrees = degrees % 360;
        }
        if (degrees % 45 == 0) {
            return Direction.ofDegrees(degrees);
        }
        int index = (degrees + 23) / 45;
        return Direction.values()[index];
    }


    public Direction opposite() {
//        throw new UnsupportedOperationException();
        return getDir((this.getDegrees() + 180) % 360);
    }

    public int differenceDegreesTo(Direction direction) {
//        throw new UnsupportedOperationException();
        if (this.getDegrees() == 0 && direction.getDegrees() > 180){
            return 360 - direction.getDegrees();
        }
        return Math.abs(this.getDegrees() - direction.getDegrees());
    }
}
