package com.publicissapient.recruting.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    @Test
    public void should_create_a_mower_given_x_and_y_coordinates_and_an_orientation() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        // WHEN
        final Mower actualMower = new Mower(x, y, orientation);
        // THEN
        assertEquals(2, actualMower.getX());
        assertEquals(3, actualMower.getY());
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
    }

    @Test
    public void should_move_a_mower_leftward() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        final Mower mower = new Mower(x, y, orientation);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.WEST, mower.getOrientation());

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.SOUTH, mower.getOrientation());

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.EAST, mower.getOrientation());

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(Orientation.NORTH, mower.getOrientation());


    }

    @Test
    public void should_move_a_mower_rightward() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        final Mower mower = new Mower(x, y, orientation);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.EAST, mower.getOrientation());

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.SOUTH, mower.getOrientation());

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.WEST, mower.getOrientation());

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(Orientation.NORTH, mower.getOrientation());


    }
}
