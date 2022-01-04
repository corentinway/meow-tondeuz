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
        assertMowerPosition(actualMower, 2, 3);
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
    }

    private void assertMowerPosition(Mower actualMower, int expectedX, int expectedY) {
        assertEquals(expectedX, actualMower.getX());
        assertEquals(expectedY, actualMower.getY());
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
    public void should_move_the_mower_northward() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.NORTH;
        final Mower actualMower = new Mower(x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 2, 4);
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
    }
    @Test
    public void should_move_the_mower_southward() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.SOUTH;
        final Mower actualMower = new Mower(x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 2, 2);
        assertEquals(Orientation.SOUTH, actualMower.getOrientation());
    }
    @Test
    public void should_move_the_mower_westward() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.WEST;
        final Mower actualMower = new Mower(x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 1, 3);
        assertEquals(Orientation.WEST, actualMower.getOrientation());
    }
    @Test
    public void should_move_the_mower_eastward() {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = Orientation.EAST;
        final Mower actualMower = new Mower(x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, 3, 3);
        assertEquals(Orientation.EAST, actualMower.getOrientation());
    }
}
