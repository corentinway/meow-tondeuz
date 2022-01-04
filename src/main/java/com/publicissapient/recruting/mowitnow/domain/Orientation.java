package com.publicissapient.recruting.mowitnow.domain;

public enum Orientation {
    NORTH, WEST, SOUTH, EAST;


    public Orientation left() {
        final int nextPosition = (this.ordinal() + 1) % 4;

        return Orientation.values()[nextPosition];
    }
    public Orientation right() {
        final int nextPosition = this.ordinal() == 0 ? 3 : this.ordinal() - 1;
        return Orientation.values()[nextPosition];
    }


}
