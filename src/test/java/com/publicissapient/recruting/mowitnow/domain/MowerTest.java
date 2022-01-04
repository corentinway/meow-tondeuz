package com.publicissapient.recruting.mowitnow.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.publicissapient.recruting.mowitnow.domain.Orientation.NORTH;
import static com.publicissapient.recruting.mowitnow.domain.Orientation.SOUTH;
import static com.publicissapient.recruting.mowitnow.domain.Orientation.WEST;
import static com.publicissapient.recruting.mowitnow.domain.Orientation.EAST;

public class MowerTest {

    private static final Surface SURFACE = new Surface(5, 5);


    @Test
    public void should_create_a_mower_given_x_and_y_coordinates_and_an_orientation() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = NORTH;
        // WHEN
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // THEN
        assertMowerPosition(actualMower, 2, 3);
        assertEquals(NORTH, actualMower.getOrientation());
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForOutOfSurfaceTest")
    public void should_throw_exception_given_mower_position_created_out_of_the_surface(int x, int y, Orientation orientation, String expectedMessage) {
        // WHEN
        final MowerCoordinateOutOfBoundException actualException = assertThrows(MowerCoordinateOutOfBoundException.class,
                () -> new Mower(SURFACE, x, y, orientation));
        // THEN
        assertEquals(expectedMessage, actualException.getMessage());
    }

    private static Stream<Arguments> provideArgumentsForOutOfSurfaceTest() {
        return Stream.of(
            Arguments.of(100, 3, NORTH, "la coordonnée 'x' de la tondeuse est en dehors de la surface" ),
            Arguments.of(-2, 3, NORTH, "la coordonnée 'x' de la tondeuse est en dehors de la surface" ),
            Arguments.of(2, 300, NORTH, "la coordonnée 'y' de la tondeuse est en dehors de la surface" ),
            Arguments.of(3, -43, NORTH, "la coordonnée 'y' de la tondeuse est en dehors de la surface" )
        );
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
        final Orientation orientation = NORTH;
        final Mower mower = new Mower(SURFACE, x, y, orientation);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(WEST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(SOUTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(EAST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('G');
        // THEN
        assertEquals(NORTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);
    }

    @Test
    public void should_move_a_mower_rightward() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 2;
        final int y = 3;
        final Orientation orientation = NORTH;
        final Mower mower = new Mower(SURFACE, x, y, orientation);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(EAST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(SOUTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(WEST, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);

        // WHEN
        mower.move('D');
        // THEN
        assertEquals(NORTH, mower.getOrientation());
        assertMowerPosition(mower, 2, 3);
    }


    @ParameterizedTest
    @MethodSource("provideArgumentsForMoveForwardsTest")
    public void should_move_the_mower_forward(int x, int y, Orientation orientation,
                                              int expectedX, int expectedY, Orientation expectedOrientation) throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final Mower actualMower = new Mower(SURFACE, x, y, orientation);
        // WHEN
        actualMower.move('A');
        // THEN
        assertMowerPosition(actualMower, expectedX, expectedY);
        assertEquals(expectedOrientation, actualMower.getOrientation());
    }
    
    private static Stream<Arguments> provideArgumentsForMoveForwardsTest() {
        final int actualX = 2;
        final int actualY = 3;
        return Stream.of(
            Arguments.of(actualX, actualY, NORTH, actualX, 4, NORTH),
            Arguments.of(actualX, actualY, SOUTH, actualX, 2, SOUTH),
            Arguments.of(actualX, actualY, WEST, 1, actualY, WEST),
            Arguments.of(actualX, actualY, EAST, 3, actualY, EAST)
        );
    }



    @Test
    public void should_move_to_1_3_N_given_the_initial_position_1_2_N_and_the_moves_GAGAGAGAA() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 1;
        final int y = 2;
        final Orientation orientation = NORTH;
        final Mower mower = new Mower(SURFACE, x, y, orientation);
        final String actions = "GAGAGAGAA";
        // WHEN
        actions.chars().forEach(action -> mower.move((char) action));
        // THEN
        assertMowerPosition(mower, 1, 3);
        assertEquals(NORTH, mower.getOrientation());
    }

    @Test
    public void should_move_to_5_1_E_given_the_initial_position_3_3_E_and_the_moves_AADAADADDA() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final int x = 3;
        final int y = 3;
        final Orientation orientation = EAST;
        final Mower mower = new Mower(SURFACE, x, y, orientation);
        final String actions = "AADAADADDA";
        // WHEN
        actions.chars().forEach(action -> mower.move((char) action));
        // THEN
        assertMowerPosition(mower, 5, 1);
        assertEquals(EAST, mower.getOrientation());
    }
}
