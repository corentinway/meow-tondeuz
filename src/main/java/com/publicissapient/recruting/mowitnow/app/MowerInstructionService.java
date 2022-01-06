package com.publicissapient.recruting.mowitnow.app;

import com.publicissapient.recruting.mowitnow.domain.IllegalInstructionException;
import com.publicissapient.recruting.mowitnow.domain.Instruction;
import com.publicissapient.recruting.mowitnow.domain.Mower;
import com.publicissapient.recruting.mowitnow.infrastructure.InstructionReader;

import java.util.List;
import java.util.stream.Collectors;

public class MowerInstructionService {

    private final InstructionReader instructionReader;

    public MowerInstructionService(InstructionReader instructionReader) {
        this.instructionReader = instructionReader;
    }

    public String mowTheSurface() throws IllegalInstructionException {
        final Instruction instruction = instructionReader.read();
        final List<Mower> mowers = instruction.execute();

        return formatMowerPosition(mowers);

    }

    private String formatMowerPosition(List<Mower> mowers) {
        return mowers
            .stream()
            .map( mower -> String.format("%d %d %s",
                        mower.getX(),
                        mower.getY(),
                        mower.getOrientation().name().charAt(0)
            )).collect(Collectors.joining("\n"));

    }
}
