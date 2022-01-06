package com.publicissapient.recruting.mowitnow;

import com.publicissapient.recruting.mowitnow.app.MowerInstructionService;
import com.publicissapient.recruting.mowitnow.domain.IllegalInstructionException;
import com.publicissapient.recruting.mowitnow.infrastructure.InstructionReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerInstructionServiceTest {

    private MowerInstructionService sut;

    class StringsInstructionReader extends InstructionReader {

        @Override
        protected Iterator<String> buildInstructionIterator() {
            return List.of(
                    "5 5",
                    "1 2 N",
                    "GAGAGAGAA"
            ).iterator();
        }
    }

    @BeforeEach
    public void setup() {
        sut = new MowerInstructionService(new StringsInstructionReader());
    }

    @Test
    public void should_move_the_mower_to_the_target_position() throws IllegalInstructionException {
        // WHEN
        final String targetPosition = sut.mowTheSurface();
        // THEN
        assertEquals("1 3 N", targetPosition);
    }
}
