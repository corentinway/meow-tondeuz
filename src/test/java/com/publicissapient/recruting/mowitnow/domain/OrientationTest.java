package com.publicissapient.recruting.mowitnow.domain;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {

    @Test
    public void should_have_for_orientations() {
        assertEquals("NORTH", Orientation.NORTH.name());
        assertEquals("SOUTH", Orientation.SOUTH.name());
        assertEquals("WEST", Orientation.WEST.name());
        assertEquals("EAST", Orientation.EAST.name());


    }

    @Test
    public void should_return_next_left_value() {
        assertEquals(Orientation.WEST, Orientation.NORTH.left());
        assertEquals(Orientation.SOUTH, Orientation.WEST.left());
        assertEquals(Orientation.EAST, Orientation.SOUTH.left());
        assertEquals(Orientation.NORTH, Orientation.EAST.left());
    }

    @Test
    public void should_return_next_right_value() {
        assertEquals(Orientation.EAST, Orientation.NORTH.right());
        assertEquals(Orientation.SOUTH, Orientation.EAST.right());
        assertEquals(Orientation.WEST, Orientation.SOUTH.right());
        assertEquals(Orientation.NORTH, Orientation.WEST.right());
    }


}
