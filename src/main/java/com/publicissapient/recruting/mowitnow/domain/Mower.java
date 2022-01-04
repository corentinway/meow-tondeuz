package com.publicissapient.recruting.mowitnow.domain;

public class Mower {
    private final int y;
    private final int x;
    private Orientation orientation;

    public Mower(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void move(char action) {
        switch (action) {
            case 'G':
                this.orientation = this.orientation.left();
                break;
            case 'D':
                this.orientation = this.orientation.right();
                break;
        }
    }

}
