package com.publicissapient.recruting.mowitnow.domain;

public class MowerCoordinateOutOfBoundException extends Exception {

    private MowerCoordinateOutOfBoundException(final String message) {
        super(message);
    }

    public static MowerCoordinateOutOfBoundException outOfXException() {
        return new MowerCoordinateOutOfBoundException("la coordonnée 'x' de la tondeuse est en dehors de la surface");
    }
    public static MowerCoordinateOutOfBoundException outOfYException() {
        return new MowerCoordinateOutOfBoundException("la coordonnée 'y' de la tondeuse est en dehors de la surface");
    }
}
