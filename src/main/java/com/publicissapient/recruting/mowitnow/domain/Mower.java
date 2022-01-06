package com.publicissapient.recruting.mowitnow.domain;

public class Mower {
    /**
     * surface where the mower can move inside
     */
    private int y;
    private int x;
    private Orientation orientation;

    public Mower(Surface surface, int x, int y, Orientation orientation) throws MowerCoordinateOutOfBoundException {
        validateMowerPosition(surface, x, y);
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    private void validateMowerPosition(Surface surface, int x, int y) throws MowerCoordinateOutOfBoundException {
        if (x < 0 || x > surface.getWidth()) {
            throw MowerCoordinateOutOfBoundException.outOfXException();
        }
        if (y < 0 || y > surface.getHeight()) {
            throw MowerCoordinateOutOfBoundException.outOfYException();
        }
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
        /*
        we could use Enum to represent actions.
        I choose `char` type because it is
        lighter in the VM and managed in the stack
         */
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
            default:
                // ignore action that don't match expected values : 'G', 'D', 'A'
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
