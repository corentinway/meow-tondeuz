package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;

import java.util.Iterator;

public abstract class InstructionReader {


    public abstract Instruction read() throws IllegalInstructionException;

    public Instruction read(Iterator<String> lineIterator) throws IllegalInstructionException {
        int lineIndex = 0;
        String line = lineIterator.next();
        final Surface surface = SurfaceFactory.create(line);

        line = lineIterator.next();
        ++lineIndex;
        final Mower mower = MowerFactory.readMower(surface, line, lineIndex);

        line = lineIterator.next();
        ++lineIndex;
        final String mowerAction = line.trim();

        return new Instruction(mower, mowerAction);
    }



}
