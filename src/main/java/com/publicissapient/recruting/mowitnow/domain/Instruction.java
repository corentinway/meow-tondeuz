package com.publicissapient.recruting.mowitnow.domain;

public class Instruction {

    private final Surface surface;
    private final Mower mower;
    private String mowerAction;

    public Instruction(Surface surface, Mower mower, String mowerAction) {
        this.surface = surface;
        this.mower = mower;
        this.mowerAction = mowerAction;
    }

    public Surface getSurface() {
        return surface;
    }

    public Mower getMower() {
        return mower;
    }

    public void execute() {
        mowerAction.chars()
                .forEach( action -> mower.move((char) action));
    }
}
