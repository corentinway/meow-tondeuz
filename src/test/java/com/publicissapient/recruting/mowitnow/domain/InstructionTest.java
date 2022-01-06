package com.publicissapient.recruting.mowitnow.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InstructionTest {
    @Test
    void should_move_the_mower_to_the_target_position() throws MowerCoordinateOutOfBoundException {
        // GIVEN
        final Instruction actualInstruction = buildInstruction();
        // WHEN
        List<Mower> mowers = actualInstruction.execute();
        // THEN

        Mower actualMower = mowers.get(0);
        assertNotNull(actualMower);
        assertEquals(1, actualMower.getX());
        assertEquals(3, actualMower.getY());
        assertEquals(Orientation.NORTH, actualMower.getOrientation());

        actualMower = mowers.get(1);
        assertNotNull(actualMower);
        assertEquals(5, actualMower.getX());
        assertEquals(1, actualMower.getY());
        assertEquals(Orientation.EAST, actualMower.getOrientation());
    }

    private Instruction buildInstruction() throws MowerCoordinateOutOfBoundException {
        final Surface surface = new Surface(5, 5);
        List<Instruction.MowerAction> mowersActions = List.of(
                new Instruction.MowerAction( new Mower(surface, 1, 2, Orientation.NORTH), "GAGAGAGAA"),
                new Instruction.MowerAction( new Mower(surface, 3, 3, Orientation.EAST), "AADAADADDA")
        );
        return new Instruction(mowersActions);
    }
}
