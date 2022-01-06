package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.List;

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
class FileInstructionReaderTest {

    private static final String INSTRUCTION_FILENAME = "./src/test/resources/instructions.txt";

    private FileInstructionReader sut;

    @BeforeEach
    void setUpInstructionReader() throws IllegalInstructionException {
        sut = new FileInstructionReader(INSTRUCTION_FILENAME);
    }

    @Test
    void should_throw_exception_given_io_exception_when_reading_instruction_file() {
        // GIVEN
        final String notExistingFileName = "I don't exists";
        // WHEN
        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> new FileInstructionReader(notExistingFileName));
        // THEN
        assertEquals("Invalid file", actualException.getMessage());
        assertTrue(actualException.getCause() instanceof IOException);
    }


    @Test
    void should_read_successfully_the_instruction() throws IllegalInstructionException {
        // WHEN
        final Instruction actualInstruction = sut.read();
        // THEN
        final List<Instruction.MowerAction> mowers = actualInstruction.getMowersAndActions();

        Mower actualMower = mowers.get(0).getMower();
        assertNotNull(actualMower);
        assertEquals(1, actualMower.getX());
        assertEquals(2, actualMower.getY());
        assertEquals(Orientation.NORTH, actualMower.getOrientation());
        assertEquals("GAGAGAGAA", mowers.get(0).getActions());

        actualMower = mowers.get(1).getMower();
        assertNotNull(actualMower);
        assertEquals(3, actualMower.getX());
        assertEquals(3, actualMower.getY());
        assertEquals(Orientation.EAST, actualMower.getOrientation());
        assertEquals("AADAADADDA", mowers.get(1).getActions());
    }

    @ParameterizedTest
    @ValueSource(strings = {"instructions_line_count_1.txt", "instructions_line_count_2.txt"})
    void should_throw_exception_given_file_with_less_than_3_lines(String filename) {

        IllegalInstructionException exception = assertThrows(IllegalInstructionException.class,
                () -> new FileInstructionReader("src/test/resources/" + filename));

        assertEquals("File instruction should have 3 lines at least", exception.getMessage());

    }
    @Test
    void should_throw_exception_given_file_with_not_odd_number_of_lines() {
        // GIVEN
        final String filename = "instruction_not_odd_line_count.txt";
        // WHEN
        IllegalInstructionException exception = assertThrows(IllegalInstructionException.class,
                () -> new FileInstructionReader("src/test/resources/" + filename));
        // THEN
        assertEquals("File instruction should have odd number of lines", exception.getMessage());

    }


}
