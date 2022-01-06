package com.publicissapient.recruting.mowitnow.app;

import com.publicissapient.recruting.mowitnow.domain.IllegalInstructionException;
import com.publicissapient.recruting.mowitnow.domain.Instruction;
import com.publicissapient.recruting.mowitnow.domain.Mower;
import com.publicissapient.recruting.mowitnow.infrastructure.InstructionReader;

public class MowerInstructionService {

    private final InstructionReader instructionReader;

    public MowerInstructionService(InstructionReader instructionReader) {
        this.instructionReader = instructionReader;
    }

    public String mowTheSurface() throws IllegalInstructionException {
        final Instruction instruction = instructionReader.read();
        instruction.execute();

        return formatMowerPosition(instruction.getMower());

    }

    private String formatMowerPosition(Mower mower) {
        return String.format("%d %d %s",
                mower.getX(),
                mower.getY(),
                mower.getOrientation().name().charAt(0));
    }
}
