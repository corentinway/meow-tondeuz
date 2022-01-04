package com.publicissapient.recruting.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MowerTest {

    private static final Surface SURFACE = new Surface(5, 5);


    @Test
    public void should_create_a_mower_given_x_and_y_coordinates_and_an_orientation() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        // WHEN
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // THEN
        assertMowerPosition(actualMower, 2, 3);
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
    }


    @Test
    public void should_throw_exception_given_a_mower_out_of_x_limit() {
        // GIVEN
        final int x = 100;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        // WHEN
        final MowerCoordinateOutOfBoundException actualException = assertThrows(MowerCoordinateOutOfBoundException.class, () -> new Mower(SURFACE, x, y, orientation));
        // THEN
        assertEquals("la coordonnée 'x' de la tondeuse est en dehors de la surface", actualException.getMessage());

    }


    @Test
    public void should_throw_exception_given_a_mower_out_of_y_limit() {
        // GIVEN
        final int x = 4;
        final int y = 300;
        final Orientation orientation = Orientation.NORTH;
        // WHEN
        final MowerCoordinateOutOfBoundException actualException = assertThrows(MowerCoordinateOutOfBoundException.class, () -> new Mower(SURFACE, x, y, orientation));
        // THEN
        assertEquals("la coordonnée 'y' de la tondeuse est en dehors de la surface", actualException.getMessage());

    }

    // TODO add more context on exceptions


    private void assertMowerPosition(Mower actualMower, int expectedX, int expectedY) {
        assertEquals(expectedX, actualMower.getX());
        assertEquals(expectedY, actualMower.getY());
    }

    @Test
    public void should_move_a_mower_leftward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        final Mower mower = new Mower(SURFACE, x, y, orientation);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.WEST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.SOUTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.EAST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.NORTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);
    }

    @Test
    public void should_move_a_mower_rightward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        final Mower mower = new Mower(SURFACE, x, y, orientation);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.EAST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.SOUTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.WEST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.NORTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);
    }

    @Test
    public void should_move_the_mower_northward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 2, 4);
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
    }

    @Test
    public void should_move_the_mower_southward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.SOUTH;
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 2, 2);
        assertEquals(Orientation.SOUTH, actualMower.getOrientation());
    }

    @Test
    public void should_move_the_mower_westward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.WEST;
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 1, 3);
        assertEquals(Orientation.WEST, actualMower.getOrientation());
    }

    @Test
    public void should_move_the_mower_eastward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.EAST;
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 3, 3);
        assertEquals(Orientation.EAST, actualMower.getOrientation());
    }

    @Test
    public void should_move_to_1_3_N_given_the_initial_position_1_2_N_and_the_moves_GAGAGAGAA() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 1;
        final int y = 2;
        final Orientation orientation = Orientation.NORTH;
        final Mower mower = new Mower(SURFACE, x, y, orientation);
        final String actions = "GAGAGAGAA";
        // WHEN
        actions.chars().forEach(action -> mower.move((char) action));
        // THEN
        assertMowerPosition(mower, 1, 3);
        assertEquals(Orientation.NORTH, mower.getOrientation());
    }

    @Test
    public void should_move_to_5_1_E_given_the_initial_position_3_3_E_and_the_moves_AADAADADDA() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 3;
        final int y = 3;
        final Orientation orientation = Orientation.EAST;
        final Mower mower = new Mower(SURFACE, x, y, orientation);
        final String actions = "AADAADADDA";
        // WHEN
        actions.chars().forEach(action -> mower.move((char) action));
        // THEN
        assertMowerPosition(mower, 5, 1);
        assertEquals(Orientation.EAST, mower.getOrientation());
    }
}
