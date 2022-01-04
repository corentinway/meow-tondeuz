package com.publicissapient.recruting.mowitnow.domain;

public class Mower {
    /**
     * surface where the mower can move inside
     */
    private final Surface surface;
    private int y;
    private int x;
    private Orientation orientation;

    public Mower(Surface surface, int x, int y, Orientation orientation) throws MowerCoordinateOutOfBoundException {
        if ( x > surface.getWidth() ) {
            throw MowerCoordinateOutOfBoundException.outOfXException();
        }
        if ( y > surface.getHeight() ) {
            throw MowerCoordinateOutOfBoundException.outOfYException();
        }
        this.surface = surface;
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
