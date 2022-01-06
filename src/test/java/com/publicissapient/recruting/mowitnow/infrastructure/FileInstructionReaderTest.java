package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
public class FileInstructionReaderTest {

    private static final String INSTRUCTION_FILENAME = "./src/test/resources/instructions.txt";

    private FileInstructionReader sut;

    @BeforeEach
    public void setUpInstructionReader() {
        sut = new FileInstructionReader(INSTRUCTION_FILENAME);
    }

    @Test
    public void should_throw_exception_given_io_exception_when_reading_instruction_file() {
        // GIVEN
        final String notExistingFileName = "I don't exists";
        // WHEN
        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> new FileInstructionReader(notExistingFileName));
        // THEN
        assertEquals("Invalid file", actualException.getMessage());
        assertTrue(actualException.getCause() instanceof IOException);
    }


    @Test
    public void should_move_the_mower_to_the_target_position() throws IllegalInstructionException {
        // WHEN
        final Instruction actualInstruction = sut.read();
        actualInstruction.execute();
        // THEN
        final List<Mower> mowers = actualInstruction.getMowers();

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


}
