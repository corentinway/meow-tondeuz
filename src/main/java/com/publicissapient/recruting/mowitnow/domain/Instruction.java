package com.publicissapient.recruting.mowitnow.domain;

public class Instruction {

    private final Mower mower;
    private String mowerAction;

    public Instruction(Mower mower, String mowerAction) {
        this.mower = mower;
        this.mowerAction = mowerAction;
    }


    public Mower getMower() {
        return mower;
    }

    public void execute() {
        mowerAction.chars()
                .forEach(action -> mower.move((char) action));
    }
}
