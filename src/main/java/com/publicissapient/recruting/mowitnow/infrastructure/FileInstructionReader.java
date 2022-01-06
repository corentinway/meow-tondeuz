package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class FileInstructionReader extends InstructionReader {
    private final Iterator<String> lineIterator;

    public FileInstructionReader(String instructionFilename) throws IllegalInstructionException {
        final Path path = Paths.get(instructionFilename);
        try {
            final List<String> lines = Files.readAllLines(path);
            validateFile(lines);
            this.lineIterator = lines.iterator();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file", e);
        }
    }

    private void validateFile(List<String> lines) throws IllegalInstructionException {
        if (lines.size() < 3) {
            throw new IllegalInstructionException("File instruction should have 3 lines at least");
        }
        if (lines.size() % 2 == 0) {
            throw new IllegalInstructionException("File instruction should have odd number of lines");
        }
    }


    @Override
    protected Iterator<String> buildInstructionIterator() {
        return lineIterator;
    }
}
