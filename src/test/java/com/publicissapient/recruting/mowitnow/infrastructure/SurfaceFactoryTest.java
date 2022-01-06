package com.publicissapient.recruting.mowitnow.infrastructure;

import com.publicissapient.recruting.mowitnow.domain.Surface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SurfaceFactoryTest {

    @Test
    void should_read_a_surface_given_a_string_with_2_numbers() {
        // GIVEN
        final String line = "5 5";
        // WHEN
        final Surface actualSurface = SurfaceFactory.create(line);
        // THEN
        assertEquals(5, actualSurface.getWidth());
        assertEquals(5, actualSurface.getHeight());
    }

    @Test
    void should_throw_exception_given_an_empty_string() {
        // GIVEN
        final String line = "";
        // WHEN
        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> SurfaceFactory.create(line));
        // THEN
        assertEquals("surface definition should not be empty", actualException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2 2 2",
            "qdsq 2",
            " 5  5",
            "9  9",
            "9 9       "
    })
    void should_throw_exception_given_a_line_not_matching_2_int_patterns(String line) {
        // WHEN
        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> SurfaceFactory.create(line));
        // THEN
        assertEquals("surface definition does not match the pattern 'digit digit'", actualException.getMessage());
    }
}
