package com.publicissapient.recruting.mowitnow.domain;

public class Mower {
    private int y;
    private int x;
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
            case 'A':
                goForward();
                break;
        }
    }

    private void goForward() {
        switch (orientation) {
            case NORTH:
                this.y += 1;
                break;
            case WEST:
                this.x -= 1;
                break;
            case SOUTH:
                this.y -= 1;
                break;
            case EAST:
                this.x += 1;
                break;
        }
    }

}
