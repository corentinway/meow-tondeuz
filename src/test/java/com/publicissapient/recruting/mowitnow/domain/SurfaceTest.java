package com.publicissapient.recruting.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurfaceTest {

    @Test
    void should_create_a_surface_given_its_with_and_height() {
        // GIVEN
        final int width = 5;
        final int height = 5;
        // WHEN
        final Surface actualSurface = new Surface(width, height);
        // THEN
        assertEquals(5, actualSurface.getWidth());
        assertEquals(5, actualSurface.getHeight());
    }
}
