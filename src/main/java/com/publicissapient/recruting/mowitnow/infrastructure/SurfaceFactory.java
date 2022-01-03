package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.Surface;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SurfaceFactory {

    private static final Predicate<String> VALID_LINE_PREDICATE = Pattern.compile("^\\p{Digit}+ \\p{Digit}+$").asPredicate();

    public static Surface create(String line) {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("surface definition should not be empty");
        }
        boolean lineNotValide = !VALID_LINE_PREDICATE.test(line);
        if (lineNotValide) {
            throw new IllegalArgumentException("surface definition does not match the pattern 'digit digit'");
        }
        final String[] parts = line.split(" ");
        final int width = Integer.parseInt(parts[0]);
        final int height = Integer.parseInt(parts[1]);
        return new Surface(width, height);
    }
}
