package com.publicissapient.recruting.mowitnow;

import com.publicissapient.recruting.mowitnow.app.MowerInstructionService;
import com.publicissapient.recruting.mowitnow.domain.IllegalInstructionException;
import com.publicissapient.recruting.mowitnow.infrastructure.FileInstructionReader;
import com.publicissapient.recruting.mowitnow.infrastructure.InstructionReader;

public class Main {
    public static void main(String[] args) throws IllegalInstructionException {
        final String instructionFileName = args[0];

        // setup context
        final InstructionReader instructionReader = new FileInstructionReader(instructionFileName);
        final MowerInstructionService mowerInstructionService = new MowerInstructionService(instructionReader);

        //
        try {
            final String targetPosition = mowerInstructionService.mowTheSurface();
            System.out.println(targetPosition);
        } catch (IllegalInstructionException e) {
            // may be enhance
            System.err.println("Error when mowing the surface : " + e.getMessage() );
        }
    }
}
