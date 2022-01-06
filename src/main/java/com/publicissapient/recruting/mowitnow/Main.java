package com.publicissapient.recruting.mowitnow;

import com.publicissapient.recruting.mowitnow.app.MowerInstructionService;
import com.publicissapient.recruting.mowitnow.domain.IllegalInstructionException;
import com.publicissapient.recruting.mowitnow.infrastructure.FileInstructionReader;
import com.publicissapient.recruting.mowitnow.infrastructure.InstructionReader;

public class Main {
    public static void main(String[] args) throws IllegalInstructionException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expecting the file name at the 1st argument");
        }
        final String instructionFileName = args[0];

        // setup context
        final InstructionReader instructionReader = new FileInstructionReader(instructionFileName);
        final MowerInstructionService mowerInstructionService = new MowerInstructionService(instructionReader);

        // run
        try {
            final String targetPosition = mowerInstructionService.mowTheSurface();
            System.out.println(targetPosition);
        } catch (IllegalInstructionException e) {
            // may be enhance
            // log error should be great
            System.err.println("Error when mowing the surface : " + e.getMessage() );
        }
    }
}
