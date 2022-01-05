package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;

public class MowerFactory {

    private MowerFactory() {
    }

    public static Mower readMower(Surface surface, String line, int lineIndex) throws IllegalInstructionException {
        final String[] mowerParts = line.split(" ");

        int x = readNumberAt(lineIndex, 'X', mowerParts, 0);
        int y = readNumberAt(lineIndex, 'Y', mowerParts, 1);

        final Orientation orientation = readOrientation(lineIndex, mowerParts[2].charAt(0));
        Mower mower = null;
        try {
            mower = new Mower(surface, x, y, orientation);
        } catch (MowerCoordinateOutOfBoundException e) {
            throw new IllegalInstructionException("invalid mower position line " + (lineIndex + 1), e);
        }
        return mower;
    }

    private static int readNumberAt(int lineIndex, char type, String[] mowerParts, int partPosition) throws IllegalInstructionException {
        final String value = mowerParts[partPosition];
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            int endIndex = Math.min(100, value.length());
            final String safeValue = value.substring(0, endIndex);
            throw new IllegalInstructionException("invalid " + type + " position of the mower at line " + (lineIndex + 1) + " : " + safeValue, e);
        }
    }

    private static Orientation readOrientation(int lineIndex, char value) throws IllegalInstructionException {
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
