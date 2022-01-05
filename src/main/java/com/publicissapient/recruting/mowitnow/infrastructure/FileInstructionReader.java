package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class FileInstructionReader extends InstructionReader {
    private final Iterator<String> lineIterator;

    public FileInstructionReader(String instructionFilename) {
        final Path path = Paths.get(instructionFilename);
        try {
            this.lineIterator = Files.readAllLines(path).iterator();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file", e);
        }
    }


    @Override
    public Instruction read() throws IllegalInstructionException {
        return super.read(lineIterator);
    }
}
