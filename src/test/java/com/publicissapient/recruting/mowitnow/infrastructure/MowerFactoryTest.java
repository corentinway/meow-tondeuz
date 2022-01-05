package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class MowerFactoryTest {
    @ParameterizedTest
    @CsvSource({
            "1 2 N,NORTH",
            "1 2 S,SOUTH",
            "1 2 E,EAST",
            "1 2 W,WEST"
    })
    public void should_return_the_first_mower_given_a_valid_instruction_file(String lineInstruction, String orientationName) throws IllegalInstructionException {
        // WHEN
        Surface surface = new Surface(5, 5);
        final Mower actualMower = MowerFactory.readMower(surface, lineInstruction, 1);
        // THEN
        assertNotNull(actualMower);
        assertEquals(1, actualMower.getX());
        assertEquals(2, actualMower.getY());
        assertEquals(Orientation.valueOf(orientationName), actualMower.getOrientation());
    }

    @ParameterizedTest
    @CsvSource({
            "invalidX 2 N,invalid X position of the mower at line 2 : invalidX,NumberFormatException",
            "1 invalidY N,invalid Y position of the mower at line 2 : invalidY,NumberFormatException",
            "1 2 Z,invalid orientation of the mower at line 2 : Z,",
            "100 2 N,invalid mower position line 2,MowerCoordinateOutOfBoundException"
    })
    public void should_throw_IllegalInstructionException_given_invalid_X_initial_mower_position(String instructionLine, String expectedMessage, String causeExceptionName) {
        // GIVEN
        final Surface surface = new Surface(5, 5);
        final int lineIndex = 1;
        // WHEN
        final IllegalInstructionException actualException = assertThrows(IllegalInstructionException.class,
                () -> MowerFactory.readMower(surface, instructionLine, lineIndex));
        // THEN
        assertEquals(expectedMessage, actualException.getMessage());
        if (actualException.getCause() != null) {
            assertEquals(causeExceptionName, actualException.getCause().getClass().getSimpleName());
        } else {
            assertEquals(causeExceptionName, actualException.getCause());
        }
    }


}
