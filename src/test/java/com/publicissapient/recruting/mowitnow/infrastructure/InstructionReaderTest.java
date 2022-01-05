package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test for reading an instruction file like this :
 * <pre>
 *          5 5
 *          1 2 N
 *          GAGAGAGAA
 *          3 3 E
 *          AADAADADDA
 *  </pre>
 */
public class InstructionReaderTest {

    private static final String INSTRUCTION_FILENAME = "./src/test/resources/instructions.txt";

    private InstructionReader sut;

    @BeforeEach
    public void setUpInstructionReader() {
        sut = new InstructionReader(INSTRUCTION_FILENAME);
    }

    @Test
    public void should_throw_exception_given_io_exception_when_reading_instruction_file() {
        // GIVEN
        final String notExistingFileName = "I don't exists";
        // WHEN
        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> new InstructionReader(notExistingFileName));
        // THEN
        assertEquals("Invalid file", actualException.getMessage());
        assertTrue(actualException.getCause() instanceof IOException);
    }

    @Test
    public void should_read_and_return_a_surface_given_a_valid_instruction_file() throws IllegalInstructionException {
        // WHEN
        final Instruction actualInstruction = sut.read();
        // THEN
        final Surface actualSurface = actualInstruction.getSurface();
        assertNotNull(actualSurface);
        assertEquals(5, actualSurface.getWidth());
        assertEquals(5, actualSurface.getHeight());
    }

    @Test
    public void should_the_first_mower_given_a_valid_instruction_file() throws IllegalInstructionException {
        // WHEN
        final Instruction actualInstruction = sut.read();
        // THEN
        final Mower actualMower = actualInstruction.getMower();
        assertNotNull(actualMower);
        assertEquals(1, actualMower.getX());
        assertEquals(2, actualMower.getY());
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
    }

    @Test
    public void should_move_the_mower_to_the_target_position() throws IllegalInstructionException {
        // WHEN
        final Instruction actualInstruction = sut.read();
        actualInstruction.execute();
        // THEN
        final Mower actualMower = actualInstruction.getMower();
        assertNotNull(actualMower);
        assertEquals(1, actualMower.getX());
        assertEquals(3, actualMower.getY());
        assertEquals(Orientation.NORTH, actualMower.getOrientation());

    }

    @ParameterizedTest
    @CsvSource({
            "instructions_invalid_X.txt,invalid X position of the mower at line 2 : invalidX,NumberFormatException",
            "instructions_invalid_Y.txt,invalid Y position of the mower at line 2 : invalidY,NumberFormatException",
            "instructions_invalid_Orientation.txt,invalid orientation of the mower at line 2 : Z,",
            "instructions_mower_out_of_surface.txt,invalid mower position line 2,MowerCoordinateOutOfBoundException"
    })
    public void should_throw_IllegalInstructionException_given_invalid_X_initial_mower_position(String instructionFile, String expectedMessage, String causeExceptionName) {
        // GIVEN
        final InstructionReader reader = new InstructionReader("src/test/resources/" + instructionFile);
        // WHEN
        final IllegalInstructionException actualException = assertThrows(IllegalInstructionException.class, () -> reader.read());
        // THEN
        assertEquals(expectedMessage, actualException.getMessage());
        if (actualException.getCause() != null) {
            assertEquals(causeExceptionName, actualException.getCause().getClass().getSimpleName());
        } else {
            assertEquals(causeExceptionName, actualException.getCause());
        }
    }
}
