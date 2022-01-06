package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class InstructionReader {


    public Instruction read() throws IllegalInstructionException {
        final Iterator<String> instructionIterator = buildInstructionIterator();
        return read(instructionIterator);
    }

    protected abstract Iterator<String> buildInstructionIterator();

    private Instruction read(Iterator<String> lineIterator) throws IllegalInstructionException {
        int lineIndex = 0;
        String line = lineIterator.next();
        final Surface surface = SurfaceFactory.create(line);

        List<Instruction.MowerAction> mowersActions = new ArrayList<>();

        while (lineIterator.hasNext()) {
            line = lineIterator.next();
            ++lineIndex;
            final Mower mower = MowerFactory.readMower(surface, line, lineIndex);

            line = lineIterator.next();
            ++lineIndex;
            final String mowerAction = line.trim();

            mowersActions.add(new Instruction.MowerAction(mower, mowerAction));
        }

        return new Instruction(mowersActions);
    }


}
