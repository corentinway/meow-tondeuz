package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InstructionReader {
    private final List<String> lines;

    public InstructionReader(String instructionFilename) {
        final Path path = Paths.get(instructionFilename);
        try {
            this.lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file", e);
        }
    }

    public Instruction read() throws IllegalInstructionException {
        final Surface surface = SurfaceFactory.create(lines.get(0));

        final String line = lines.get(1);
        final Mower mower = readMower(surface, line, 1); // TODO move this into a mower factory ?
        final String mowerAction = lines.get(2).trim();

        return new Instruction(surface, mower, mowerAction);
    }

    private Mower readMower(Surface surface, String line, int lineIndex) throws IllegalInstructionException {
        final String[] mowerParts = line.split(" ");

        int x = readNumberAt(lineIndex, 'X', mowerParts, 0);
        int y = readNumberAt(lineIndex, 'Y', mowerParts, 1);

        final Orientation orientation = readOrientation(lineIndex, mowerParts[2].charAt(0)); // FIXME exception handling
        Mower mower = null;
        try {
            mower = new Mower(surface, x, y, orientation);
        } catch (MowerCoordinateOutOfBoundException e) {
            throw new IllegalInstructionException("invalid mower position line " + (lineIndex + 1), e);
        }
        return mower;
    }

    private int readNumberAt(int lineIndex, char type, String[] mowerParts, int partPosition) throws IllegalInstructionException {
        final String value = mowerParts[partPosition];
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            int endIndex = Math.min(100, value.length());
            final String safeValue = value.substring(0, endIndex);
            throw new IllegalInstructionException("invalid " + type + " position of the mower at line " + (lineIndex + 1) + " : " + safeValue, e);
        }
    }

    private Orientation readOrientation(int lineIndex, char value) throws IllegalInstructionException {
        switch (value) {
            case 'N':
                return Orientation.NORTH;
            case 'W':
                return Orientation.WEST;
            case 'E':
                return Orientation.EAST;
            case 'S':
                return Orientation.SOUTH;
            default:
                throw new IllegalInstructionException("invalid orientation of the mower at line " + (lineIndex + 1) + " : " + value);
        }
    }
}
